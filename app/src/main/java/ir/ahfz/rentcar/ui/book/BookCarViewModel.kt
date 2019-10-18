package ir.ahfz.rentcar.ui.book

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.model.CarResponse
import ir.ahfz.rentcar.io.model.ExtraResponse
import ir.ahfz.rentcar.io.model.ReservationRequest
import ir.ahfz.rentcar.repository.PrivateAccessRepository
import ir.ahfz.rentcar.repository.PublicAccessRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookCarViewModel(
    private val publicAccessRepository: PublicAccessRepository,
    private val privateAccessRepository: PrivateAccessRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }
    val errorLiveData = MutableLiveData<String?>()
    val extraResponseLiveData = MutableLiveData<ExtraResponse>()
    val pickupDate = MutableLiveData<String>()
    val returnDate = MutableLiveData<String>()
    lateinit var car: CarResponse.Car

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val extras = publicAccessRepository.getExtras()
            if (extras.isSuccessful)
                extraResponseLiveData.postValue(extras.body())
        }
    }

    fun setPickupDate(year: Int, month: Int, dayOfMonth: Int) {
        pickupDate.postValue("$year/$month/$dayOfMonth")
    }

    fun setReturnDate(year: Int, month: Int, dayOfMonth: Int) {
        returnDate.postValue("$year/$month/$dayOfMonth")
    }

    fun bookIt(bookCarActivity: BookCarActivity) {
        val reservationRequest = ReservationRequest()
        reservationRequest.pickupDate = pickupDate.value
        reservationRequest.returnDate = returnDate.value
        reservationRequest.selectedCarID = car.id
        reservationRequest.totalPrice = calculatePrice()

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
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
    }

    private fun calculatePrice(): String? {
        return "1000"
    }

}
