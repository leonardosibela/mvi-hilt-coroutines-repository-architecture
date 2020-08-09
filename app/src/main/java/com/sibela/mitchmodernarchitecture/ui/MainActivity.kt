package com.sibela.mitchmodernarchitecture.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sibela.mitchmodernarchitecture.R
import com.sibela.mitchmodernarchitecture.model.Blog
import com.sibela.mitchmodernarchitecture.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainViewModel.MainStateEvent.GetBlogsEvent)
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

    private fun appendBlogTitles(blogs: List<Blog>) {
        val stringBuilder = StringBuilder()
        blogs.forEach { blog ->
            stringBuilder.append(blog.title + "\n")
            text.text = stringBuilder.toString()
        }
    }

    private fun displayError(message: String?) {
        if (message != null) {
            text.text = message
        } else {
            text.text = "Unknown error"
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