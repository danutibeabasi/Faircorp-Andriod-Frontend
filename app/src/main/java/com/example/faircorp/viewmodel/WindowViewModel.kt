package com.example.faircorp.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.faircorp.FaircorpApplication
import com.example.faircorp.dao.WindowDao
import com.example.faircorp.api.ApiServices
import com.example.faircorp.model.Window
import com.example.faircorp.model.WindowDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WindowViewModel(private val windowDao: WindowDao) : ViewModel() {

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val windowDao = (extras[APPLICATION_KEY] as FaircorpApplication).database.windowDao()
                return WindowViewModel(windowDao) as T
            }
        }
    }
    fun findAll(): LiveData<List<WindowDto>> = liveData {
        val elements: List<WindowDto> = withContext(Dispatchers.IO) {
            try {
                val response = ApiServices.windowApiService.findAll().execute()
                withContext(Dispatchers.Main) {
                    networkState.value = State.ONLINE
                }
                val windows: List<WindowDto> = response.body() ?: emptyList()
                windows.apply {
                    windowDao.clearAll()
                    forEach {
                        windowDao.create(
                            Window(
                                id = it.id.toInt(),
                                name = it.name,
                                roomId = it.room.id.toInt(),
                                roomName = it.room.name,
                                windowStatus = it.windowStatus
                            )
                        )
                    }
                }
            } catch (e: Exception) {withContext(Dispatchers.Main) {
                networkState.value = State.OFFLINE
            }
                windowDao.findAll().map { it.toDto() }
            }
        } as List<WindowDto>
        emit(elements)
    }

    fun findById(windowId: Int): LiveData<WindowDto> = liveData {
        viewModelScope.launch(Dispatchers.IO) {
            emit(windowDao.findById(windowId).toDto())
        }
    }

    val networkState: MutableLiveData<State> by lazy {
        MutableLiveData<State>().also { it.value = State.ONLINE }
    }
}


