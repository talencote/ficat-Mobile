package com.talencote.ficat.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.talencote.ficat.api.ApiClient
import com.talencote.ficat.api.SessionManager
import com.talencote.ficat.data.dto.FanficDto
import kotlinx.coroutines.launch

class ForYouViewModel(application: Application) : AndroidViewModel(application) {

    private var apiClient : ApiClient = ApiClient()
    private val context = getApplication<Application>().applicationContext
    private var sessionManager: SessionManager = SessionManager(context)

    private val _data = MutableLiveData<List<FanficDto>>().apply {
        value = null
    }
    val data: LiveData<List<FanficDto>> = _data

    init {
        viewModelScope.launch {
            val fanfics = apiClient.getApiService(context).foryou(1, sessionManager.fetchUser().id)
            _data.postValue(fanfics)
        }
    }
}