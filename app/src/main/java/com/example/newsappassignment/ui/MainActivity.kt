package com.example.newsappassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.newsappassignment.R
import com.example.newsappassignment.data.Articles
import com.example.newsappassignment.data.DataState
import com.example.newsappassignment.databinding.ActivityMainBinding
import com.example.newsappassignment.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NewsAdapter.OnItemClickListener {
    private val viewModel : MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NewsAdapter
    var list= arrayListOf<Articles>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //supportActionBar?.hide()
        initObservers()
        viewModel.setState(MainStateEvent.GetNewsEvent)

    }

    private fun initData()
    {
        adapter = NewsAdapter(this,list,this)
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
                    list = dataState.data
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
        showMsg("onSaveClicked $newsId")
    }
}