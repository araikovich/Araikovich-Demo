package araikovich.inc.araikovichdemo.ui.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import araikovich.inc.araikovichdemo.App

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