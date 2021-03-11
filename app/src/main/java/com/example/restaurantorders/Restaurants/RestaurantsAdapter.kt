package com.example.restaurantorders.Restaurants

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.restaurantorders.R
import java.util.*


class RestaurantsAdapter(
    private val context: Context,
    private val layout: Int,
    private val list: ArrayList<RestaurantItemModel>
) :
    BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(i: Int): Any {
        return list[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    private inner class ViewHolder {
        var txtItem: TextView? = null
        var txtItemDescription: TextView? = null
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View? {
        var item = view
        var holder = ViewHolder()
        if (item == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            item = inflater.inflate(layout, null)
            holder.txtItem = item.findViewById(R.id.tvItem)
            holder.txtItemDescription = item.findViewById(R.id.tvDescription)
            item.tag = holder
        } else holder = item.tag as ViewHolder
        val model = list[i]
        holder.txtItem?.text = model.menuItem
        holder.txtItemDescription?.text = model.itemDescription
        return item
    }
}