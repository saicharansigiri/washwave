package com.raghav.washwave.ui.viewmodels

import android.content.Context
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raghav.washwave.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    companion object {
        const val TAG = "LoginViewModel"
        const val SIX = "6"
        const val SEVEN = "7"
        const val EIGHT = "8"
        const val NINE = "9"
        const val VALID_NUMBER_LENGTH = 10
        const val VALID_NUMBER_MESSAGE = "Valid"

    }

    private val _uiState = MutableStateFlow<LoginScreenUiState>(LoginScreenUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _signInIntent = MutableStateFlow<IntentSender?>(null)
    val signInIntent: StateFlow<IntentSender?> = _signInIntent


    private val _phoneNumber = MutableStateFlow("")
    val phoneNumberState = _phoneNumber.asStateFlow()

    fun updatePhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }



    fun validatePhoneNumber(context: Context) {
        viewModelScope.launch {
            val phoneNumber = _phoneNumber.value
            _uiState.value = LoginScreenUiState.Loading
            val validationMessage = when {
                phoneNumber.isEmpty() -> context.getString(R.string.empty_number)
                phoneNumber.length != VALID_NUMBER_LENGTH -> context.getString(R.string.in_complete_number)
                !phoneNumber.all { it.isDigit() } -> context.getString(R.string.must_contain_digits)
                !phoneNumber.startsWith(SIX) && !phoneNumber.startsWith(SEVEN) &&
                        !phoneNumber.startsWith(EIGHT) && !phoneNumber.startsWith(NINE) -> {
                    context.getString(R.string.wrong_starting_digit)
                }

                else -> VALID_NUMBER_MESSAGE
            }

            // Update the state based on the validation result
            _uiState.value = if (validationMessage == VALID_NUMBER_MESSAGE) {
                delay(300L)
                LoginScreenUiState.Success
            } else {
                LoginScreenUiState.Error(validationMessage)
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginScreenUiState.Idle
    }


}

sealed class LoginScreenUiState {
    data object Idle : LoginScreenUiState()
    data object Loading : LoginScreenUiState()
    data object GoogleLoginSuccess: LoginScreenUiState()
    data class Error(val message: String) : LoginScreenUiState()
    data object Success : LoginScreenUiState()
}