package ir.ahfz.rentcar.di

import com.google.gson.Gson
import ir.ahfz.rentcar.io.network.webservice.PublicAccessWebservice
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get() as URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    factory { URL("http://192.168.2.180") }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
    single {
        get<Retrofit>().create(PublicAccessWebservice::class.java)
    }
    single { GsonConverterFactory.create(get()) }
    single { Gson() }
}