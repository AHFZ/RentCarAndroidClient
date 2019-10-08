package ir.ahfz.rentcar.ui.profile

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.model.AuthenticatedResponse
import kotlinx.android.synthetic.main.activity_profile_detail.*
import kotlinx.android.synthetic.main.activity_profile_detail_content.*
import kotlinx.android.synthetic.main.activity_profile_detail_content.listMyReservation
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileDetailActivity : AppCompatActivity(R.layout.activity_profile_detail) {

    private var viewModel = viewModel<MyReservationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getParcelableExtra<AuthenticatedResponse>("user")
        viewModel.value.profileLiveData.postValue(user)
        nestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                if (scrollY > 20) {
                    appBarLayout.elevation = 8 * resources.displayMetrics.density
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

        tvLocation.setOnClickListener {
            tvLocation.text = viewModel.value.profileLiveData.value?.address
        }

        viewModel.value.reservationLiveData.observe(this, Observer {
            if (listMyReservation != null && listMyReservation.adapter != null && it.isNotEmpty()) {
                (listMyReservation.adapter as MyReservationAdapter).addAll(it)
            } else {
                layoutInflater.inflate(R.layout.item_no_reservation, linearLayout, true)
            }
        })
        viewModel.value.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            finish()
        })
        viewModel.value.profileLiveData.observe(this, Observer {
            tvProfileName.text = it.name
            tvLocation.text = it.city
            tvPhoneNumber.text = it.phone
            tvEmail.text = it.email
        })
    }
}
