package ir.ahfz.rentcar

import android.app.Application
import ir.ahfz.rentcar.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(retrofitModule))
        }
    }
}