package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var customRecycler: CustomImageRecyclerView


    private val imageUrls = listOf(
        "https://picsum.photos/id/10/800/800",
        "https://picsum.photos/id/20/800/800",
        "https://picsum.photos/id/30/800/800",
        "https://picsum.photos/id/40/800/800",
        "https://picsum.photos/id/50/800/800",
        "https://picsum.photos/id/60/800/800",
        "https://picsum.photos/id/70/800/800",
        "https://picsum.photos/id/80/800/800",
        "https://picsum.photos/id/90/800/800",
        "https://picsum.photos/id/100/800/800"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        customRecycler = findViewById(R.id.customRecycler)


        customRecycler.setSpanCount(2)
        customRecycler.setItemSpacingDp(8)
        customRecycler.setItemHeightDp(150)


        customRecycler.setImages(imageUrls)


        customRecycler.setOnImageClickListener { _, position ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putStringArrayListExtra(DetailActivity.EXTRA_URLS, ArrayList(imageUrls))
                putExtra(DetailActivity.EXTRA_INDEX, position)
            }
            startActivity(intent)
        }
    }
}
