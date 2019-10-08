package ir.ahfz.rentcar.io.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ir.ahfz.rentcar.io.model.CarResponse

@Dao
interface CarDao : BaseDao<CarResponse.Car> {

    @Query("select * from Car")
    fun getAllCar(): List<CarResponse.Car>

    @Query("select * from Car")
    fun getAllCarLive(): LiveData<List<CarResponse.Car>>
}