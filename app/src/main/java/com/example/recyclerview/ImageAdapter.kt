package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(
    private val images: List<String>,
    private val onClick: ((String) -> Unit)? = null
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(v)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = images[position]
        Glide.with(holder.itemView.context)
            .load(url)
            .centerCrop()
            .into(holder.imageView)

        holder.itemView.setOnClickListener { onClick?.invoke(url) }
    }

    override fun getItemCount(): Int = images.size
}
