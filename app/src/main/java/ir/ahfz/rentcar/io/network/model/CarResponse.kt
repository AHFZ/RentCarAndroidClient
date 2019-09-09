package ir.ahfz.rentcar.io.network.model

import ir.ahfz.rentcar.io.network.BaseResponse

/**
 * Data model for Car Response
 */
data class CarResponse(var cars: List<Car>? = null) : BaseResponse() {

    /**
     * Data model for Car
     */
    data class Car(
        var id: String? = null,
        var modelId: String? = null,
        var fuel: String? = null,
        var registration: String? = null,
        var color: Any? = null,
        var year: String? = null,
        var capacity: String? = null,
        var isAutomatic: String? = null,
        var equipment: Any? = null,
        var class_: String? = null,
        var type: String? = null,
        var minAge: String? = null,
        var pricePerDay: String? = null,
        var img: String? = null,
        var branchID: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null,
        var make: String? = null,
        var model: String? = null,
        var branch: String? = null
    )
}
