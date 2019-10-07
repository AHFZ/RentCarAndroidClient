package ir.ahfz.rentcar.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.io.network.model.AuthenticatedResponse
import ir.ahfz.rentcar.io.network.model.CarResponse
import ir.ahfz.rentcar.io.network.model.MakeResponse
import ir.ahfz.rentcar.repository.AuthenticationRepository
import ir.ahfz.rentcar.repository.PublicAccessRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val publicAccessRepository: PublicAccessRepository,
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }
    val errorLiveData = MutableLiveData<String?>()
    val userAuthLiveData = MutableLiveData<AuthenticatedResponse?>()
    val carListLiveData = MutableLiveData<List<CarResponse.Car>>()
    val makeListLiveData = MutableLiveData<List<MakeResponse.Make>>()

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val isAuthResponse = authenticationRepository.isAuthenticated()
            userAuthLiveData.postValue(isAuthResponse)
        }
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val makeResponse = publicAccessRepository.getMakes()
            makeListLiveData.postValue(makeResponse.body()?.makes)
        }
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val carResponse = publicAccessRepository.getCars()
            carListLiveData.postValue(carResponse.body()?.cars)
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            authenticationRepository.logout()
            userAuthLiveData.postValue(null)
        }
    }
}