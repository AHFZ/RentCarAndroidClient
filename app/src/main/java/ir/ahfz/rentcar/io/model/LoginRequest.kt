package ir.ahfz.rentcar.io.model

import ir.ahfz.rentcar.io.network.BaseRequest

data class LoginRequest(var email: String?, var password: String?) : BaseRequest()