package com.example.restaurantorders.Orders

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import com.example.restaurantorders.DatabaseHelper
import com.example.restaurantorders.Models.FoodMenuItem
import com.example.restaurantorders.Models.Order
import com.example.restaurantorders.R
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    var adapter: OrdersAdapter? = null
    private var listOrder: ArrayList<Order>? = null
    lateinit var orderItem: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        addOrderItem()
        initViews()

        listViewOrders.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->
                var helper = DatabaseHelper(this)
                orderItem = adapter?.getItem(position) as Order
                helper.delete(orderItem.id)
                listOrder?.removeAt(position)
                adapter?.notifyDataSetChanged();
            }
    }

    private fun initViews(){
        adapter = OrdersAdapter(this, R.layout.row_food_item, listOrder!!)
        listViewOrders?.adapter = adapter
    }

    private fun addOrderItem(): List<Order?>? {
        listOrder = ArrayList()
        var helper = DatabaseHelper(this)
        val cursor: Cursor? =
            helper.getData("SELECT * FROM Food_Menu_Item Join OrderItem on Food_Menu_Item.id" +
                    "=OrderItem.order_food_menu_item_id ")
        //        listRate.clear();
        while (cursor?.moveToNext()!!) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val description = cursor.getString(2)
            val price = cursor.getDouble(3)

            listOrder!!.add(Order(FoodMenuItem(id, name, description, price)))
        }
        //        adapter.notifyDataSetChanged();
        if (listOrder?.isEmpty() == true) {
        }
        return listOrder
    }
}