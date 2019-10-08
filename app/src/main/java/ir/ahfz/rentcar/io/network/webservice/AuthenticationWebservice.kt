package ir.ahfz.rentcar.io.network.webservice

import ir.ahfz.rentcar.io.model.AuthenticatedResponse
import ir.ahfz.rentcar.io.model.LoginRequest
import ir.ahfz.rentcar.io.model.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationWebservice {

    @POST("/login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<ResponseBody>

    @POST("/register")
    fun register(@Body registerRequest: RegisterRequest): Call<ResponseBody>

    @GET("/api/authenticated")
    fun isAuthenticated(): Call<AuthenticatedResponse>

    @GET("/logout")
    fun logout(): Call<ResponseBody>
}