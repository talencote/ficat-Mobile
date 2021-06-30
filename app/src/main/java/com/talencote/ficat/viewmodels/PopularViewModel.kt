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
            var fanfics = apiClient.getApiService(context).popular(1)
            if (fanfics != null) {
                _data.postValue(fanfics)
            }
        }
    }
}