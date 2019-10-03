package ir.ahfz.rentcar.ui.profile

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ahfz.rentcar.R
import kotlinx.android.synthetic.main.activity_profile_detail.*
import kotlinx.android.synthetic.main.activity_profile_detail_content.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileDetailActivity : AppCompatActivity(R.layout.activity_profile_detail) {

    private var viewModel = viewModel<MyReservationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                if (scrollY > 20) {
                    appBarLayout.elevation = 16F
                } else
                    appBarLayout.elevation = 0F
        }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listMyReservation.apply {
            layoutManager = LinearLayoutManager(this@ProfileDetailActivity)
                .apply {
                    isAutoMeasureEnabled = true
                }
            isNestedScrollingEnabled = false
            adapter = MyReservationAdapter()
        }

        viewModel.value.reservationLiveData.observe(this, Observer {
            if (listMyReservation != null && listMyReservation.adapter != null) {
                (listMyReservation.adapter as MyReservationAdapter).addAll(it)
            }
        })
        viewModel.value.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}
