package ir.ahfz.rentcar.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.net.URL

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get() as URL)
            .client(get())
            .build()
    }

    factory { URL("") }

    single {
        OkHttpClient.Builder()
            .build()
    }
}