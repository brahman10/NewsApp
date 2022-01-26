package com.example.newsappassignment.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappassignment.data.Articles
import com.example.newsappassignment.databinding.ItemNewsBinding

class NewsAdapter(val context:Context,var arrList : ArrayList<Articles> , val onItemClick: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.getContext())
        val itemBinding: ItemNewsBinding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = arrList[position]
        holder.bind(item,position)

    }

    inner class NewsViewHolder(binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        private val binding: ItemNewsBinding = binding
        fun bind(item: Articles,pos:Int) {
            binding.tvHeading.text = item.title
            binding.tvDesc.text = item.description
            val separated = item.publishedAt?.split("T")
            binding.tvDate.text =separated?.get(0) ?: ""
            Glide.with(context).load(item.urlToImage).into(binding.ivItem)
            binding.btnRead.setOnClickListener {
                onItemClick.onReadClicked(pos)
            }
            binding.btnSave.setOnClickListener {
                onItemClick.onSaveClicked(pos)
            }
            binding.cvSave.setOnClickListener {
                onItemClick.onSaveClicked(pos)
            }
            binding.executePendingBindings()
        }

    }


    interface OnItemClickListener{
        fun onReadClicked(newsId:Int)
        fun onSaveClicked(newsId:Int)
    }

}