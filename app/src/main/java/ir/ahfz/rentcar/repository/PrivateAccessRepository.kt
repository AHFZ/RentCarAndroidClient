package ir.ahfz.rentcar.repository

import ir.ahfz.rentcar.io.model.ReservationHistoryResponse
import ir.ahfz.rentcar.io.model.ReservationRequest
import ir.ahfz.rentcar.io.network.webservice.PrivateAccessWebservice
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.awaitResponse

class PrivateAccessRepository(private val privateAccessWebservice: PrivateAccessWebservice) {

    suspend fun getReservationHistory(): Response<ReservationHistoryResponse> {
        return privateAccessWebservice.getReservationHistory().awaitResponse()
    }

    suspend fun saveReservation(reservationRequest: ReservationRequest): Response<ResponseBody> {
        return privateAccessWebservice.saveReservation(reservationRequest).awaitResponse()
    }
}