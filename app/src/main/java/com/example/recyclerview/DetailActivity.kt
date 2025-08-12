package com.example.recyclerview.detail

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.recyclerview.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val closeBtn: ImageButton = findViewById(R.id.closeBtn)
        val nextBtn: ImageButton = findViewById(R.id.nextBtn)
        val prevBtn: ImageButton = findViewById(R.id.prevBtn)

        val images = intent.getStringArrayListExtra("images") ?: arrayListOf()
        val startPosition = intent.getIntExtra("position", 0)

        val adapter = DetailAdapter(images)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(startPosition, false)

        closeBtn.setOnClickListener { finish() }

        nextBtn.setOnClickListener {
            val next = viewPager.currentItem + 1
            if (next < images.size) viewPager.setCurrentItem(next, true)
        }

        prevBtn.setOnClickListener {
            val prev = viewPager.currentItem - 1
            if (prev >= 0) viewPager.setCurrentItem(prev, true)
        }
    }
}
