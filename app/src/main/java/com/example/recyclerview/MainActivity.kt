package com.example.recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
            Toast.makeText(this, "Tıklandı: $clickedUrl", Toast.LENGTH_SHORT).show()
        }
    }
}

