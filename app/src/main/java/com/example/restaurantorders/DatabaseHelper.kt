package com.example.restaurantorders

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.restaurantorders.Models.FoodMenuItem
import com.example.restaurantorders.Models.Order

private const val TABLE_FOOD_MENU_ITEM = "Food_Menu_Item"
private const val TABLE_ORDER = "OrderItem"

private const val KEY_ID = "id"
private const val KEY_NAME = "name"
private const val KEY_DESCRIPTION = "description"
private const val KEY_PRICE = "price"
private const val KEY_FOOD_ITEM_ORDER_ID = "order_food_menu_item_id"

private const val KEY_STATUS = "status"


private const val DatabaseName = "Restaurant.db"
private const val DatabaseVersion = 3

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DatabaseName,
    null,
    DatabaseVersion
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CreateTableFoodMenuItem)
        db.execSQL(CreateTableOrderItem)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD_MENU_ITEM")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDER")
        onCreate(db)
    }

    private val CreateTableFoodMenuItem = "Create Table " + "if not exists " + TABLE_FOOD_MENU_ITEM +
            "(" +
            KEY_ID + " integer primary key, " +
            KEY_NAME + " string not null, " +
            KEY_DESCRIPTION + " string not null, " +
            KEY_PRICE + " double not null " +
//            KEY_FOOD_ITEM_IMAGE_ID + " integer not null " +
//            "FOREIGN KEY " + "(" + KEY_FOOD_ITEM_IMAGE_ID + ")" + " REFERENCES " + TABLE_FOOD_MENU_ITEM + " (" + KEY_ID + ")" +
            ")"

    private val CreateTableOrderItem = "Create Table " + "if not exists " + TABLE_ORDER +
            "(" +
            KEY_ID + " integer primary key autoincrement default 1, " +
            KEY_FOOD_ITEM_ORDER_ID + " integer not null, " +
            "FOREIGN KEY " + "(" + KEY_FOOD_ITEM_ORDER_ID + ")" + " REFERENCES " + TABLE_FOOD_MENU_ITEM + " (" + KEY_ID + ") " +
            ")"

    fun insertFoodItem(item: FoodMenuItem){
        val db = this.writableDatabase
            val values = ContentValues()
           values.put(KEY_ID, item.id)
            values.put(KEY_NAME, item.name)
            values.put(KEY_DESCRIPTION, item.description)
            values.put(KEY_PRICE, item.price)
            db.insert(TABLE_FOOD_MENU_ITEM, null, values)
            db.close()
    }

    fun insertOrderItem(item: Order){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_FOOD_ITEM_ORDER_ID, item.foodMenuItemId.id)
//        values.put(KEY_STATUS, item.status)
        var id: Long = db.insert(TABLE_ORDER, null, values)
        item.id = id
        db.close()
    }

    fun getData(sql: String?): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery(sql, null)
    }

    @Throws(SQLException::class)
    fun delete(id: Long) {
        val db = this.writableDatabase
        val q = "DELETE FROM $TABLE_ORDER WHERE ID=$id"
        db.execSQL(q)
    }

}