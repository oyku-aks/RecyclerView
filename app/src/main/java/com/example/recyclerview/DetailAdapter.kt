package com.example.recyclerview.detail

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.R

class DetailAdapter(
    private val imageList: List<String>
) : RecyclerView.Adapter<DetailAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.fullscreenImageView)
        private val handler = Handler(Looper.getMainLooper())
        private var impressionFired = false // reset

        private val impressionRunnable = Runnable {
            println("impression")
            impressionFired = true
        }

        fun bind(url: String) {
            Glide.with(itemView.context).load(url).into(imageView)
            startTimer()
        }

        private fun startTimer() {
            impressionFired = false
            handler.removeCallbacks(impressionRunnable)
            handler.postDelayed(impressionRunnable, 10_000) // 10 sn sonra impression
        }

        fun cancelIfLate() {
            handler.removeCallbacks(impressionRunnable)
            if (impressionFired) {
                Toast.makeText(itemView.context, "cancel", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_image, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.cancelIfLate()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.cancelIfLate()
    }
}
