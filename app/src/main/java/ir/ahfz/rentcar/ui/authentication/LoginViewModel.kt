package ir.ahfz.rentcar.ui.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.io.network.model.AuthenticatedResponse
import ir.ahfz.rentcar.repository.AuthenticationRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val authenticationRepository: AuthenticationRepository) : ViewModel() {


    val successLogin = MutableLiveData<AuthenticatedResponse?>()
    val failedLogin = MutableLiveData<String?>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        failedLogin.postValue(throwable.message)
    }

    fun login(email: String?, password: String?) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = authenticationRepository.login(email, password)
            successLogin.postValue(response)
        }
    }

}