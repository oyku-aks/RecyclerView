package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.ItemFullscreenImageBinding

class DetailAdapter(private val imageList: List<String>) :
    RecyclerView.Adapter<DetailAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ItemFullscreenImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.fullscreenImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemFullscreenImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}
