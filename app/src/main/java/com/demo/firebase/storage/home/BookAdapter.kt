package com.demo.firebase.storage.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.firebase.storage.R
import com.demo.firebase.storage.model.Book

class BookAdapter() : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffUtil()) {


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookViewHolder(inflater.inflate(R.layout.item_book, parent, false))
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookName: TextView = itemView.findViewById(R.id.tvTitle)
        private val bookAuthor: TextView = itemView.findViewById(R.id.tvAuthor)

        fun bindData(book: Book) {
            bookName.text = book.bookName
            bookAuthor.text = book.bookAuthor
        }
    }

    class BookDiffUtil : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book) =
            oldItem.bookName == newItem.bookName

        override fun areContentsTheSame(oldItem: Book, newItem: Book) =
            oldItem == newItem
    }
}