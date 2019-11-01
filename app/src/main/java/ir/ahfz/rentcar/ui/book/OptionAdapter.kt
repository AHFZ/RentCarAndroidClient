package ir.ahfz.rentcar.ui.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.model.ExtraResponse
import kotlinx.android.synthetic.main.item_book_option.view.*

class OptionAdapter : RecyclerView.Adapter<OptionAdapter.ViewHolder>() {

    private val extras = ArrayList<ExtraResponse.Extra>()
    private var isSelected = ArrayList<Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book_option,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = extras.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvOptionTitle.text = extras[position].description
        holder.itemView.ivWantIt.isChecked = isSelected[position]
    }

    fun setData(extraResponse: ExtraResponse) {
        extras.addAll(extraResponse.extras!!)
        isSelected.removeAll(isSelected)
        extras.forEach {
            isSelected.add(false)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                val isChecked = !isSelected[adapterPosition]
                isSelected.set(adapterPosition, isChecked)
                it.ivWantIt.isChecked = isChecked
                notifyItemChanged(adapterPosition)
            }
        }
    }
}
