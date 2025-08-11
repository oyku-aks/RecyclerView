package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val images = intent.getStringArrayListExtra("images") ?: arrayListOf()
        val position = intent.getIntExtra("position", 0)

        val adapter = DetailAdapter(images)
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(position, false)

        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.nextBtn.setOnClickListener {
            val nextItem = binding.viewPager.currentItem + 1
            if (nextItem < images.size) {
                binding.viewPager.setCurrentItem(nextItem, true)
            }
        }

        binding.prevBtn.setOnClickListener {
            val prevItem = binding.viewPager.currentItem - 1
            if (prevItem >= 0) {
                binding.viewPager.setCurrentItem(prevItem, true)
            }
        }
    }
}
