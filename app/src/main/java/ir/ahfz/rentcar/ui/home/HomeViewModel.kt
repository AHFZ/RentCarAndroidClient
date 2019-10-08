package ir.ahfz.rentcar.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.io.model.AuthenticatedResponse
import ir.ahfz.rentcar.io.model.CarResponse
import ir.ahfz.rentcar.io.model.MakeResponse
import ir.ahfz.rentcar.repository.AuthenticationRepository
import ir.ahfz.rentcar.repository.CarRepository
import ir.ahfz.rentcar.repository.PublicAccessRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val publicAccessRepository: PublicAccessRepository,
    private val authenticationRepository: AuthenticationRepository,
    private val carRepository: CarRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }
    val errorLiveData = MutableLiveData<String?>()
    val userAuthLiveData = MutableLiveData<AuthenticatedResponse?>()
    val carListLiveData = carRepository.getAllCarsLive()
    val makeListLiveData = MutableLiveData<List<MakeResponse.Make>>()

    init {
        getBrands()
        getCars()
        isAuthenticated()
    }

    private fun isAuthenticated() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val isAuthResponse = authenticationRepository.isAuthenticated()
            userAuthLiveData.postValue(isAuthResponse)
        }
    }

    private fun getBrands() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val makeResponse = publicAccessRepository.getMakes()
            makeListLiveData.postValue(makeResponse.body()?.makes)
        }
    }

    private fun getCars() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val carResponse = publicAccessRepository.getCars()
            carRepository.addCarIgnoreConflict(carResponse.body()?.cars ?: return@launch)
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            authenticationRepository.logout()
            userAuthLiveData.postValue(null)
        }
    }
}