package com.example.restaurantorders.Models

import android.os.Parcel
import android.os.Parcelable

class Order {
    constructor(foodMenuItemId: FoodMenuItem) {
        this.foodMenuItemId = foodMenuItemId
    }
    constructor(id: Long) {
        this.id = id
    }

    var id: Long = 0
    var foodMenuItemId: FoodMenuItem = FoodMenuItem()


}