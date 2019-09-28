package ir.ahfz.rentcar.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.io.network.model.ReservationHistoryResponse
import ir.ahfz.rentcar.repository.PrivateAccessRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyReservationViewModel(
    private val privateAccessRepository: PrivateAccessRepository
) :
    ViewModel() {

    val reservationLiveData = MutableLiveData<List<ReservationHistoryResponse.Reservation>>()
    val errorLiveData = MutableLiveData<String?>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }

    init {
        getReservationHistoryList()
    }

    fun getReservationHistoryList() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val reservationHistory = privateAccessRepository.getReservationHistory()
            if (reservationHistory.isSuccessful)
                reservationLiveData.postValue(reservationHistory.body()?.reservations)
        }
    }
}