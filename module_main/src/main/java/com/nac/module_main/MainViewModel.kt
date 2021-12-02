package com.nac.module_main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nac.export_mall.IMallService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val service: IMallService
) : ViewModel() {
    var data = mutableStateOf("main content")

    var localData = repository.getLocalData()

    fun getData() {
        viewModelScope.launch {
            data.value = service.getMallData()
        }
    }

    fun saveLocal() {
        viewModelScope.launch {
            repository.saveLocal(data.value)
        }
    }
}