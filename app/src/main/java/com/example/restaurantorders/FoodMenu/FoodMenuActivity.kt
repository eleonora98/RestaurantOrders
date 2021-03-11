package com.example.restaurantorders.FoodMenu

import android.database.Cursor
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantorders.DatabaseHelper
import com.example.restaurantorders.Models.FoodMenuItem
import com.example.restaurantorders.Models.Order
import com.example.restaurantorders.R
import kotlinx.android.synthetic.main.activity_food_menu2.*

class FoodMenuActivity : AppCompatActivity() {

    var adapter: FoodMenuAdapter? = null
    private var listMenu: ArrayList<FoodMenuItem>? = null
    lateinit var orderItem: FoodMenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_menu2)
        addFoodMenuItem()
        initViews()
        listViewFoods.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->
                var helper: DatabaseHelper = DatabaseHelper(this)
                orderItem = adapter?.getItem(position) as FoodMenuItem
                helper.insertOrderItem(Order(orderItem))
                Toast.makeText(this, R.string.madeOrder, Toast.LENGTH_LONG).show()
            }
    }

    private fun initViews(){
        adapter = FoodMenuAdapter(this, R.layout.row_food_item, listMenu!!)
        listViewFoods?.adapter = adapter
    }

    private fun addFoodMenuItem(): List<FoodMenuItem?>? {
        listMenu = ArrayList<FoodMenuItem>()
        val helper: DatabaseHelper = DatabaseHelper(this)
        val cursor: Cursor? =
            helper.getData("SELECT * FROM Food_Menu_Item ")
        //        listRate.clear();
        while (cursor?.moveToNext()!!) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val description = cursor.getString(2)
            val price = cursor.getDouble(3)

            lateinit var  item: FoodMenuItem
            listMenu!!.add(FoodMenuItem(id, name, description, price))
        }
        //        adapter.notifyDataSetChanged();
        if (listMenu?.isEmpty() == true) {
        }
        return listMenu
    }
}