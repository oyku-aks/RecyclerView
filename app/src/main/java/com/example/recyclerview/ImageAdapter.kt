package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.ItemImageBinding

class ImageAdapter(
    private val images: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ImageAdapter.VH>() {

    inner class VH(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            Glide.with(binding.root.context)
                .load(url)
                .centerCrop()
                .into(binding.imageView)

            binding.root.setOnClickListener { onItemClick(url) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(images[position])

    override fun getItemCount() = images.size
}
