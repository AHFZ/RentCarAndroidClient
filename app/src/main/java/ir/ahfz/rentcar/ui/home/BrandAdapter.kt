package ir.ahfz.rentcar.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.network.model.CarBrand
import ir.ahfz.rentcar.io.network.model.MakeResponse
import kotlinx.android.synthetic.main.item_brand.view.*

class BrandAdapter : RecyclerView.Adapter<BrandAdapter.ViewHolder>() {

    val brandList = ArrayList<MakeResponse.Make>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_brand, parent, false))

    override fun getItemCount(): Int = brandList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = brandList[position]
        try {
            val brand = CarBrand.valueOf(model.make.toString())
            holder.itemView.backgroundLayout.setBackgroundColor(brand.color)
            holder.itemView.brandTextView.text = holder.itemView.context.getString(brand.nameResId)
            Glide.with(holder.itemView.brandIdLogoImageView)
                .load(brand.drawable)
               // .apply(RequestOptions.circleCropTransform())
                .into(holder.itemView.brandIdLogoImageView)
        } catch (e: Exception) {
        }
    }

    fun addBrandList(it: List<MakeResponse.Make>?) {
        brandList.addAll(it!!)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}