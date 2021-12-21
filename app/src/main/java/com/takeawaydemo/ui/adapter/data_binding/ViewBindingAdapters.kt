package com.iebayirli.recyclerviewwithdatabinding.internal.data_binding

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.iebayirli.recyclerviewwithdatabinding.base.BaseAdapter
import com.iebayirli.recyclerviewwithdatabinding.base.ListAdapterItem
import com.takeawaydemo.R

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseAdapter<ViewDataBinding, ListAdapterItem>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.updateData(list ?: listOf())
}

@BindingAdapter("manageState")
fun manageState(progressBar: ProgressBar, state: Boolean) {
    progressBar.visibility = if (state) View.VISIBLE else View.GONE
}

@BindingAdapter("setImage")
fun setImage(imageView: ShapeableImageView, image: Int) {
    Glide.with(imageView.context)
        .load(image)
        .into(imageView)
}

@BindingAdapter("setFavouriteCondition")
fun setFavouriteCondition(imageView: ShapeableImageView, isFavourite: Boolean) {
    if (isFavourite) {
        imageView.setImageResource(R.drawable.ic_fav_fill)
    } else {
        imageView.setImageResource(R.drawable.ic_fav_outline)
    }
}

@BindingAdapter("setTextBackground")
fun setTextBackground(textview: MaterialTextView, isSelect: Boolean) {
    if (isSelect) {
        //textview.setBackgroundColor(R.drawable.ic_text_border_orange)
        textview.setTextColor(textview.getContext().getResources().getColor(R.color.colorPrimary));
    } else {
       // textview.setBackgroundColor(R.drawable.ic_text_border_white)
        textview.setTextColor(textview.getContext().getResources().getColor(R.color.black));
    }
}
