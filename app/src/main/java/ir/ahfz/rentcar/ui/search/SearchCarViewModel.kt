package ir.ahfz.rentcar.ui.search

import ir.ahfz.rentcar.io.model.FuelResponse
import ir.ahfz.rentcar.repository.CarRepository
import ir.ahfz.rentcar.repository.PublicAccessRepository

class SearchCarViewModel(
    private val publicAccessRepository: PublicAccessRepository,
    private val carRepository: CarRepository
) {
    val filterFuelType = ArrayList<FuelResponse.Fuel>()
}