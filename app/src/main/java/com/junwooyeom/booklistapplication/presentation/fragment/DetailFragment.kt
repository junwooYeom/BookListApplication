package com.junwooyeom.booklistapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.junwooyeom.booklistapplication.R
import com.junwooyeom.booklistapplication.databinding.FragmentDetailBinding
import com.junwooyeom.booklistapplication.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        binding.apply {
            ivThumbnail.loadImage(args.book.volumeInfo.links?.thumbnail)
            tvTitle.text = args.book.volumeInfo.title
            tvSubtitle.text = args.book.volumeInfo.subTitle
            tvAuthors.text =
                if (args.book.volumeInfo.authors.isEmpty()) {
                    "작가 미상"
                } else {
                    binding.root.context.getString(R.string.book_authod_text, args.book.volumeInfo.authors.joinToString(","))
                }
            tvPublisher.text = args.book.volumeInfo.publisher
            tvPublished.text = args.book.volumeInfo.published
        }
    }
}
