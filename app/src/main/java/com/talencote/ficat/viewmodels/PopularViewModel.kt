package com.talencote.ficat.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.talencote.ficat.api.ApiClient
import com.talencote.ficat.data.dto.FanficDto
import kotlinx.coroutines.launch

class PopularViewModel(application: Application) : AndroidViewModel(application) {

    private var apiClient : ApiClient = ApiClient()
    private val context = getApplication<Application>().applicationContext

    private val _data = MutableLiveData<List<FanficDto>>().apply {
        value = null
    }
    val data: LiveData<List<FanficDto>> = _data

    init {
        viewModelScope.launch {
            val fanfics = apiClient.getApiService(context).popular(1)
            _data.postValue(fanfics)
        }
    }
}