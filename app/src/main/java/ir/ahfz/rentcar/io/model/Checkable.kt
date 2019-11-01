package ir.ahfz.rentcar.io.model

interface Checkable {
    var isChecked: Boolean

    fun getTitle(): String?
}