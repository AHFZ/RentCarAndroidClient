package ir.ahfz.rentcar.io.network.model

import ir.ahfz.rentcar.io.network.BaseResponse
/**
 * Data model for class response
 */
data class ClassResponse(
    var types: List<Type>? = null
) : BaseResponse() {
    /**
     * Data model for Type response
     */
    data class Type(
        var id: Int = 0,
        var class_: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null
    )
}
