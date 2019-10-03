package ir.ahfz.rentcar.io.network.model

import ir.ahfz.rentcar.io.network.BaseResponse

/**
 * Data model for Fuel response
 */
data class FuelResponse(
    var fuels: List<Fuel>? = null
) : BaseResponse() {

    /**
     * Data model for Fuel
     */
    data class Fuel(
        var id: Int = 0,
        var fuel: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null
    )
}
