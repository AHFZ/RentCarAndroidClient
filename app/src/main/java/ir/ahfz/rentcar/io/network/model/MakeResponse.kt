package ir.ahfz.rentcar.io.network.model

import ir.ahfz.rentcar.io.network.BaseResponse
/**
 * Data model for Make response
 */
data class MakeResponse(var makes: List<Make>? = null) : BaseResponse() {
    /**
     * Data model for Make
     */
    data class Make(
        var id: Int = 0,
        var make: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null
    )
}