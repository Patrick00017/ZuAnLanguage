package com.example.zuanlanguage.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zuanlanguage.R
import com.example.zuanlanguage.database.Language
import kotlinx.android.synthetic.main.data_recycler_cell.view.*

class DataFragmentRecyclerAdapter: ListAdapter<Language, DataFragmentRecyclerAdapter.ViewHolder>(diff) {

    object diff: DiffUtil.ItemCallback<Language>(){
        override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.data_recycler_cell,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.acTextView.text = getItem(position).acronym
        holder.itemView.meaningTextView.text = getItem(position).meaning
    }

}