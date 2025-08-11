package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageAdapter


    private val imageList = listOf(
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ImageAdapter(imageList) { clickedUrl ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putStringArrayListExtra("images", ArrayList(imageList)) // DetailActivity ile uyumlu
            intent.putExtra("position", imageList.indexOf(clickedUrl))
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }
}
