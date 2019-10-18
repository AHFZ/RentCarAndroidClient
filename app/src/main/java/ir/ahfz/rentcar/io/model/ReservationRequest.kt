package ir.ahfz.rentcar.io.model

import ir.ahfz.rentcar.io.network.BaseRequest

data class ReservationRequest(
    var pickupDate: String? = null,
    var returnDate: String? = null,
    var pickupLocation: String? = "0",
    var returnLocation: String? = "0",
    var pickupTimeH: String? = "00",
    var pickupTimeM: String? = "00",
    var returnTimeH: String? = "00",
    var returnTimeM: String? = "00",
    var selectedCarID: String? = null,
    var totalPrice: String? = "",
    var arrayOfSelectedExtras: String? = ""
) : BaseRequest()