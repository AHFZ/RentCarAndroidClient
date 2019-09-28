package ir.ahfz.rentcar.repository

import ir.ahfz.rentcar.io.network.model.ReservationHistoryResponse
import ir.ahfz.rentcar.io.network.webservice.PrivateAccessWebservice
import retrofit2.Response
import retrofit2.awaitResponse

class PrivateAccessRepository(private val privateAccessWebservice: PrivateAccessWebservice) {

    suspend fun getReservationHistory(): Response<ReservationHistoryResponse> {
        return privateAccessWebservice.getReservationHistory().awaitResponse()
    }
}