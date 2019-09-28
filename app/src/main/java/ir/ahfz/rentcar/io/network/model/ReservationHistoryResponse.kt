package ir.ahfz.rentcar.io.network.model

import ir.ahfz.rentcar.io.network.BaseResponse

data class ReservationHistoryResponse(var reservations: List<Reservation>) : BaseResponse() {

    data class Reservation(
        var id: String? = null,
        var userID: String? = null,
        var carID: String? = null,
        var pickupLocation: String? = null,
        var returnLocation: String? = null,
        var pickupDate: String? = null,
        var returnDate: String? = null,
        var extras: String? = null,
        var price: String? = null,
        var isPending: Any? = null,
        var isPaid: Any? = null,
        var isCompleted: Any? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null,
        var make: String? = null,
        var model: String? = null,
        var fuel: String? = null,
        var branch: String? = null,
        var class_: String? = null
    )
}