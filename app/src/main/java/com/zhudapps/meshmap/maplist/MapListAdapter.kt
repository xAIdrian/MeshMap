package com.zhudapps.meshmap.maplist


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhudapps.meshmap.R
import com.zhudapps.meshmap.model.MapPin
import kotlinx.android.synthetic.main.fragment_item.view.*

class MapListAdapter(
    private var values: List<MapPin>
) : RecyclerView.Adapter<MapListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.mIdView.text = "${item.latitude}, ${item.longitude}"
        holder.mContentView.text = item.description
    }

    override fun getItemCount(): Int = values.size
    fun update(pinList: ArrayList<MapPin>) {
        values = pinList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
