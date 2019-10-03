package ir.ahfz.rentcar.io.network.webservice

import ir.ahfz.rentcar.io.network.model.ReservationHistoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface PrivateAccessWebservice {

    @POST("/api/reservation/save")
    fun saveReservation()

    @GET("/api/users/reservations")
    fun getReservationHistory(): Call<ReservationHistoryResponse>

}