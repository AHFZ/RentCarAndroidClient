package ir.ahfz.rentcar.ui.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.io.model.AuthenticatedResponse
import ir.ahfz.rentcar.repository.AuthenticationRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(private val authenticationRepository: AuthenticationRepository) :
    ViewModel() {

    val registrationResult = MutableLiveData<AuthenticatedResponse>()
    val errorLiveData = MutableLiveData<String>()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorLiveData.postValue(throwable.message)
    }

    fun requestForRegistration(
        name: String?,
        email: String?,
        password: String?,
        passwordConfirmation: String?,
        city: String?,
        address: String?,
        phoneNumber: String?
    ) {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            val register = authenticationRepository.register(
                name,
                email,
                password,
                passwordConfirmation,
                city,
                address,
                phoneNumber
            )
            registrationResult.postValue(register)
        }
    }
}