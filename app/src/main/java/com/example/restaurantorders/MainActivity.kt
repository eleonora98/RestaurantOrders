package com.example.restaurantorders

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.restaurantorders.ContactUs.ContactsActivity
import com.example.restaurantorders.FoodMenu.FoodMenuActivity
import com.example.restaurantorders.Models.FoodMenuItem
import com.example.restaurantorders.Orders.OrderActivity
import com.example.restaurantorders.Restaurants.RestaurantsActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var viewPager: ViewPager? = null
    var sliderDotsPannel: LinearLayout? = null
    var price: ImageView? = null
    private var dotsCount = 0
    private var dots: Array<ImageView?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        addMenuItems()
        buttonsClick()
    }

    private fun initViews() {
        viewPager = findViewById(R.id.viewPager)
        sliderDotsPannel = findViewById<LinearLayout>(R.id.SliderDots)
        price = findViewById(R.id.price)
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager!!.adapter = viewPagerAdapter
        dotsCount = viewPagerAdapter.count

        addDotsToSlider()
    }

    private fun addMenuItems() {
        val foodMenuItem: FoodMenuItem = FoodMenuItem(1, "Пица Маргарита", "Домати, моцарела", 8.00)

        val foodMenuItem2: FoodMenuItem = FoodMenuItem(2, "Пица Карбонара", "Пица Карбонара", 9.00)
        val foodMenuItem3: FoodMenuItem = FoodMenuItem(3, "Пица с пиле", "Моцарела, пиле", 9.00)
        val helper = DatabaseHelper(this)

        helper.insertFoodItem(foodMenuItem)
        helper.insertFoodItem(foodMenuItem2)
        helper.insertFoodItem(foodMenuItem3)
    }

    private fun addDotsToSlider() {
        dots = arrayOfNulls(dotsCount)
        for (i in 0 until dotsCount) {
            dots!![i] = ImageView(this)
            dots!![i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.non_active_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotsPannel!!.addView(dots!![i], params)
        }
        dots!![0]!!.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.active_dot
            )
        )
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotsCount) {
                    dots!![i]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.non_active_dot
                        )
                    )
                }
                dots!![position]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.active_dot
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun buttonsClick() {
        menuButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, FoodMenuActivity::class.java))
        })

        ordersButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, OrderActivity::class.java))
        })

        restaurantsButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, RestaurantsActivity::class.java))
        })

        contactsButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, ContactsActivity::class.java))
        })
    }

}