package com.talencote.ficat.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.talencote.ficat.api.ApiClient
import com.talencote.ficat.data.dto.FanficDto
import kotlinx.coroutines.launch

class FanficContentViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient: ApiClient = ApiClient()
    private val context = getApplication<Application>().applicationContext

    private val _data = MutableLiveData<FanficDto>().apply {
        value = null
    }
    val data: LiveData<FanficDto> = _data

    fun find(id: Int) {
        viewModelScope.launch {
            val fanfic = apiClient.getApiService(context).getFanfic(id)
            _data.postValue(fanfic)
        }
    }
}