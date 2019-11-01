package ir.ahfz.rentcar.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.ahfz.rentcar.R
import ir.ahfz.rentcar.io.model.Checkable
import kotlinx.android.synthetic.main.item_simple_selectable_string.view.*

class CheckableAdapter : RecyclerView.Adapter<CheckableAdapter.ViewHolder>() {

    private val checkableItems = ArrayList<Checkable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_simple_selectable_string,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = checkableItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = checkableItems[position]
        holder.itemView.text.text = item.getTitle()
        if (item.isChecked) {
            holder.itemView.setBackgroundResource(R.drawable.border_rounded_accent)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.border_rounded_gray)
        }
    }

    fun addItems(checkableItems: List<Checkable>) {
        this.checkableItems.addAll(checkableItems)
        notifyDataSetChanged()
    }

    fun getCheckedItem(): List<Checkable> {
        val checkedItem = ArrayList<Checkable>()
        checkableItems.forEach {
            if (it.isChecked)
                checkedItem.add(it)
        }
        return checkedItem
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                val item = checkableItems[adapterPosition]
                item.isChecked = !item.isChecked
                notifyItemChanged(adapterPosition)
            }
        }
    }
}