package ir.ahfz.rentcar.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.model.CarResponse
import kotlinx.android.synthetic.main.item_car_specification.view.*

class CarSpecAdapter : RecyclerView.Adapter<CarSpecAdapter.ViewHolder>() {

    private var car: CarResponse.Car? = null
    private val icons = arrayOf(
        R.drawable.ic_currency_usd,
        R.drawable.ic_car_transmission,
        R.drawable.ic_gas_station,
        R.drawable.ic_car_seat,
        R.drawable.ic_car_info,
        R.drawable.ic_format_color_fill,
        R.drawable.ic_calendar
    )
    private val titles = arrayOf(
        R.string.price_per_day,
        R.string.gearbox,
        R.string.fuel,
        R.string.seat,
        R.string.type,
        R.string.color,
        R.string.year_old
    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_car_specification, viewGroup, false)
    )

    /*
        Number of spec ( it is totally static)
     */
    override fun getItemCount(): Int = icons.size

    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {
        val car = this.car ?: return
        viewHolder.itemView.tvSpec.text = when (pos) {
            0 -> {
                String.format(viewHolder.getString(R.string.price), car.pricePerDay)
            }
            1 -> {
                viewHolder.getString(if (car.isAutomatic == "1") R.string.automatic else R.string.manual)
            }
            2 -> {
                car.fuel
            }
            3 -> {
                car.capacity
            }
            4 -> {
                car.type // toString
            }
            5 -> {
                if (car.color.isNullOrBlank()) viewHolder.getString(R.string.white) else car.color
            }
            6 -> {
                car.year
            }
            else -> "na"
        }
        Glide.with(viewHolder.itemView)
            .load(icons[pos])
            .into(viewHolder.itemView.ivSpec)
        viewHolder.itemView.tvSpecTitle.text = viewHolder.getString(titles[pos])
    }

    fun setCar(car: CarResponse.Car) {
        this.car = car
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun getString(resId: Int): String = itemView.context.getString(resId)
    }
}