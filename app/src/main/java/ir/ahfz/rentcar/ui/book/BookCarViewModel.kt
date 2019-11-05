package ir.ahfz.rentcar.ui.book

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.model.CarResponse
import ir.ahfz.rentcar.io.model.CityResponse
import ir.ahfz.rentcar.io.model.ExtraResponse
import ir.ahfz.rentcar.io.model.ReservationRequest
import ir.ahfz.rentcar.repository.AuthenticationRepository
import ir.ahfz.rentcar.repository.CarRepository
import ir.ahfz.rentcar.repository.PrivateAccessRepository
import ir.ahfz.rentcar.repository.PublicAccessRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.random.Random

class BookCarViewModel(
    private val publicAccessRepository: PublicAccessRepository,
    private val authenticationRepository: AuthenticationRepository,
    private val privateAccessRepository: PrivateAccessRepository,
    private val carRepository: CarRepository
) : ViewModel() {

    var pickupLocation: CityResponse.City? = null
    var returnLocation: CityResponse.City? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }
    val errorLiveData = MutableLiveData<String?>()
    val extraResponseLiveData = MutableLiveData<ExtraResponse>()
    var cities = ArrayList<CityResponse.City>()
    val pickupDate = MutableLiveData<String>()
    val returnDate = MutableLiveData<String>()
    lateinit var car: CarResponse.Car

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val extras = publicAccessRepository.getExtras()
            if (extras.isSuccessful)
                extraResponseLiveData.postValue(extras.body())
            val citiesResponse = publicAccessRepository.getCities()
            cities = citiesResponse.body()!!.cities as ArrayList<CityResponse.City>
        }
    }

    fun setPickupDate(year: Int, month: Int, dayOfMonth: Int) {
        pickupDate.postValue("$year/$month/$dayOfMonth")
    }

    fun setReturnDate(year: Int, month: Int, dayOfMonth: Int) {
        returnDate.postValue("$year/$month/$dayOfMonth")
    }

    fun bookIt(bookCarActivity: BookCarActivity) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val reservationRequest = ReservationRequest()
            reservationRequest.pickupDate = pickupDate.value
            reservationRequest.returnDate = returnDate.value
            reservationRequest.selectedCarID = car.id
            reservationRequest.totalPrice =
                calculatePrice(car.id, pickupDate.value!!, returnDate.value!!)
            reservationRequest.pickupLocation = pickupLocation?.id.toString()
            reservationRequest.returnLocation = returnLocation?.id.toString()

            val authenticated = authenticationRepository.isAuthenticated()
            if (authenticated != null) {
                privateAccessRepository.saveReservation(reservationRequest).apply {
                    if (isSuccessful) {
                        launch(Dispatchers.Main) {
                            Toast.makeText(
                                bookCarActivity,
                                bookCarActivity.getString(R.string.succssfully_booked),
                                Toast.LENGTH_SHORT
                            ).show()
                            bookCarActivity.setResult(AppCompatActivity.RESULT_OK)
                            bookCarActivity.finish()
                        }
                    }
                }
            }
            throw Exception("You are not login!")
        }
    }

    private suspend fun calculatePrice(
        carId: String,
        pickDate: String,
        returnDate: String
    ): String? {
        val days = calculateNumberOfDate(pickDate, returnDate)
        carRepository.getAllCars().forEach {
            if (it.id == carId)
                return (it.pricePerDay?.toInt()?.times(days)).toString()
        }
        return (100 * Random(2).nextInt(20)).toString()
    }

    private fun calculateNumberOfDate(
        pickDate: String,
        returnDate: String
    ): Int {
        val splitPickDate = pickDate.split("/")
        val splitReturnDate = returnDate.split("/")
        if (splitReturnDate[0] != splitReturnDate[0])
            throw Exception("You cant book from $pickDate to $returnDate")

        val days = if (splitPickDate[1] == splitReturnDate[1])
            abs(splitPickDate[2].toInt() - splitReturnDate[2].toInt())
        else {
            abs(splitPickDate[1].toInt() - splitReturnDate[1].toInt() - 1) * 30
            +abs(30 - splitPickDate[2].toInt())
            +abs(0 - splitReturnDate[2].toInt())
        }
        return days
    }

}
