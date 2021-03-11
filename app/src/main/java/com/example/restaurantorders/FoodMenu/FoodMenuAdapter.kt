package com.example.restaurantorders.FoodMenu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.restaurantorders.Models.FoodMenuItem
import com.example.restaurantorders.R
import java.util.ArrayList

class FoodMenuAdapter(
    private val context: Context,
    private val layout: Int,
    private val foodMenuList: ArrayList<FoodMenuItem>
) :
    BaseAdapter() {
    override fun getCount(): Int {
        return foodMenuList.size
    }

    override fun getItem(position: Int): Any {
        return foodMenuList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    private inner class ViewHolder {
        var txtItem: TextView? = null
        var txtItemDescription: TextView? = null
        var txtPrice: TextView? = null
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
            item.tag = holder
        } else holder = item.tag as ViewHolder
        val model = foodMenuList[i]
        holder.txtItem?.text = model.name
        holder.txtItemDescription?.text = model.description
        holder.txtPrice?.text = model.price.toString()

        return item
    }

}