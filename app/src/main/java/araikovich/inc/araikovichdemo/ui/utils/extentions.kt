package araikovich.inc.araikovichdemo.ui.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import araikovich.inc.araikovichdemo.App
import araikovich.inc.araikovichdemo.R

fun Int?.orZero() = this ?: 0

fun Int.dpToPx(): Int = (this * App.instance.resources.displayMetrics.density).toInt()

fun View.scale(scale: Float) {
    scaleX = scale
    scaleY = scale
}

fun ImageView.loadCircleIcon(imageUrl: String?, @DrawableRes placeholder: Int? = null) {
    LoadImageUtil.loadCircleIcon(this, imageUrl, placeholder)
}

fun ImageView.loadCircleIconRes(imageRes: Int?, @DrawableRes placeholder: Int? = null) {
    LoadImageUtil.loadCircleImageRes(this, imageRes, placeholder)
}

fun Context.toolBarHeight(): Int {
    val attrs = intArrayOf(R.attr.actionBarSize)
    val typedArray = obtainStyledAttributes(attrs)
    val toolBarHeight = typedArray.getDimensionPixelSize(0, -1)
    typedArray.recycle()
    return toolBarHeight
}