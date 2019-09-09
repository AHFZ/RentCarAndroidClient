package ir.ahfz.rentcar.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.ahfz.rentcar.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPasswordActivity : AppCompatActivity() {

    val resetPasswordViewModel = viewModel<ResetPasswordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
    }
}
