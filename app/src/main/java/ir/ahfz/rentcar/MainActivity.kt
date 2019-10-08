package ir.ahfz.rentcar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.ahfz.rentcar.io.network.webservice.PublicAccessWebservice
import ir.ahfz.rentcar.ui.authentication.LoginActivity
import ir.ahfz.rentcar.ui.home.HomeActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val webservice: PublicAccessWebservice by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
