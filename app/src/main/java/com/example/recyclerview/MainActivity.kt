package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.adapter.ImageAdapter
import com.example.recyclerview.detail.DetailActivity

class MainActivity : AppCompatActivity() {

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

        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = GridLayoutManager(this, 2)
        rv.setHasFixedSize(true)
        rv.adapter = ImageAdapter(imageUrls) { clickedUrl ->
            val i = Intent(this, DetailActivity::class.java)
            i.putStringArrayListExtra("images", ArrayList(imageUrls))
            i.putExtra("position", imageUrls.indexOf(clickedUrl))
            startActivity(i)
        }
    }
}
