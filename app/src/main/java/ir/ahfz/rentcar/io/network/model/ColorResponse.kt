package ir.ahfz.rentcar.io.network.model

import ir.ahfz.rentcar.io.network.BaseResponse

/**
 * Data model for Branch response
 */
class ColorResponse(
    var colors: List<Color>? = null
) : BaseResponse() {
    /**
     * Data model for Branch response
     */
    data class Color(
        var id: Int = 0,
        var color: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null
    )
}
