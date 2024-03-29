package ir.ahfz.rentcar.repository

import ir.ahfz.rentcar.io.model.AuthenticatedResponse
import ir.ahfz.rentcar.io.model.LoginRequest
import ir.ahfz.rentcar.io.model.RegisterRequest
import ir.ahfz.rentcar.io.network.webservice.AuthenticationWebservice
import retrofit2.awaitResponse


class AuthenticationRepository(private val authenticationWebservice: AuthenticationWebservice) {

    suspend fun login(email: String?, password: String?): AuthenticatedResponse? {
        val loginTry =
            authenticationWebservice.login(LoginRequest(email, password)).awaitResponse()
        if (loginTry.isSuccessful) {
            val authenticatedResponse = authenticationWebservice.isAuthenticated().awaitResponse()
            if (authenticatedResponse.isSuccessful) {
                return authenticatedResponse.body()
            } else {
                throw Exception(authenticatedResponse.message())
            }
        } else {
            throw Exception(loginTry.message())
        }
    }

    suspend fun register(
        name: String?,
        email: String?,
        password: String?,
        passwordConfirmation: String?,
        city: String?,
        address: String?,
        phoneNumber: String?
    ): AuthenticatedResponse? {

        val authenticated = isAuthenticated()
        if (authenticated != null) {
            throw Exception("To registration, please logout from other account.")
        }
        val awaitResponse = authenticationWebservice.register(
            RegisterRequest(
                name,
                email,
                password,
                passwordConfirmation,
                address,
                city,
                phoneNumber
            )
        ).awaitResponse()

        if (!awaitResponse.isSuccessful) {
            throw Exception(awaitResponse.code().toString())
        }

        return isAuthenticated() ?: throw Exception("Unknown error")
    }

    suspend fun logout() {
        isAuthenticated() ?: throw Exception("Your are not login!")
        val logoutResponse = authenticationWebservice.logout().awaitResponse()
        if (!logoutResponse.isSuccessful)
            throw Exception(logoutResponse.message())
    }

    suspend fun isAuthenticated(): AuthenticatedResponse? {
        try {
            val isAuthenticatedResponse = authenticationWebservice.isAuthenticated().awaitResponse()
            //todo -> control other exception
            return isAuthenticatedResponse.body()
        } catch (e: Exception) {
            return null
        }
    }
}