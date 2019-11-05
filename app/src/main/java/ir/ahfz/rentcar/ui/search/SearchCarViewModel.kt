package ir.ahfz.rentcar.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.io.model.*
import ir.ahfz.rentcar.repository.CarRepository
import ir.ahfz.rentcar.repository.PublicAccessRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchCarViewModel(
    private val publicAccessRepository: PublicAccessRepository,
    private val carRepository: CarRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }

    val errorLiveData = MutableLiveData<String?>()
    val carList = MutableLiveData<List<CarResponse.Car>>()
    val makes = MutableLiveData<MakeResponse>()
    val fuels = MutableLiveData<FuelResponse>()
    val classes = MutableLiveData<ClassResponse>()
    val filterFuelType = ArrayList<String>()
    val filterCarType = ArrayList<Int>()
    val filterCarBrand = ArrayList<String>()
    var filterTransmission = -1
    var orderBy = "pricePerDay"
    var ascending = true
    var search = ""

    init {
        load()
        filter("")
    }

    fun filter(search: String?) {
        if (search != null) {
            this.search = search
        }
        viewModelScope.launch {
            val filteredCars = carRepository.getCarsByFilter(
                search ?: "",
                filterCarBrand,
                filterCarType,
                filterFuelType,
                filterTransmission,
                orderBy,
                ascending
            )
            carList.postValue(filteredCars)
        }
    }

    private fun load() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val classes = publicAccessRepository.getClasses()
            this@SearchCarViewModel.classes.postValue(classes.body())
        }
        viewModelScope.launch(Dispatchers.IO + exceptionHandler)  {
            val makes = publicAccessRepository.getMakes()
            this@SearchCarViewModel.makes.postValue(makes.body())
        }
        viewModelScope.launch(Dispatchers.IO + exceptionHandler)  {
            val fuels = publicAccessRepository.getFuels()
            this@SearchCarViewModel.fuels.postValue(fuels.body())
        }
    }

    fun setFilterTransmission(checkedItem: List<Checkable>) {
        if (checkedItem.isEmpty() || checkedItem.size == 2) {
            filterTransmission = -1
        } else if (checkedItem.size == 1) {
            if (checkedItem[0].getTitle() == "Auto")
                filterTransmission = 1
            else
                filterTransmission = 0
        }
    }

    fun setFilterBrand(checkedItem: List<Checkable>) {
        filterCarBrand.clear()
        checkedItem.forEach {
            if (it is MakeResponse.Make)
                filterCarBrand.add(it.make ?: " ")
        }
    }

    fun setFilterFuel(checkedItem: List<Checkable>) {
        filterFuelType.clear()
        checkedItem.forEach {
            if (it is FuelResponse.Fuel)
                filterFuelType.add(it.fuel ?: " ")
        }

    }

    fun setFilterCarType(checkedItem: List<Checkable>) {
        filterCarType.clear()
        checkedItem.forEach {
            if (it is MakeResponse.Make)
                filterCarType.add(it.id)
        }
    }
}