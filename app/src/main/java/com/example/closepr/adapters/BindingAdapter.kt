package com.example.closepr.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.closepr.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imageUrl: String) {
    Picasso.get().load(imageUrl)
        .transform(RoundedCornersTransformation(40, 5))
        .into(view)
}

@BindingAdapter(value = ["date", "dateLabel"], requireAll = true)
fun setDate(view: TextView, date: Date, dateLabel: String) {
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    val dateString: String = sdf.format(date)
    view.text =
        String.format(view.context.getString(R.string.date_with_label), dateLabel, dateString)
}