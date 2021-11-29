package com.nac.module_main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    var data = mutableStateOf("main content")

    var localData = repository.getLocalData()

    fun getData() {
        viewModelScope.launch {
            data.value = repository.getData().toString()
        }
    }

    fun saveLocal() {
        viewModelScope.launch {
            repository.saveLocal(data.value)
        }
    }
}