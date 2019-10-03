package ir.ahfz.rentcar.ui.authentication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.Utils.setLightStatusBar
import kotlinx.android.synthetic.main.activity_registration.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationActivity : AppCompatActivity(), Observer<String?> {

    private val viewModel = viewModel<RegistrationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setLightStatusBar()

        registerButton.setOnClickListener {
            /*if (viewModel.isInitialized()) {
                Log.e(javaClass.name, "${ResetPasswordViewModel::class.java.simpleName} is not initialized.")
                return@setOnClickListener
            }
            */viewModel.value.requestForRegistration(nameEditText.text.toString(),
                    emailEditText.text.toString(),
                    passwordEditText.text.toString(),
                    passwordConfirmationEditText.text.toString(),
                    cityEditText.text.toString(),
                    addressEditText.text.toString(),
                    phoneEditText.text.toString())
        }

        viewModel.value.registrationResult.observe(this, this)

    }

    override fun onChanged(t: String?) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show()
    }
}
