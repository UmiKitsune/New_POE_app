package com.example.poe_app.presentation.utils

sealed class MyStates {

    object EmptyField: MyStates()

    object FilledField: MyStates()

    object Loading: MyStates()

    object Success: MyStates()
}