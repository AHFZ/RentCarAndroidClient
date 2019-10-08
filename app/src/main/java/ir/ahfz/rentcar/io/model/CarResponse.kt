package ir.ahfz.rentcar.io.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.ahfz.rentcar.io.network.BaseResponse

/**
 * Data model for Car Response
 */
data class CarResponse(var cars: List<Car>? = null) : BaseResponse() {

    /**
     * Data model for Car
     */
    @Entity
    data class Car(
        @PrimaryKey
        var id: String = "",
        var modelId: String? = null,
        var fuel: String? = null,
        var registration: String? = null,
        var color: String? = null,
        var year: String? = null,
        var capacity: String? = null,
        var isAutomatic: String? = null,
        var equipment: String? = null,
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
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(modelId)
            parcel.writeString(fuel)
            parcel.writeString(registration)
            parcel.writeString(color)
            parcel.writeString(year)
            parcel.writeString(capacity)
            parcel.writeString(isAutomatic)
            parcel.writeString(equipment)
            parcel.writeString(class_)
            parcel.writeString(type)
            parcel.writeString(minAge)
            parcel.writeString(pricePerDay)
            parcel.writeString(img)
            parcel.writeString(branchID)
            parcel.writeString(createdAt)
            parcel.writeString(updatedAt)
            parcel.writeString(make)
            parcel.writeString(model)
            parcel.writeString(branch)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Car> {
            override fun createFromParcel(parcel: Parcel): Car {
                return Car(parcel)
            }

            override fun newArray(size: Int): Array<Car?> {
                return arrayOfNulls(size)
            }
        }
    }
}
