package com.raghav.washwave.ui.viewmodels

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(
) : ViewModel() {

    companion object {
        const val TAG = "OTPViewModel"
    }

    private val _uiState = MutableStateFlow<OTPScreenUiState>(OTPScreenUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private var verificationId: String? = null

    private val _phoneNumber = MutableStateFlow("")

    fun updatePhoneNumber(phoneNumber: String, activity: ComponentActivity) {
        _phoneNumber.value = "+91$phoneNumber"
        verifyPhoneNumber(activity)
    }

    private fun verifyPhoneNumber(activity: ComponentActivity) {
        viewModelScope.launch {
            _uiState.value = OTPScreenUiState.Loading

        }
    }

    fun verifyOtp(enteredOTP: String) {

    }

    private fun createPartnerProfile(name: String, phone: String) {

    }


    fun resetUiState() {
        _uiState.value = OTPScreenUiState.Idle
    }
}

sealed class OTPScreenUiState {
    data object Idle : OTPScreenUiState()
    data object Loading : OTPScreenUiState()
    data class Error(val message: String) : OTPScreenUiState()
    data object OTPSent : OTPScreenUiState()
    data object Success : OTPScreenUiState()
}