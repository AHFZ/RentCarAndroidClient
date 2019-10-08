package ir.ahfz.rentcar.io.model

import ir.ahfz.rentcar.io.network.BaseResponse

/**
 * Data model for City response
 */
data class CityResponse(var cities: List<City>? = null) : BaseResponse() {

    /**
     * Data model for City
     */
    data class City(
        var id: Int = 0,
        var city: String? = null,
        var postcode: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null
    )
}
