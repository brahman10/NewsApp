package com.example.newsappassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.newsappassignment.R
import com.example.newsappassignment.data.DataState
import com.example.newsappassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initObservers()
        viewModel.setState(MainStateEvent.GetNewsEvent)

    }

    private fun initObservers()
    {
        viewModel.dataState.observe(this,{dataState->
            when(dataState)
            {
                is DataState.Loading->
                {
                    showMsg("loading")
                }
                is DataState.Error->
                {
                    showMsg(dataState.exception.localizedMessage)
                }
                is DataState.Success->
                {
                    showMsg("${dataState.data}")
                }
            }
        })
    }

    private fun showMsg(s:String)
    {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }
}