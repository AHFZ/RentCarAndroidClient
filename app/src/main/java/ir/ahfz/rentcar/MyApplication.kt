package ir.ahfz.rentcar

import android.app.Application
import ir.ahfz.rentcar.di.databaseModule
import ir.ahfz.rentcar.di.repositoryModule
import ir.ahfz.rentcar.di.retrofitModule
import ir.ahfz.rentcar.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 *
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
        const val base_url = "http://192.168.1.100"
    }
}
/*

        Reserve car+
        Search and filter

 */