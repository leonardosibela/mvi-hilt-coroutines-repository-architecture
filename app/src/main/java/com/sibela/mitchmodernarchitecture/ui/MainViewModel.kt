package com.sibela.mitchmodernarchitecture.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.sibela.mitchmodernarchitecture.model.Blog
import com.sibela.mitchmodernarchitecture.repository.MainRepository
import com.sibela.mitchmodernarchitecture.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {

        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogsEvents -> {
                    mainRepository.getBlogs()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {
                    // who cares
                }
            }
        }

    }

    sealed class MainStateEvent {

        object GetBlogsEvents : MainStateEvent()

        object None : MainStateEvent()
    }

}