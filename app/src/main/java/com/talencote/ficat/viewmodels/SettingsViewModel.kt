package com.talencote.ficat.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.talencote.ficat.api.ApiClient
import com.talencote.ficat.api.SessionManager
import com.talencote.ficat.data.dto.FandomsDto
import com.talencote.ficat.data.dto.MessageResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private var apiClient: ApiClient = ApiClient()
    private val context = getApplication<Application>().applicationContext
    private var sessionManager: SessionManager = SessionManager(context)

    private val _data = MutableLiveData<String>().apply {
        value = null
    }
    val data: LiveData<String> = _data

    fun setFandoms(fandoms: String) {
        apiClient.getApiService(context).setFandomsString(sessionManager.fetchUser().id, FandomsDto(fandoms))
                .enqueue(object : Callback<MessageResponse> {
                    override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                        Toast.makeText(context, "SettingsFragment - onFailure - $t", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                        Toast.makeText(context, "Fandoms saved", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    init {
        viewModelScope.launch {
            val fandoms = apiClient.getApiService(context).getFandomsString(sessionManager.fetchUser().id).message
            _data.postValue(fandoms)
        }
    }
}