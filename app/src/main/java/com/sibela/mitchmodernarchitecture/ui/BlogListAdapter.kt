package com.sibela.mitchmodernarchitecture.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sibela.mitchmodernarchitecture.R
import com.sibela.mitchmodernarchitecture.model.Blog
import kotlinx.android.synthetic.main.blog_list_item.view.*

class BlogListAdapter(private var blogsField: List<Blog>) :
    RecyclerView.Adapter<BlogListAdapter.BlogListViewHolder>() {

    var blogs
        get() = blogsField
        set(value) {
            this.blogsField = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = blogsField.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.blog_list_item, parent, false)
        return BlogListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogListViewHolder, position: Int) {
        val blog = blogsField[position]
        holder.bind(blog)
    }

    class BlogListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: AppCompatTextView = view.blog_title

        fun bind(blog: Blog) {
            title.text = blog.title
        }
    }
}