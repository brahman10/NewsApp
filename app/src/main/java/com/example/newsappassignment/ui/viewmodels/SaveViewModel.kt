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
class SaveViewModel @Inject
constructor(
    //have not created different repo....as this is smaller project
    private val mainRepo: MainRepo
):ViewModel(){
    private val _dataState :MutableLiveData<DataState<ArrayList<Articles>>> = MutableLiveData()
    val dataState : LiveData<DataState<ArrayList<Articles>>>
        get () = _dataState

    var searchQuery=""

    var list= arrayListOf<Articles>()

    fun getSavedArticle()
    {
        viewModelScope.launch {
            mainRepo.getSavedNews().onEach { dataState ->
                _dataState.value = dataState
            }.launchIn(viewModelScope)
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
