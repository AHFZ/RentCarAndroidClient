package ir.ahfz.rentcar.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.ui.authentication.LoginActivity
import ir.ahfz.rentcar.ui.profile.ProfileDetailActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val RQ = 1
    private val homeViewModel = viewModel<HomeViewModel>()
    private val adapter = CarAdapter()
    private val brandAdapter = BrandAdapter()
    private lateinit var waitDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        waitDialog = AlertDialog.Builder(this)
            .setMessage(getString(R.string.please_wait))
            .setCancelable(false)
            .create()

        nestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                if (scrollY > 20) {
                    appBarLayout.elevation = 8F

                } else {
                    appBarLayout.elevation = 0F
                }
        }

        textViewProfile.setOnClickListener {
            val value = homeViewModel.value.userAuthLiveData.value
            if (value != null)
                startActivity(
                    (Intent(this, ProfileDetailActivity::class.java)).putExtra(
                        "user",
                        value
                    )
                )
            else
                startActivityForResult(Intent(this, LoginActivity::class.java), RQ)
        }

        list_brand.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        list_brand.adapter = brandAdapter

        list_car.layoutManager = LinearLayoutManager(this)
        list_car.adapter = adapter

        homeViewModel.value.carListLiveData.observe(this, Observer {
            adapter.addCarList(it)
        })
        homeViewModel.value.makeListLiveData.observe(this, Observer {
            brandAdapter.addBrandList(it)
        })
        homeViewModel.value.userAuthLiveData.observe(this, Observer {
            textViewProfile.text = it?.name ?: getString(R.string.login)
            ivLogout.visibility = if (it != null) View.VISIBLE else View.INVISIBLE
            waitDialog.dismiss()
        })
        homeViewModel.value.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            waitDialog.dismiss()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == RQ) {
            homeViewModel.value.userAuthLiveData.postValue(data?.getParcelableExtra("user"))
        }
    }

    fun logout(view: View) {
        homeViewModel.value.logout()
        waitDialog.show()
    }
}
