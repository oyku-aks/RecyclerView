package com.example.recyclerview

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.recyclerview.detail.DetailAdapter

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_URLS = "extra_urls"
        const val EXTRA_INDEX = "extra_index"
    }

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val urls = intent.getStringArrayListExtra(EXTRA_URLS) ?: arrayListOf()
        val startIndex = intent.getIntExtra(EXTRA_INDEX, 0)

        viewPager = findViewById(R.id.viewPager)
        adapter = DetailAdapter(urls)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(startIndex, false)

        findViewById<ImageButton>(R.id.closeBtn).setOnClickListener { finish() }
        findViewById<ImageButton>(R.id.nextBtn).setOnClickListener {
            val next = (viewPager.currentItem + 1).coerceAtMost(urls.lastIndex)
            viewPager.setCurrentItem(next, true)
        }
        findViewById<ImageButton>(R.id.prevBtn).setOnClickListener {
            val prev = (viewPager.currentItem - 1).coerceAtLeast(0)
            viewPager.setCurrentItem(prev, true)
        }
    }
}
