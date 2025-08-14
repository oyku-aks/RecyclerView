package com.example.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.ItemImageBinding

class CustomImageRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private var onItemClick: ((url: String, position: Int) -> Unit)? = null
    private var columnCount: Int = 2
    private var itemHeightPx: Int = dp(150)
    private var itemSpacingPx: Int = dp(8)

    private val imageAdapter = ImageAdapter()

    init {
        layoutManager = GridLayoutManager(context, columnCount)
        adapter = imageAdapter
        setHasFixedSize(true)
        overScrollMode = OVER_SCROLL_NEVER
        addItemDecoration(SpacingItemDecoration(itemSpacingPx))
    }

    fun setImages(urls: List<String>) {
        imageAdapter.submit(urls)
    }

    fun setOnImageClickListener(l: (String, Int) -> Unit) {
        onItemClick = l
    }

    fun setSpanCount(count: Int) {
        columnCount = count.coerceAtLeast(1)
        (layoutManager as? GridLayoutManager)?.spanCount = columnCount
    }

    fun setItemHeightDp(heightDp: Int) {
        itemHeightPx = dp(heightDp)
        imageAdapter.notifyDataSetChanged()
    }

    fun setItemSpacingDp(spaceDp: Int) {
        itemSpacingPx = dp(spaceDp)
        while (itemDecorationCount > 0) removeItemDecorationAt(0)
        addItemDecoration(SpacingItemDecoration(itemSpacingPx))
        invalidateItemDecorations()
    }

    private inner class ImageAdapter : Adapter<ImageAdapter.VH>() {
        private val items = mutableListOf<String>()

        inner class VH(val binding: ItemImageBinding) : ViewHolder(binding.root) {
            fun bind(url: String, pos: Int) {

                Glide.with(binding.root)
                    .load(url)
                    .centerCrop()
                    .into(binding.imageView)


                binding.root.layoutParams = binding.root.layoutParams.apply {
                    height = itemHeightPx
                }

                binding.root.setOnClickListener {
                    onItemClick?.invoke(url, pos)
                        ?: Toast.makeText(context, "Clicked $pos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemImageBinding.inflate(inflater, parent, false)
            return VH(binding)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(items[position], position)
        }

        override fun getItemCount() = items.size

        fun submit(data: List<String>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }
    }

    private fun dp(v: Int) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, v.toFloat(), resources.displayMetrics
    ).toInt()
}
