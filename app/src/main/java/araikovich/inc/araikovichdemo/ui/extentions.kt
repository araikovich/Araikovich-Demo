package araikovich.inc.araikovichdemo.ui

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import araikovich.inc.araikovichdemo.App
import araikovich.inc.araikovichdemo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

fun Int?.orZero() = this ?: 0

const val IMAGE_MATRIX_CROSS_FADE_DURATION = 200L
private const val ICON_SIZE = 200

fun Int.dpToPx(): Int = (this * App.instance.resources.displayMetrics.density).toInt()

private val crossFadeFactory =
    DrawableCrossFadeFactory.Builder(IMAGE_MATRIX_CROSS_FADE_DURATION.toInt()).setCrossFadeEnabled(true).build()

fun ImageView.loadCircleIcon(imageUrl: String?, @DrawableRes placeholder: Int? = null){
    try {
        imageUrl?.let {
            Glide.with(this)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade(crossFadeFactory))
                .apply(RequestOptions().circleCrop())
                .error(
                    Glide.with(this)
                        .load(imageUrl)
                        .apply(RequestOptions().circleCrop())
                        .override(ICON_SIZE, ICON_SIZE)
                )
                .override(ICON_SIZE, ICON_SIZE)
                .into(this)
        }
    } catch (e: Exception) {
        Log.e("loadCircleIcon", e.toString())
    }
}