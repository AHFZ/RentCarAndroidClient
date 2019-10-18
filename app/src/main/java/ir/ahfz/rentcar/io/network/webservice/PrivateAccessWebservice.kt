package ir.ahfz.rentcar.io.network.webservice

import ir.ahfz.rentcar.io.model.ReservationHistoryResponse
import ir.ahfz.rentcar.io.model.ReservationRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PrivateAccessWebservice {

    @POST("/api/reservation/save")
    fun saveReservation(@Body reservationRequest: ReservationRequest): Call<ResponseBody>

    @GET("/api/users/reservations")
    fun getReservationHistory(): Call<ReservationHistoryResponse>

}