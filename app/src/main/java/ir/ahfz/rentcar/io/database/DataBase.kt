package ir.ahfz.rentcar.io.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.ahfz.rentcar.io.model.CarResponse
import ir.ahfz.rentcar.io.model.MakeResponse

@Database(
    entities = [CarResponse.Car::class, MakeResponse.Make::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun provideCarDao(): CarDao

}