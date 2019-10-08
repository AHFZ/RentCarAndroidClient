package ir.ahfz.rentcar.io.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.ahfz.rentcar.io.network.BaseResponse

/**
 * Data model for Make response
 */
data class MakeResponse(var makes: List<Make>? = null) : BaseResponse() {
    /**
     * Data model for Make
     */
    @Entity(tableName = "Brand")
    data class Make(
        @PrimaryKey
        var id: Int = 0,
        var make: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null
    )
}