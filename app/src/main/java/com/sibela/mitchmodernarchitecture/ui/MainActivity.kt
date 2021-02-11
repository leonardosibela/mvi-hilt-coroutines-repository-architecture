package com.sibela.mitchmodernarchitecture.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibela.mitchmodernarchitecture.R
import com.sibela.mitchmodernarchitecture.model.Blog
import com.sibela.mitchmodernarchitecture.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

private const val TAG: String = "AppDebug"

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var blogListAdapter = BlogListAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainViewModel.MainStateEvent.GetBlogsEvent)
        setupRecyclerView()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressbar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressbar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressbar(true)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        blogs_recycler.apply {
            setHasFixedSize(true)
            adapter = blogListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun appendBlogTitles(blogs: List<Blog>) {
        blogListAdapter.blogs = blogs
    }

    private fun displayError(message: String?) {
        if (message != null) {
            error_message.text = message
        } else {
            error_message.setText(R.string.message_error_unknown)
        }
    }

    private fun displayProgressbar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}