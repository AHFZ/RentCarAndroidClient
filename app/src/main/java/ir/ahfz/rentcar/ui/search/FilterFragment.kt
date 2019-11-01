package ir.ahfz.rentcar.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ahfz.rentcar.R
import kotlinx.android.synthetic.main.fragment_filter.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FilterFragment : Fragment(R.layout.fragment_filter) {

    private val viewModel: SearchCarViewModel by sharedViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeViewModel()
        initUI()
    }

    private fun initUI() {
        tvFilter.setOnClickListener {
            viewModel.setFilterCarType((listCarType.adapter as CheckableAdapter).getCheckedItem())
            viewModel.setFilterFuel((listFuelType.adapter as CheckableAdapter).getCheckedItem())
            viewModel.setFilterBrand((listBrand.adapter as CheckableAdapter).getCheckedItem())
            viewModel.setFilterTransmission((listTransmission.adapter as CheckableAdapter).getCheckedItem())
            viewModel.filter(null)
            activity?.onBackPressed()
        }

        listBrand.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CheckableAdapter()
        }

        listFuelType.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CheckableAdapter()
        }

        listTransmission.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CheckableAdapter().also {
                val auto = object : ir.ahfz.rentcar.io.model.Checkable {
                    override var isChecked: Boolean = false
                    override fun getTitle(): String? = "Auto"
                }
                val manual = object : ir.ahfz.rentcar.io.model.Checkable {
                    override var isChecked: Boolean = false
                    override fun getTitle(): String? = "Manual"
                }
                it.addItems(arrayListOf(auto, manual))
            }
        }

        listCarType.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CheckableAdapter()
        }
    }

    private fun subscribeViewModel() {
        viewModel.makes.observe(this, Observer {
            val titleAdapter = listBrand.adapter as CheckableAdapter
            titleAdapter.addItems(it.makes!!)
        })
        viewModel.fuels.observe(this, Observer {
            val titleAdapter = listFuelType.adapter as CheckableAdapter
            titleAdapter.addItems(it.fuels!!)
        })
        viewModel.classes.observe(this, Observer {
            val titleAdapter = listCarType.adapter as CheckableAdapter
            titleAdapter.addItems(it.types!!)
        })
    }
}