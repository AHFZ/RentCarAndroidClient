package ir.ahfz.rentcar.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ahfz.rentcar.R
import kotlinx.android.synthetic.main.fragment_my_reservation.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyReservationFragment : Fragment(R.layout.fragment_my_reservation) {

    private var viewModel = viewModel<MyReservationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listMyReservation.layoutManager = LinearLayoutManager(context)
        listMyReservation.adapter = MyReservationAdapter()
        listMyReservation.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )

        viewModel.value.reservationLiveData.observe(this, Observer {
            if (listMyReservation != null && listMyReservation.adapter != null && it.isNotEmpty()) {
                (listMyReservation.adapter as MyReservationAdapter).addAll(it)
            } else {
                activity!!.layoutInflater.inflate(R.layout.item_no_reservation, null, false)
            }
        })
        viewModel.value.errorLiveData.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }
}