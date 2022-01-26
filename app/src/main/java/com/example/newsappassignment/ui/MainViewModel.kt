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

    fun setState(mainStateEvent:MainStateEvent)
    {
        viewModelScope.launch {
            when(mainStateEvent)
            {
                is MainStateEvent.GetNewsEvent->
                {
                    mainRepo.getAllNews().onEach { dataState ->
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
}

sealed class MainStateEvent{
    object GetNewsEvent:MainStateEvent()
    object None:MainStateEvent()
}