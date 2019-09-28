package ir.ahfz.rentcar.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.network.model.ReservationHistoryResponse
import kotlinx.android.synthetic.main.item_reservation.view.*

class MyReservationAdapter : RecyclerView.Adapter<MyReservationAdapter.ViewHolder>() {

    private val reservationList = ArrayList<ReservationHistoryResponse.Reservation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_reservation
            , parent,
            false
        )
    )

    override fun getItemCount(): Int = reservationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = reservationList[position]
        holder.itemView.text.text = reservation.toString()
    }

    fun addAll(it: List<ReservationHistoryResponse.Reservation>?) {
        if (it != null) {
            reservationList.addAll(it)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
