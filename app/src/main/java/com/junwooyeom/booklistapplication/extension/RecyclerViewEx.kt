package com.junwooyeom.booklistapplication.extension

import androidx.recyclerview.widget.RecyclerView

fun <T: RecyclerView.ViewHolder> T.listenOnClick(event: (position: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition)
    }
    return this
}
