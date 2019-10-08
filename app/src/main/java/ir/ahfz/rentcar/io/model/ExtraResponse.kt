package ir.ahfz.rentcar.io.model

import ir.ahfz.rentcar.io.network.BaseResponse

/**
 * Data model for Extra response
 */
data class ExtraResponse(var extras: List<Extra>? = null) : BaseResponse() {

    /**
     * Data model for Extra
     */
    data class Extra(
        var id: Int = 0,
        var title: String? = null,
        var description: String? = null,
        var price: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null
    ) {


    }

}
