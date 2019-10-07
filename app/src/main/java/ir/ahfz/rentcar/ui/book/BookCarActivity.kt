package ir.ahfz.rentcar.ui.book

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ahfz.rentcar.R
import kotlinx.android.synthetic.main.activity_book_car_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookCarActivity : AppCompatActivity(R.layout.activity_book_car) {

    private val bookCarViewModel = viewModel<BookCarViewModel>()
    private val optionAdapter = OptionAdapter()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun init() {

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        /*
            Config listOption RecyclerView
         */
        listOption.apply {
            layoutManager = LinearLayoutManager(this@BookCarActivity)
            adapter = optionAdapter
        }

        etTo.setOnClickListener {
            DatePickerDialog(this).apply {
                setOnDateSetListener { view, year, month, dayOfMonth ->
                    (it as EditText).text?.clear()
                    it.text?.append("$year/$month/$dayOfMonth")
                }
                show()
            }
        }
        etFrom.setOnClickListener {
            DatePickerDialog(this).apply {
                setOnDateSetListener { view, year, month, dayOfMonth ->
                    (it as EditText).text?.clear()
                    it.text?.append("$year/$month/$dayOfMonth")
                }
                show()
            }
        }
        bookCarViewModel.value.extraResponseLiveData.observe(this, Observer {
            optionAdapter.setData(it)
        })

        bookCarViewModel.value.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}