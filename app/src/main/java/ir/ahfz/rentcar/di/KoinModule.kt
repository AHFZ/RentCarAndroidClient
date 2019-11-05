package ir.ahfz.rentcar.di

import android.content.Context
import androidx.room.Room
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.Gson
import ir.ahfz.rentcar.io.database.DataBase
import ir.ahfz.rentcar.io.network.webservice.AuthenticationWebservice
import ir.ahfz.rentcar.io.network.webservice.PrivateAccessWebservice
import ir.ahfz.rentcar.io.network.webservice.PublicAccessWebservice
import ir.ahfz.rentcar.repository.*
import ir.ahfz.rentcar.ui.authentication.LoginViewModel
import ir.ahfz.rentcar.ui.authentication.RegistrationViewModel
import ir.ahfz.rentcar.ui.authentication.ResetPasswordViewModel
import ir.ahfz.rentcar.ui.book.BookCarViewModel
import ir.ahfz.rentcar.ui.home.HomeViewModel
import ir.ahfz.rentcar.ui.profile.MyReservationViewModel
import ir.ahfz.rentcar.ui.search.SearchCarViewModel
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<SharePreferencesRepository>().getServerURL())
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single {
        OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .cookieJar(get<PersistentCookieJar>())
            .build()
    }
    single {
        return@single PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(get<Context>())
        )
    }
    single { get<Retrofit>().create(AuthenticationWebservice::class.java) }
    single { get<Retrofit>().create(PublicAccessWebservice::class.java) }
    single { get<Retrofit>().create(PrivateAccessWebservice::class.java) }
    single { GsonConverterFactory.create(get()) }
    single { Gson() }
}

val repositoryModule = module {

    single { AuthenticationRepository(get()) }
    single { PublicAccessRepository(get()) }
    single { SharePreferencesRepository(get()) }
    single { PrivateAccessRepository(get()) }
    single { CarRepository(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), DataBase::class.java, "rentalCar")
            .allowMainThreadQueries()
            .build()
    }
    single { get<DataBase>().provideCarDao() }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { ResetPasswordViewModel() }
    viewModel { MyReservationViewModel(get(), get()) }
    viewModel { BookCarViewModel(get(), get(), get(), get()) }
    viewModel { SearchCarViewModel(get(), get()) }
}