package ir.ahfz.rentcar.repository

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import ir.ahfz.rentcar.io.database.CarDao
import ir.ahfz.rentcar.io.model.CarResponse
import java.util.*

class CarRepository(private val carDao: CarDao) {

    fun getAllCarsLive(): LiveData<List<CarResponse.Car>> {
        return carDao.getAllCarLive()
    }

    suspend fun getAllCars(): List<CarResponse.Car> {
        return carDao.getAllCar()
    }

    fun addCarIgnoreConflict(cars: List<CarResponse.Car>) {
        carDao.insert(cars)
    }

    suspend fun getCarsByFilter(
        search: String,
        filterCarBrand: ArrayList<String>,
        filterCarType: ArrayList<Int>,
        filterFuelType: ArrayList<String>,
        filterTransmission: Int,
        orderBy: String,
        ascending: Boolean
    ): List<CarResponse.Car> {
        val stringBuilder = StringBuilder()
        val createBrandQuery = createBrandQuery(filterCarBrand, false)
        val createCarTypeQuery = createCarTypeQuery(filterCarType, createBrandQuery.isNotEmpty())
        val createFuelTypeQuery = createFuelTypeQuery(
            filterFuelType,
            createBrandQuery.isNotEmpty() ||
                    createCarTypeQuery.isNotEmpty()
        )
        val createTransmissionQuery = createTransmissionQuery(
            filterTransmission,
            createBrandQuery.isNotEmpty() ||
                    createCarTypeQuery.isNotEmpty() ||
                    createFuelTypeQuery.isNotEmpty()
        )
        stringBuilder
            .append("select * from Car where ")
            .append(createBrandQuery)
            .append(createCarTypeQuery)
            .append(createFuelTypeQuery)
            .append(createTransmissionQuery)
            .append(createSearchQuery(search, !stringBuilder.endsWith("where ")))
            .append(createOrderQuery(orderBy, ascending))
        return carDao.customQuery(
            SimpleSQLiteQuery(stringBuilder.toString())
        )
    }

    private fun createSearchQuery(search: String, flag: Boolean): String {
        val stringBuilder = StringBuilder()
        if (search.isEmpty()) return stringBuilder.toString()
        if (flag)
            stringBuilder.append(" and ")
        stringBuilder.append(" model like '%$search%'")
        return stringBuilder.toString()
    }

    private fun createOrderQuery(orderBy: String, ascending: Boolean): String {
        val tmp = if (ascending) " asc" else " desc"
        return " order by $orderBy $tmp"
    }

    private fun createTransmissionQuery(filterTransmission: Int, flag: Boolean): String {
        val stringBuilder = StringBuilder()
        if (flag)
            stringBuilder.append(" and ")
        stringBuilder.append(" ( ")
        if (filterTransmission != 0) {
            stringBuilder.append(" isAutomatic == '0' or isAutomatic='1' ")
        } else
            stringBuilder.append(" isAutomatic == '$filterTransmission'")
        stringBuilder.append(" ) ")
        return stringBuilder.toString()
    }

    private fun createFuelTypeQuery(filterFuelType: ArrayList<String>?, flag: Boolean): String {
        filterFuelType ?: return ""
        if (filterFuelType.isEmpty()) return ""
        val stringBuilder = StringBuilder()
        if (flag)
            stringBuilder.append(" and ")
        stringBuilder.append(" ( ")
        val iterator = filterFuelType.iterator()
        do {
            stringBuilder.append("fuel == '${iterator.next()}'")
            if (iterator.hasNext()) {
                stringBuilder.append(" or ")
            }
        } while (iterator.hasNext())
        stringBuilder.append(" ) ")
        return stringBuilder.toString()
    }

    private fun createCarTypeQuery(filterCarType: ArrayList<Int>?, flag: Boolean): String {
        filterCarType ?: return ""
        if (filterCarType.isEmpty()) return ""
        val stringBuilder = StringBuilder()
        if (flag)
            stringBuilder.append(" and ")
        stringBuilder.append(" ( ")
        val iterator = filterCarType.iterator()
        do {
            stringBuilder.append(" classes == '${iterator.next()}'")
            if (iterator.hasNext()) {
                stringBuilder.append(" or ")
            }
        } while (iterator.hasNext())
        stringBuilder.append(" ) ")
        return stringBuilder.toString()
    }

    private fun createBrandQuery(filterCarBrand: ArrayList<String>?, flag: Boolean): String {
        filterCarBrand ?: return ""
        if (filterCarBrand.isEmpty()) return ""
        val stringBuilder = StringBuilder()
        if (flag)
            stringBuilder.append(" and ")
        stringBuilder.append(" ( ")
        val iterator = filterCarBrand.iterator()
        do {
            stringBuilder.append(" make == '${iterator.next()}'")
            if (iterator.hasNext()) {
                stringBuilder.append(" or ")
            }
        } while (iterator.hasNext())
        stringBuilder.append(" ) ")
        return stringBuilder.toString()
    }
}