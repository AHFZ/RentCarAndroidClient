package ir.ahfz.rentcar.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.io.model.CarResponse
import ir.ahfz.rentcar.io.model.ClassResponse
import ir.ahfz.rentcar.io.model.FuelResponse
import ir.ahfz.rentcar.io.model.MakeResponse
import ir.ahfz.rentcar.repository.CarRepository
import ir.ahfz.rentcar.repository.PublicAccessRepository
import kotlinx.coroutines.launch

class SearchCarViewModel(
    private val publicAccessRepository: PublicAccessRepository,
    private val carRepository: CarRepository
) : ViewModel() {

    val carList = MutableLiveData<List<CarResponse.Car>>()
    val makes = MutableLiveData<MakeResponse>()
    val fuels = MutableLiveData<FuelResponse>()
    val classes = MutableLiveData<ClassResponse>()
    val filterFuelType = ArrayList<String>()
    val filterCarType = ArrayList<Int>()
    val filterCarBrand = ArrayList<String>()
    val filterTransmission = -1
    var orderBy = "pricePerDay"
    var ascending = true

    init {
        load()
        filter("")
    }

    fun filter(search: String) {
        viewModelScope.launch {
            val filteredCars = carRepository.getCarsByFilter(
                search,
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
        viewModelScope.launch {
            val classes = publicAccessRepository.getClasses()
            this@SearchCarViewModel.classes.postValue(classes.body())
        }
        viewModelScope.launch {
            val makes = publicAccessRepository.getMakes()
            this@SearchCarViewModel.makes.postValue(makes.body())
        }
        viewModelScope.launch {
            val fuels = publicAccessRepository.getFuels()
            this@SearchCarViewModel.fuels.postValue(fuels.body())
        }
    }
}