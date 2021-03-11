package com.example.restaurantorders.Models

import android.os.Parcel
import android.os.Parcelable

class FoodMenuItem(){
     var id: Int = 0
     var name: String = ""
     var description: String = ""
     var price: Double = 0.0

     constructor(id: Int, name: String, description: String, price: Double) : this() {
          this.id = id
          this.name = name
          this.description = description
          this.price = price
     }



}