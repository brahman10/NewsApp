package com.example.newsappassignment.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappassignment.R
import com.example.newsappassignment.data.Articles
import com.example.newsappassignment.databinding.ItemNewsBinding
import com.example.newsappassignment.databinding.ItemSavesBinding

class SaveAdapter(val context:Context,var arrList : ArrayList<Articles> , val onItemClick: OnSaveItemClickListener) :
    RecyclerView.Adapter<SaveAdapter.SaveViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding= ItemSavesBinding.inflate(layoutInflater, parent, false)
        return SaveViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        val item = arrList[position]
        holder.bind(item,position)

    }

    inner class SaveViewHolder(binding: ItemSavesBinding) : RecyclerView.ViewHolder(binding.root) {
        private val binding: ItemSavesBinding = binding
        fun bind(item: Articles,pos:Int) {
            binding.tvHashtag.text="#notfoundinapi"
            binding.tvHeading.text = item.title
            val separated = item.publishedAt?.split("T")
            binding.tvDate.text ="${separated?.get(0)} ${item.author}" ?: ""
            Glide.with(context).load(item.urlToImage).into(binding.ivItem)
            binding.executePendingBindings()
        }
    }


    interface OnSaveItemClickListener{
        fun onClicked(newsId:Int)
        fun onUnSaveClicked(newsId:Int)
    }

}