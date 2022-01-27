package com.example.newsappassignment.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.newsappassignment.R
import com.example.newsappassignment.data.DataState.*
import com.example.newsappassignment.databinding.ActivitySaveBinding
import com.example.newsappassignment.ui.MainStateEvent
import com.example.newsappassignment.ui.MainViewModel
import com.example.newsappassignment.ui.SaveViewModel
import com.example.newsappassignment.ui.adapters.NewsAdapter
import com.example.newsappassignment.ui.adapters.SaveAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveActivity : AppCompatActivity(),SaveAdapter.OnSaveItemClickListener {
    private val viewModel : SaveViewModel by viewModels()
    lateinit var binding: ActivitySaveBinding
    lateinit var adapter: SaveAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_save)
        initObservers()
        viewModel.getSavedArticle()
    }

    private fun initData()
    {
        adapter = SaveAdapter(this,viewModel.list,this)
        binding.rvNews.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun initObservers()
    {
        viewModel.dataState.observe(this,{dataState->
            when(dataState)
            {
                is Loading->
                {
                    binding.pbNews.visibility= View.VISIBLE
                    showMsg("Loading")
                }
                is Error->
                {
                    binding.pbNews.visibility= View.GONE
                    showMsg(dataState.exception.localizedMessage)
                }
                is Success->
                {
                    viewModel.list = dataState.data
                    initData()
                    binding.pbNews.visibility= View.GONE
                    adapter.notifyDataSetChanged()
                }
            }
        })

        binding.logo.setOnClickListener{
            onBackPressed()
        }
    }

    private fun showMsg(s:String)
    {
        Toast.makeText(this,s, Toast.LENGTH_LONG).show()
    }

    override fun onClicked(newsId: Int) {
        TODO("Not yet implemented")
    }

    override fun onUnSaveClicked(newsId: Int) {
        viewModel.list.removeAt(newsId)
        adapter.notifyItemRemoved(newsId)
    }
}