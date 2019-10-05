package ir.ahfz.rentcar.io.network.model

data class AuthenticatedResponse(
    var id: Int = 0,
    var name: String? = null,
    var email: String? = null,
    var isAdmin: String? = null,
    var address: String? = null,
    var city: String? = null,
    var phone: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
)