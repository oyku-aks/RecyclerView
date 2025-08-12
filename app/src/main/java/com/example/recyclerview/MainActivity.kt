package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter

    private val imageUrls = listOf(
        "https://picsum.photos/id/10/1200/1200",
        "https://picsum.photos/id/20/1200/1200",
        "https://picsum.photos/id/30/1200/1200",
        "https://picsum.photos/id/40/1200/1200",
        "https://picsum.photos/id/50/1200/1200",
        "https://picsum.photos/id/60/1200/1200",
        "https://picsum.photos/id/70/1200/1200",
        "https://picsum.photos/id/80/1200/1200",
        "https://picsum.photos/id/90/1200/1200",
        "https://picsum.photos/id/100/1200/1200"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        imageAdapter = ImageAdapter(imageUrls) { position ->
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putStringArrayListExtra("images", ArrayList(imageUrls))
            intent.putExtra("position", position)
            startActivity(intent)
        }
        recyclerView.adapter = imageAdapter
    }
}
