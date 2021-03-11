package com.example.restaurantorders.Orders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getString
import com.example.restaurantorders.Models.Order
import com.example.restaurantorders.R
import java.util.ArrayList

class OrdersAdapter(
    private val context: Context,
    private val layout: Int,
    private val orderList: ArrayList<Order>
    ) :
    BaseAdapter() {
    override fun getCount(): Int {
        return orderList.size
    }

    override fun getItem(position: Int): Any {
        return orderList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    private inner class ViewHolder {
        var txtItem: TextView? = null
        var txtItemDescription: TextView? = null
        var txtPrice: TextView? = null
        var txtDeny: TextView? = null
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View? {
        var item = view
        var holder = ViewHolder()
        if (item == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            item = inflater.inflate(layout, null)
            holder.txtItem = item.findViewById(R.id.name)
            holder.txtItemDescription = item.findViewById(R.id.description)
            holder.txtPrice = item.findViewById(R.id.price)
            holder.txtDeny = item.findViewById(R.id.order)
            item.tag = holder
        } else holder = item.tag as ViewHolder
        val model = orderList[i]
        holder.txtItem?.text = model.foodMenuItemId.name
        holder.txtItemDescription?.text = model.foodMenuItemId.description
        holder.txtPrice?.text = model.foodMenuItemId.price.toString()
        holder.txtDeny?.text = context.getString(R.string.cancel)
        return item
    }
}