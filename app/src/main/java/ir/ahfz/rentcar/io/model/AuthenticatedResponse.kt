package ir.ahfz.rentcar.io.model

import android.os.Parcel
import android.os.Parcelable

data class AuthenticatedResponse(
    var id: Int = 0,
    var name: String? = null,
    var email: String? = null,
    var isAdmin: String? = null,
    var address: String? = null,
    var city: String? = null,
    var phone: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(isAdmin)
        parcel.writeString(address)
        parcel.writeString(city)
        parcel.writeString(phone)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AuthenticatedResponse> {
        override fun createFromParcel(parcel: Parcel): AuthenticatedResponse {
            return AuthenticatedResponse(parcel)
        }

        override fun newArray(size: Int): Array<AuthenticatedResponse?> {
            return arrayOfNulls(size)
        }
    }
}