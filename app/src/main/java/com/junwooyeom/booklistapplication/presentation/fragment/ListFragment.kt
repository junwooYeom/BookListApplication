package com.junwooyeom.booklistapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junwooyeom.booklistapplication.domain.model.NetworkResult
import com.junwooyeom.booklistapplication.R
import com.junwooyeom.booklistapplication.databinding.FragmentListBinding
import com.junwooyeom.booklistapplication.domain.model.Book
import com.junwooyeom.booklistapplication.hideKeyboard
import com.junwooyeom.booklistapplication.presentation.adapter.BookAdapter
import com.junwooyeom.booklistapplication.presentation.viewmodel.BookListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment :Fragment() {

    private val adapter by lazy {
        BookAdapter(this::onItemClicked)
    }

    private val viewModel by viewModels<BookListViewModel>()
    private lateinit var binding: FragmentListBinding

    private var isLastLoading: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        initListener()
        initRecyclerView()
        collectFlow()
    }

    private fun initRecyclerView() {
        binding.rvBook.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = this@ListFragment.adapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val gridManager = layoutManager as GridLayoutManager
                    if (gridManager.findLastVisibleItemPosition() >= gridManager.itemCount - 1 && gridManager.itemCount > 0 && isLastLoading.not()) {
                        isLastLoading = true
                        loadMore(gridManager.findLastVisibleItemPosition())
                    }
                }
            })
        }
    }

    private fun initListener() {
        binding.etKeyword.setEndIconOnClickListener {
            searchBooksByKey()
        }
    }

    private fun collectFlow() {
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.totalBooks.collectLatest {
                    binding.tvBookCount.text = getString(R.string.book_total_count, it)
                }
            }
        }
    }

    private fun onItemClicked(item: Book) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(item)
        findNavController().navigate(action)
    }

    private fun searchBooksByKey() {
        hideKeyboard()
        if (binding.etKeyword.editText?.text.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "텍스트를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.findBooksByKeyword(binding.etKeyword.editText?.text.toString()).collectLatest {
                    when (it) {
                        is NetworkResult.Success -> {
                            binding.llLoading.isVisible = false
                            adapter.submitList(it.data)
                        }
                        is NetworkResult.Loading -> {
                            binding.llLoading.isVisible = it.isLoading
                        }
                        is NetworkResult.Failure -> {
                            binding.llLoading.isVisible = false
                            Toast.makeText(requireContext(), "특정한 이유로 인하여 페이지를 불러오지 못했습니다 + ${it.error.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun loadMore(index: Int) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loadMoreBooks(index).collectLatest {
                    when (it) {
                        is NetworkResult.Success -> {
                            adapter.submitList(adapter.currentList + it.data)
                            binding.llLoading.isVisible = false
                            isLastLoading = false
                        }
                        is NetworkResult.Loading -> {
                            binding.llLoading.isVisible = it.isLoading
                        }
                        is NetworkResult.Failure -> {
                            binding.llLoading.isVisible = false
                            Toast.makeText(requireContext(), "특정한 이유로 인하여 페이지를 불러오지 못했습니다 + ${it.error.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    fun onKeyDown() {
        searchBooksByKey()
    }
}
