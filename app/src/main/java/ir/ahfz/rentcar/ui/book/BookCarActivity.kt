package ir.ahfz.rentcar.ui.book

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.ui.detail.CarDetailActivity
import kotlinx.android.synthetic.main.activity_book_car.*
import kotlinx.android.synthetic.main.activity_book_car_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookCarActivity : AppCompatActivity(R.layout.activity_book_car) {

    private val bookCarViewModel = viewModel<BookCarViewModel>()
    private val optionAdapter = OptionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        bookCarViewModel.value.car = intent.getParcelableExtra(CarDetailActivity.CAR_DATA)!!
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        /*
            Config listOption RecyclerView
         */
        listOption.apply {
            layoutManager = LinearLayoutManager(this@BookCarActivity)
            adapter = optionAdapter
        }

        val viewModel = bookCarViewModel.value

        etTo.setOnClickListener {
            DatePickerDialog(this).apply {
                setOnDateSetListener { view, year, month, dayOfMonth ->
                    viewModel.setReturnDate(year, month, dayOfMonth)
                }
                show()
            }
        }
        etFrom.setOnClickListener {
            DatePickerDialog(this).apply {
                setOnDateSetListener { view, year, month, dayOfMonth ->
                    viewModel.setPickupDate(year, month, dayOfMonth)
                }
                show()
            }
        }
        etLocationFrom.setOnClickListener {
            if (bookCarViewModel.value.cities.isNotEmpty())
                BottomSheetItemPicker(
                    bookCarViewModel.value.cities,
                    "Pickup Location"
                ) {
                    bookCarViewModel.value.pickupLocation = it
                    etLocationFrom.text?.apply {
                        clear()
                        append(it.city)
                    }
                }
                    .show(supportFragmentManager, "a")
            else
                Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        etLocationTo.setOnClickListener {
            if (bookCarViewModel.value.cities.isNotEmpty())
                BottomSheetItemPicker(
                    bookCarViewModel.value.cities,
                    "Pickup Location"
                )
                {
                    bookCarViewModel.value.returnLocation = it
                    etLocationTo.text?.apply {
                        clear()
                        append(it.city)
                    }
                }
                    .show(supportFragmentManager, "a")
            else
                Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        viewModel.pickupDate.observe(this, Observer {
            etFrom.text!!.clear()
            etFrom.text!!.append(it)
        })
        viewModel.returnDate.observe(this, Observer {
            etTo.text!!.clear()
            etTo.text!!.append(it)
        })
        viewModel.extraResponseLiveData.observe(this, Observer {
            optionAdapter.setData(it)
        })
        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    fun bookIt(view: View) {
        bookCarViewModel.value.bookIt(this)
    }
}