package araikovich.inc.araikovichdemo.ui.utils

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

object LoadImageUtil {

    const val IMAGE_MATRIX_CROSS_FADE_DURATION = 200L
    private const val ICON_SIZE = 200

    private val crossFadeFactory =
        DrawableCrossFadeFactory.Builder(IMAGE_MATRIX_CROSS_FADE_DURATION.toInt())
            .setCrossFadeEnabled(true).build()

    fun loadCircleIcon(view: ImageView, imageUrl: String?, @DrawableRes placeholder: Int? = null) {
        try {
            imageUrl?.also {
                Glide.with(view)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade(crossFadeFactory))
                    .apply(RequestOptions().circleCrop())
                    .error(
                        Glide.with(view)
                            .load(imageUrl)
                            .apply(RequestOptions().circleCrop())
                            .override(ICON_SIZE, ICON_SIZE)
                    )
                    .override(ICON_SIZE, ICON_SIZE)
                    .into(view)
            }
        } catch (e: Exception) {
            Log.e("loadCircleIcon", e.toString())
        }
    }

    fun loadCircleImageRes(view: ImageView, imageRes: Int?, @DrawableRes placeholder: Int? = null) {
        try {
            Glide.with(view)
                .load(imageRes)
                .transition(DrawableTransitionOptions.withCrossFade(crossFadeFactory))
                .apply(RequestOptions().circleCrop())
                .error(
                    Glide.with(view)
                        .load(imageRes)
                        .apply(RequestOptions().circleCrop())
                        .override(ICON_SIZE, ICON_SIZE)
                )
                .override(ICON_SIZE, ICON_SIZE)
                .into(view)
        } catch (e: Exception) {
            Log.e("loadCircleIcon", e.toString())
        }
    }
}