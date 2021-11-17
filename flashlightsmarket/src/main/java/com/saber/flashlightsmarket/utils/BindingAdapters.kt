package com.saber.flashlightsmarket.utils

import android.content.res.Resources.NotFoundException
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.saber.flashlightsmarket.R
import com.saber.flashlightsmarket.ui.AppsAdapter
import com.squareup.picasso.Picasso

/**
 * An adapter to bind errors to EditText
 */

@BindingAdapter("error")
fun EditText.setError(error: Int) {
    try {
        setError(context.getString(error))
        requestFocus()
    } catch (e: NotFoundException) {
        setError(null)
        clearFocus()
    }

}

@BindingAdapter("pullRequestsAdapter")
fun RecyclerView.setAppsAdapter(adapter: AppsAdapter?) {
    layoutManager = LinearLayoutManager(context)
    setAdapter(adapter)
}

@BindingAdapter("onRefresh")
inline fun SwipeRefreshLayout.setOnRefreshListener(crossinline onRefresh: () -> Unit) {
    setOnRefreshListener { onRefresh() }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageByUrl(imageUrl: String?) {
    Picasso.get()
        .load(imageUrl).fit()
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(this)
}

@BindingAdapter("requestState")
fun TextView.setState(requestState: String) {
    text = requestState
//    ViewCompat.setBackground(
//        this,
//        if (requestState == context.getString(R.string.open))
//            ContextCompat.getDrawable(context, R.drawable.red_rect_filled)
//        else ContextCompat.getDrawable(context, R.drawable.green_rect_filled)
//    )
}