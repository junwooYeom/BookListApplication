package com.junwooyeom.booklistapplication

import android.widget.ImageView

fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty().not()) {
        GlideApp.with(this.context).load(url).into(this)
    } else {
        setImageResource(R.drawable.ic_block)
    }
}
