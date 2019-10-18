package ir.ahfz.rentcar.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ir.ahfz.rentcar.MyApplication
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.model.CarResponse
import ir.ahfz.rentcar.ui.book.BookCarActivity
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
        tvCarMake.text = car!!.make
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

    public companion object {
        public const val CAR_DATA = "car"
    }

    fun bookIt(view: View) {
        startActivity(
            Intent(this, BookCarActivity::class.java)
                .putExtra(
                    CAR_DATA, intent.getParcelableExtra<CarResponse.Car>(CAR_DATA)
                )
        )
        finish()
    }
}
