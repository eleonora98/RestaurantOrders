package com.example.restaurantorders.Restaurants

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantorders.R
import java.util.*

class RestaurantsActivity : AppCompatActivity() {

    var itemListView: ListView? = null
    var itemList: ArrayList<RestaurantItemModel>? = null
    var itemAdapter: RestaurantsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)
        init()
    }

    private fun init() {
        itemListView = findViewById(R.id.listViewRestaurants)
        itemList = ArrayList()
        itemList?.add(
            RestaurantItemModel(
                "Happy Bar Grill Grand Hotel Plovdiv",
                "Адрес: ул. „Васил Левски“ 2, 4003 Кършияка, Пловдив \n" +
                        "Работно време: \n" +
                        "Отворено ⋅ Ще затвори в 22:00"
            )
        )
        itemList?.add(
            RestaurantItemModel(
                "Happy Bar & Grill Landmark",
                ("Адрес: бул. „Meндeлeeв 2B, 4000 Тракия, Пловдив \n" +
                        "Работно време: \n" +
                        "Отворено ⋅ Ще затвори в 22:00")
            )
        )
        itemList?.add(
            RestaurantItemModel(
                "Хепи бар и грил",
                ("Адрес: Patriarh Evtimiy St 13, 4000 Център, Пловдив \n" +
                        "Работно време: \n" +
                        "Отворено ⋅ Ще затвори в 23:30")
            )
        )
        itemAdapter = RestaurantsAdapter(this, R.layout.restaurant_item, itemList!!)
        itemListView?.adapter = itemAdapter
    }
}