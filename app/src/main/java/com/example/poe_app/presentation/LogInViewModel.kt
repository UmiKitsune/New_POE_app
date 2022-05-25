package com.example.poe_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.poe_app.presentation.utils.MyStates
import com.example.poe_app.presentation.utils.SingleLiveEvent

class LogInViewModel: ViewModel() {

    private val _state = SingleLiveEvent<MyStates>()
    val state: LiveData<MyStates> = _state


    fun onLoginBtnOnClick(userName: String) {
        if (userName.isEmpty()) {
            _state.value = MyStates.EmptyField
        } else {
            _state.value = MyStates.FilledField
        }
    }
}