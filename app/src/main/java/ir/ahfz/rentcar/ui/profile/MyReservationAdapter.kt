package ir.ahfz.rentcar.ui.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.model.ReservationHistoryResponse
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
        holder.itemView.tvModel.text = reservation.model
        holder.itemView.tvBrand.text = reservation.make
        val backgroundColor = when {
            reservation.isCompleted == 1 -> Color.parseColor("#03a9f4")
            reservation.isPaid == 1 -> Color.parseColor("#4caf50")
            reservation.isPending == 1 -> Color.parseColor("#ff9800")
            else -> Color.parseColor("#f44336")
        }
        holder.itemView.ivStatus.background = ColorDrawable(backgroundColor)
    }

    fun addAll(it: List<ReservationHistoryResponse.Reservation>?) {
        if (it != null) {
            reservationList.addAll(it.reversed())
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
