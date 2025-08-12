package com.example.recyclerview.detail

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.recyclerview.R

class DetailActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var nextBtn: ImageButton
    private lateinit var prevBtn: ImageButton
    private lateinit var closeBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val images = intent.getStringArrayListExtra("images") ?: arrayListOf()
        val startPos = intent.getIntExtra("position", 0)

        viewPager = findViewById(R.id.viewPager)
        nextBtn = findViewById(R.id.nextBtn)
        prevBtn = findViewById(R.id.prevBtn)
        closeBtn = findViewById(R.id.closeBtn)

        viewPager.adapter = DetailAdapter(images)
        viewPager.setCurrentItem(startPos, false)

        nextBtn.setOnClickListener {
            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }
        prevBtn.setOnClickListener {
            viewPager.setCurrentItem(viewPager.currentItem - 1, true)
        }
        closeBtn.setOnClickListener { finish() }
    }
}
