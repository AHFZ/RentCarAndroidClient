package ir.ahfz.rentcar.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.ui.home.CarAdapter
import kotlinx.android.synthetic.main.activity_car_list.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCarActivity : AppCompatActivity(R.layout.activity_car_list), TextWatcher {

    private val viewModel: SearchCarViewModel by viewModel()
    private val adapter = CarAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayoutFilter.setOnClickListener {
            TransitionManager.beginDelayedTransition(rootLayout)
            fragmentContainer.visibility = View.VISIBLE
        }
        listCar.apply {
            layoutManager = LinearLayoutManager(this@SearchCarActivity)
            adapter = this@SearchCarActivity.adapter
        }
        etSearch.addTextChangedListener(this)
        viewModel.carList.observe(this, Observer {
            adapter.setCarList(it)
        })
        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onBackPressed() {
        if (fragmentContainer.visibility == View.VISIBLE) {
            TransitionManager.beginDelayedTransition(rootLayout)
            fragmentContainer.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }

    override fun afterTextChanged(s: Editable?) {
        viewModel.filter(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}
