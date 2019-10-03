package ir.ahfz.rentcar.io.network.model

import ir.ahfz.rentcar.io.network.BaseResponse

/**
 * Data model for Branch response
 */
data class BranchResponse(var branches: List<Branch>? = null) : BaseResponse() {

    /**
     * Data model for Branch
     */
    data class Branch(
        var id: String? = null,
        var name: String? = null,
        var email: String? = null,
        var address: String? = null,
        var cityId: String? = null,
        var phone: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null,
        var city: String? = null
    )
}
