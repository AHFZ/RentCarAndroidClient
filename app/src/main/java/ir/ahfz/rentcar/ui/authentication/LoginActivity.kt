package ir.ahfz.rentcar.ui.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.Utils.setLightStatusBar
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity(), Observer<String?> {

    private val loginViewModel = viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setLightStatusBar()
        setData()
        loginViewModel.value.successLogin.observe(this, this)
        loginViewModel.value.failedLogin.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        buttonLogin.setOnClickListener {
            val password = passwordEditText.text
            val email = emailEditText.text
            if (password.isNullOrBlank() && email.isNullOrBlank()) {
                Toast.makeText(this, R.string.err_empty_email_password, Toast.LENGTH_SHORT).show()
            } else if (email.toString().contains("\\w+@\\w+.\\w+")) {
                Toast.makeText(this, R.string.err_invalid_email, Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.value.login(email.toString(), password.toString())
            }
        }
    }

    private fun setData() {

        val password = intent.getStringExtra("password")
        val email = intent.getStringExtra("email")
        if (password != null && email != null) {
            passwordEditText.text?.clear()
            passwordEditText.text?.append(password)
            emailEditText.text?.clear()
            emailEditText.text?.append(email)
        }
    }

    override fun onChanged(it: String?) {
        setResult(Activity.RESULT_OK)
        Toast.makeText(this, "${getString(R.string.welcome_back)}, $it", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onStop() {
        super.onStop()
        if (isDestroyed)
            setResult(Activity.RESULT_CANCELED)
    }

    fun onClick(view: View) {
        val targetActivity = when (view.id) {
            R.id.forgotPasswordTextView -> ResetPasswordActivity::class.java
            R.id.agreementTextView -> null
            R.id.jointUsTextView -> RegistrationActivity::class.java
            else -> null
        }
        if (targetActivity != null) {
            startActivity(Intent(this, targetActivity))
            finish()
        } else
            Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show()
    }

}
