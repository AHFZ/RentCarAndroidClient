package ir.ahfz.rentcar

import android.app.Application
import ir.ahfz.rentcar.di.databaseModule
import ir.ahfz.rentcar.di.repositoryModule
import ir.ahfz.rentcar.di.retrofitModule
import ir.ahfz.rentcar.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * todo -> Remove Hard coded code!! :)
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(retrofitModule, repositoryModule, viewModelModule, databaseModule))
        }
    }

    companion object {
        var base_url = "http://192.168.1.100"
    }
}