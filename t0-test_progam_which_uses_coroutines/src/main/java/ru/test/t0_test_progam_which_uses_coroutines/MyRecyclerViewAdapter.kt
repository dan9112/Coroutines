package ru.test.t0_test_progam_which_uses_coroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView


class MyRecyclerViewAdapter(private var values: ArrayList<String>) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater.inflate(R.layout.rw0_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(values[position])
    }

    fun updateAdapter(listItems: List<String>) {
        values.clear()
        values.addAll(listItems)
        notifyDataSetChanged()//сообщения адаптеру об изменении данных для перезапуска
    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val chBox = itemView.findViewById<CheckBox>(R.id.chBox)

        fun setData(time: String) {
            chBox.text = time

            itemView.setOnClickListener { chBox.isChecked = !chBox.isChecked }
        }
    }
}