package com.example.newsappassignment.ui

import androidx.lifecycle.*
import com.example.newsappassignment.data.Articles
import com.example.newsappassignment.data.DataState
import com.example.newsappassignment.repo.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject
constructor(
    private val mainRepo: MainRepo
):ViewModel(){
    private val _dataState :MutableLiveData<DataState<ArrayList<Articles>>> = MutableLiveData()
    val dataState : LiveData<DataState<ArrayList<Articles>>>
        get () = _dataState

    var searchQuery="all"

    var list= arrayListOf<Articles>()

    fun setState(mainStateEvent:MainStateEvent)
    {
        viewModelScope.launch {
            when(mainStateEvent)
            {
                is MainStateEvent.GetNewsEvent->
                {
                    mainRepo.getAllNews("all").onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
                is MainStateEvent.SearchNewsEvent->
                {
                    mainRepo.getAllNews(searchQuery).onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
                else->
                {
                    //left
                }
            }
        }
    }

    fun saveArticle(position:Int)
    {
        viewModelScope.launch {
            list[position].isSaved=true
            mainRepo.insertSavedNews(list[position])
        }
    }

    fun UnSaveArticle(position:Int)
    {
        list[position].isSaved=false
        viewModelScope.launch {
            list[position].url.let {
                it?.let { it1 -> mainRepo.removeArticle(it1) }
            }

        }
    }
}

sealed class MainStateEvent{
    object GetNewsEvent:MainStateEvent()
    object SearchNewsEvent:MainStateEvent()
    object None:MainStateEvent()
}