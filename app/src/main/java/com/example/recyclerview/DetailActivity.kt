package com.example.recyclerview.detail

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.recyclerview.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val images = intent.getStringArrayListExtra("images") ?: arrayListOf()
        val start = intent.getIntExtra("position", 0)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = DetailAdapter(images)
        viewPager.setCurrentItem(start, false)

        findViewById<ImageButton>(R.id.closeBtn).setOnClickListener { finish() }
        findViewById<ImageButton>(R.id.nextBtn).setOnClickListener {
            if (viewPager.currentItem < images.size - 1)
                viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }
        findViewById<ImageButton>(R.id.prevBtn).setOnClickListener {
            if (viewPager.currentItem > 0)
                viewPager.setCurrentItem(viewPager.currentItem - 1, true)
        }
    }
}
