package com.example.zuanlanguage.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zuanlanguage.R
import com.example.zuanlanguage.database.Language
import com.example.zuanlanguage.viewModel.DataViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_recycler_cell.view.*

class DataFragmentRecyclerAdapter(application: Application): ListAdapter<Language, DataFragmentRecyclerAdapter.ViewHolder>(diff) {
    companion object{
        val CLIPDATA_LABEL = "ZuAnLanguage"
    }

    private lateinit var application: Application
    private lateinit var dataViewModel: DataViewModel

    init {
        this.application = application
        this.dataViewModel = DataViewModel(application)
    }
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
        val holder = ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.data_recycler_cell,
            parent,
            false
        ))

        //add data to clipboardmanager
        holder.itemView.copyButton.setOnClickListener {
            val clipboardManager = parent.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText(CLIPDATA_LABEL, getItem(holder.adapterPosition).meaning)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(parent.context, R.string.copy_successful, Toast.LENGTH_SHORT).show()
        }
        holder.itemView.like_button.setOnClickListener {
            val language = getItem(holder.adapterPosition)
            if(language.isLiked){
                Picasso.get().load(R.drawable.like_unclick).into(it as ImageView)
                language.isLiked = false
                dataViewModel.updateData(language)
                Toast.makeText(application, R.string.undoLike, Toast.LENGTH_SHORT).show()
            }else{
                Picasso.get().load(R.drawable.like_clicked).into(it as ImageView)
                language.isLiked = true
                dataViewModel.updateData(language)
                Toast.makeText(application, R.string.doLike, Toast.LENGTH_SHORT).show()
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.acTextView.text = getItem(position).acronym
        holder.itemView.meaningTextView.text = getItem(position).meaning
        if(getItem(position).isLiked){
            Picasso.get().load(R.drawable.like_clicked).into(holder.itemView.like_button as ImageView)
        }
        else{
            Picasso.get().load(R.drawable.like_unclick).into(holder.itemView.like_button as ImageView)
        }
    }


}