package com.example.restaurantorders


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


class ViewPagerAdapter(private val context: Context) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private val images =
        arrayOf<Int>(R.drawable.pizza_carbonara, R.drawable.pizza_carbonara, R.drawable.pizza_carbonara)
    var price: ImageView? = null
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.custom_layout, null)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textView = view.findViewById<TextView>(R.id.tvSlider)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        price = view.findViewById(R.id.price)
        imageView.setImageResource(images[position])
        price!!.setImageResource(R.drawable.circle_price)
        if (position == 0) {
            textView.text = "Торта + кафе"
            tvPrice.text = "3.90"
        } else if (position == 1) {
            textView.text = "2 парчета пица по избор"
            tvPrice.text = "4.90"
        } else if (position == 2) {
            textView.text = "Пица + кола"
            tvPrice.text = "4.60"
        }
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}