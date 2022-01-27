package com.example.newsappassignment.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.newsappassignment.R
import com.example.newsappassignment.data.DataState
import com.example.newsappassignment.databinding.ActivityMainBinding
import com.example.newsappassignment.ui.MainStateEvent
import com.example.newsappassignment.ui.MainViewModel
import com.example.newsappassignment.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NewsAdapter.OnItemClickListener {
    private val viewModel : MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initObservers()
        initListeners()
        viewModel.setState(MainStateEvent.GetNewsEvent)
    }

    private fun initListeners(){
        binding.cvSave.setOnClickListener {
            startActivity(Intent(this,SaveActivity::class.java))
        }
        binding.etSearch.addTextChangedListener {
            if(it.toString().length>3)
            {
                viewModel.searchQuery = it.toString()
                viewModel.setState(MainStateEvent.SearchNewsEvent)
            }
            else
            {
                viewModel.searchQuery = "all"
                viewModel.setState(MainStateEvent.GetNewsEvent)
            }
        }
    }


    private fun initData()
    {
        adapter = NewsAdapter(this,viewModel.list,this)
        binding.rvNews.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun initObservers()
    {
        viewModel.dataState.observe(this,{dataState->
            when(dataState)
            {
                is DataState.Loading->
                {
                    binding.pbNews.visibility= View.VISIBLE
                    showMsg("Loading")
                }
                is DataState.Error->
                {
                    binding.pbNews.visibility= View.GONE
                    showMsg(dataState.exception.localizedMessage)
                }
                is DataState.Success->
                {
                    viewModel.list = dataState.data
                    initData()
                    binding.pbNews.visibility= View.GONE
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun showMsg(s:String)
    {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }

    override fun onReadClicked(newsId: Int) {
        showMsg("onReadClicked $newsId")
    }

    override fun onSaveClicked(newsId: Int) {
        viewModel.saveArticle(newsId)
        showMsg("Article Saved Successfully")
    }

    override fun onUnSaveClicked(newsId: Int) {
        viewModel.UnSaveArticle(newsId)
        showMsg("Article UnSaved Successfully")
    }
}