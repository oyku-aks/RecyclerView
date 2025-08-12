package com.example.recyclerview.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.R

class DetailAdapter(private val images: List<String>) :
    RecyclerView.Adapter<DetailAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView) // XML ile aynı id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fullscreen_image, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(holder.itemView)
            .load(images[position])
            .fitCenter()
            .into(holder.image)
    }

    override fun getItemCount(): Int = images.size
}
