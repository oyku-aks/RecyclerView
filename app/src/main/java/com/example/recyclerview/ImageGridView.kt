package com.example.recyclerview.ui

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.setMargins
import com.bumptech.glide.Glide
import com.example.recyclerview.R
import kotlin.math.max

class ImageGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val grid by lazy { findViewById<android.widget.GridLayout>(R.id.grid) }
    private var urls: List<String> = emptyList()
    private var onItemClick: ((String, Int) -> Unit)? = null


    private var columnCount: Int = 2
    private var itemHeightPx: Int = dp(150)
    private var itemSpacingPx: Int = dp(8)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_image_grid, this, true)


        attrs?.let {
            val ta = context.obtainStyledAttributes(it, R.styleable.ImageGridView)
            columnCount = ta.getInt(R.styleable.ImageGridView_columnCount, 2)
            itemHeightPx = ta.getDimensionPixelSize(R.styleable.ImageGridView_itemHeight, dp(150))
            itemSpacingPx = ta.getDimensionPixelSize(R.styleable.ImageGridView_itemSpacing, dp(8))
            ta.recycle()
        }
        grid.columnCount = max(1, columnCount)
    }


    fun setImages(data: List<String>) {
        urls = data
        buildGrid()
    }


    fun setOnImageClickListener(l: (String, Int) -> Unit) {
        onItemClick = l
    }

    private fun buildGrid() {
        grid.removeAllViews()
        if (urls.isEmpty()) return

        urls.forEachIndexed { index, url ->
            val iv = ImageView(context).apply {
                layoutParams = android.widget.GridLayout.LayoutParams().also { lp ->
                    lp.width = 0 // ağırlık ile eşit genişlik
                    lp.height = itemHeightPx
                    lp.columnSpec = android.widget.GridLayout.spec(
                        android.widget.GridLayout.UNDEFINED, 1f
                    )
                    lp.setMargins(itemSpacingPx)
                    (lp as android.widget.GridLayout.LayoutParams).setGravity(Gravity.FILL)
                }
                scaleType = ImageView.ScaleType.CENTER_CROP
                contentDescription = resources.getString(R.string.image_desc)
                foreground = selectableRipple()
                setOnClickListener {
                    onItemClick?.invoke(url, index) ?: Toast.makeText(
                        context, "Clicked $index", Toast.LENGTH_SHORT
                    ).show()
                }
            }


            Glide.with(this).load(url).centerCrop().into(iv)
            grid.addView(iv)
        }
    }

    private fun selectableRipple() = runCatching {
        val out = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, out, true)
        context.getDrawable(out.resourceId)
    }.getOrNull()

    private fun dp(value: Int): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), resources.displayMetrics
        ).toInt()
}
