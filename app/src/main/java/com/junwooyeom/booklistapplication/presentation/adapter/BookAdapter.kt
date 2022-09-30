package com.junwooyeom.booklistapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junwooyeom.booklistapplication.R
import com.junwooyeom.booklistapplication.databinding.GridViewBinding
import com.junwooyeom.booklistapplication.domain.model.Book
import com.junwooyeom.booklistapplication.extension.listenOnClick
import com.junwooyeom.booklistapplication.loadImage

class BookAdapter(
    private val itemClicked: (item: Book) -> Unit
) : ListAdapter<Book, BookAdapter.ViewHolder>(bookComparator) {
    companion object {
        private val bookComparator = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GridViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).listenOnClick {
            itemClicked(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = getItem(position))
    }

    class ViewHolder(
        private val binding: GridViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book) {
            binding.tvTitle.text = item.volumeInfo.title
            binding.tvDirectors.text =
                if (item.volumeInfo.authors.isEmpty()) {
                    "작가 미상"
                } else {
                    binding.root.context.getString(R.string.book_authod_text, item.volumeInfo.authors.joinToString(","))
                }
            binding.tvDirected.text = item.volumeInfo.published
            binding.ivThumbnail.loadImage(item.volumeInfo.links?.smallThumbnail)
        }
    }
}
