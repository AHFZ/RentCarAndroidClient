package ir.ahfz.rentcar.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.ahfz.rentcar.R
import kotlinx.android.synthetic.main.fragment_item_picker.*

class BottomSheetItemPicker<T>(

    private val item: List<T>,
    private val title: String,
    private val onClick: (item: T) -> Unit
) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.fragment_item_picker, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = title
        listItem.adapter =
            ArrayAdapter<T>(context!!, android.R.layout.simple_list_item_1, item)
        listItem.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                onClick(item[position])
                dismiss()
            }
    }
}