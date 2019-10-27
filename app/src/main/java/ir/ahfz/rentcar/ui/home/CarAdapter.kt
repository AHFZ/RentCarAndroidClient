package ir.ahfz.rentcar.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ahfz.rentcar.MyApplication
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.model.CarResponse
import ir.ahfz.rentcar.ui.detail.CarDetailActivity
import kotlinx.android.synthetic.main.item_car.view.*
import org.koin.core.Koin
import org.koin.core.KoinApplication

class CarAdapter : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private val cars = ArrayList<CarResponse.Car>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false))

    override fun getItemCount(): Int {
        return cars.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        Glide.with(holder.itemView)
            .load("${MyApplication.base_url}/images/${car.img}")
            .centerCrop()
            .placeholder(R.drawable.background_rounded_colored)
            .into(holder.itemView.carImageView);
        holder.itemView.brandTextView.text = car.make
        holder.itemView.modelTextView.text = car.model
        holder.itemView.priceTextView.text =
            String.format(
                holder.itemView.context.getString(R.string.price), car.pricePerDay
            )
    }

    fun addCarList(cars: List<CarResponse.Car>) {
        this.cars.addAll(cars)
        notifyDataSetChanged()
    }

    fun setCarList(it: List<CarResponse.Car>) {
        this.cars.clear()
        addCarList(it)
    }

    inner class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                view.context.startActivity(
                    Intent(view.context, CarDetailActivity::class.java)
                        .putExtra(CarDetailActivity.CAR_DATA, cars[adapterPosition])
                )
            }
        }
    }
}