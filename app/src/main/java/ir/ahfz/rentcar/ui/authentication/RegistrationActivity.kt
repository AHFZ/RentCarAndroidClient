package ir.ahfz.rentcar.ui.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.Utils.setLightStatusBar
import ir.ahfz.rentcar.io.model.AuthenticatedResponse
import kotlinx.android.synthetic.main.activity_registration.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationActivity : AppCompatActivity(), Observer<AuthenticatedResponse?> {

    private val viewModel = viewModel<RegistrationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setLightStatusBar()

        registerButton.setOnClickListener {
            viewModel.value.requestForRegistration(
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString(),
                passwordConfirmationEditText.text.toString(),
                cityEditText.text.toString(),
                addressEditText.text.toString(),
                phoneEditText.text.toString()
            )
        }

        viewModel.value.registrationResult.observe(this, this)
        viewModel.value.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

    override fun onChanged(t: AuthenticatedResponse?) {
        Toast.makeText(this, "Hi ${t?.name}", Toast.LENGTH_SHORT).show()
        setResult(Activity.RESULT_OK, Intent().putExtra("user", t))
        finish()
    }
}
