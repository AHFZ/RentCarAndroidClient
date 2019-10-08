package ir.ahfz.rentcar.io.model

import ir.ahfz.rentcar.io.network.BaseResponse

/**
 * Data model for Model response
 */
data class ModelResponse(
    var models: List<Model>? = null
) : BaseResponse() {

    /**
     * Data model for Model
     */
    data class Model(
        var id: String? = null,
        var makeId: String? = null,
        var model: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null,
        var make: String? = null
    )
}
