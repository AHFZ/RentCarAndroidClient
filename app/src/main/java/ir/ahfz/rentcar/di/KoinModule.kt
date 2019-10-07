package ir.ahfz.rentcar.di

import com.google.gson.Gson
import ir.ahfz.rentcar.Utils.JavaNetCookieJar
import ir.ahfz.rentcar.io.network.webservice.AuthenticationWebservice
import ir.ahfz.rentcar.io.network.webservice.PrivateAccessWebservice
import ir.ahfz.rentcar.io.network.webservice.PublicAccessWebservice
import ir.ahfz.rentcar.repository.AuthenticationRepository
import ir.ahfz.rentcar.repository.PrivateAccessRepository
import ir.ahfz.rentcar.repository.PublicAccessRepository
import ir.ahfz.rentcar.repository.SharePreferencesRepository
import ir.ahfz.rentcar.ui.authentication.LoginViewModel
import ir.ahfz.rentcar.ui.authentication.RegistrationViewModel
import ir.ahfz.rentcar.ui.authentication.ResetPasswordViewModel
import ir.ahfz.rentcar.ui.book.BookCarViewModel
import ir.ahfz.rentcar.ui.home.HomeViewModel
import ir.ahfz.rentcar.ui.profile.MyReservationViewModel
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.net.CookiePolicy.ACCEPT_ALL


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
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .cookieJar(JavaNetCookieJar(get<CookieManager>()))
            .build()
    }
    single {
        return@single CookieManager().apply {
            setCookiePolicy(ACCEPT_ALL)
        }
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
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { ResetPasswordViewModel() }
    viewModel { MyReservationViewModel(get(), get()) }
    viewModel { BookCarViewModel(get()) }
}