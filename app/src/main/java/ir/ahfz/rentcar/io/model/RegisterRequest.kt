package ir.ahfz.rentcar.io.model

import ir.ahfz.rentcar.io.network.BaseRequest

data class RegisterRequest(
    var name: String?,
    var email: String?,
    var password: String?,
    var password_confirmation: String?,
    var address: String?,
    var city: String?,
    var contact: String?
) : BaseRequest()
