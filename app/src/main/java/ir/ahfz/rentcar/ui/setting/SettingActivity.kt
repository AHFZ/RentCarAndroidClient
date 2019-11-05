package ir.ahfz.rentcar.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.ahfz.rentcar.MyApplication
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.repository.SharePreferencesRepository
import kotlinx.android.synthetic.main.activity_setting.*
import okhttp3.HttpUrl
import org.koin.android.ext.android.inject
import retrofit2.Retrofit


class SettingActivity : AppCompatActivity(R.layout.activity_setting) {

    private val retrofit: Retrofit by inject()
    private val repository: SharePreferencesRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
    }

    override fun onStop() {
        super.onStop()
        repository.setServerUrl(etServerIp.text.toString())
        val field = Retrofit::class.java.getDeclaredField("baseUrl")
        field.isAccessible = true
        val newHttpUrl = HttpUrl.parse(MyApplication.base_url)
        field.set(retrofit, newHttpUrl)
    }
}
