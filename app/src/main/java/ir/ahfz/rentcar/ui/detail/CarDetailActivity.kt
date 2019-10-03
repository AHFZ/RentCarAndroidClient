package ir.ahfz.rentcar.ui.detail

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.bumptech.glide.Glide
import ir.ahfz.rentcar.MyApplication
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.network.model.CarResponse
import kotlinx.android.synthetic.main.activity_car_detail.*

class CarDetailActivity : AppCompatActivity(R.layout.activity_car_detail) {

    private val adapter = CarSpecAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        val car = intent.getParcelableExtra<CarResponse.Car>(CAR_DATA)
        /*
            Toolbar
         */
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Car Specification"
        tvCarMake.text = car.make
        tvCarModel.text = car.model
        Glide
            .with(this)
            .load("${MyApplication.base_url}/images/${car.img}")
            .into(ivCar)
        listCarSpec.apply {
            layoutManager =
                LinearLayoutManager(this@CarDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = this@CarDetailActivity.adapter.also {
                it.setCar(car)
            }
          //  LinearSnapHelper().attachToRecyclerView(this)
        }

    }

    companion object {
        const val CAR_DATA = "car"
    }
}
