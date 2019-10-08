package ir.ahfz.rentcar.repository

import androidx.lifecycle.LiveData
import ir.ahfz.rentcar.io.database.CarDao
import ir.ahfz.rentcar.io.model.CarResponse

class CarRepository(private val carDao: CarDao) {

    fun getAllCarsLive(): LiveData<List<CarResponse.Car>> {
        return carDao.getAllCarLive()
    }

    fun getAllCars(): List<CarResponse.Car> {
        return carDao.getAllCar()
    }

    fun addCarIgnoreConflict(cars: List<CarResponse.Car>) {
        carDao.insert(cars)
    }
}