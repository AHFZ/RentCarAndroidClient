package ir.ahfz.rentcar.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.ui.profile.ProfileDetailActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel = viewModel<HomeViewModel>()

    val adapter = CarAdapter()
    val brandAdapter = BrandAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                if (scrollY > 20) {
                    appBarLayout.elevation = 8F

                } else {
                    appBarLayout.elevation = 0F
                }
        }

        textViewProfile.setOnClickListener {
            startActivity((Intent(this, ProfileDetailActivity::class.java)))
        }

        list_brand.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        list_brand.adapter = brandAdapter
        list_car.layoutManager = LinearLayoutManager(this)
        list_car.adapter = adapter

        val linearSnapHelper = LinearSnapHelper()
        linearSnapHelper.attachToRecyclerView(list_brand)

        homeViewModel.value.carListLiveData.observe(this, Observer {
            adapter.addCarList(it)
        })
        homeViewModel.value.makeListLiveData.observe(this, Observer {
            brandAdapter.addBrandList(it)
        })

        homeViewModel.value.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}
