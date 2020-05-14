package com.app.myecom.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.myecom.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


object BindingAdapterUtils {
    @JvmStatic
    @BindingAdapter("android:profilePicUrl")
    fun loadImage(imageView: ImageView, image: String?) {
        Glide.with(imageView.context)  //2
            .load(image) //3
            // .centerCrop() //4
            .placeholder(R.drawable.ic_launcher_background) //5
            .error(R.drawable.ic_launcher_background) //6
            .fallback(R.drawable.ic_launcher_background) //7
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("roundedImage:imageSrc")
    fun loadImageRoundedCorner(imageView: ImageView, image: String?) {

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(RoundedCorners(16))

        Glide.with(imageView.context)  //2
            .load(image) //3
            // .centerCrop() //4
            .apply(requestOptions)
            .placeholder(R.drawable.ic_launcher_background) //5
            .error(R.drawable.ic_launcher_background) //6
            .fallback(R.drawable.ic_launcher_background) //7
            .into(imageView)
    }
}