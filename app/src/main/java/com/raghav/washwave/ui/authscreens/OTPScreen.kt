package com.raghav.washwave.ui.authscreens


import OTPInput
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raghav.washwave.R
import com.raghav.washwave.ui.components.AuthBottomSheet
import com.raghav.washwave.ui.viewmodels.OTPScreenUiState
import com.raghav.washwave.ui.viewmodels.OTPViewModel


@Composable
fun OTPScreen(
    viewModel: OTPViewModel,
    mobileNumber: String,
    onSuccess: () -> Unit,
    onMobileNumberChange: () -> Unit
) {
    viewModel.resetUiState()
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    LaunchedEffect(Unit) {
        if (activity != null) {
            viewModel.updatePhoneNumber(mobileNumber,activity)
        }
    }
    var otp by remember { mutableStateOf("") }


    val state = viewModel.uiState.collectAsState()

    if (state.value is OTPScreenUiState.Error) {
        Toast.makeText(
            context,
            (state.value as OTPScreenUiState.Error).message,
            Toast.LENGTH_SHORT
        ).show()
    }

    if (state.value is OTPScreenUiState.Success) {
        onSuccess.invoke()
        viewModel.resetUiState()
    }

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus() // Clears focus and hides the keyboard
                })
            },
    ) {
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Spacer(modifier = Modifier.weight(1f))
            // Logo
            Image(
                painter = painterResource(id = R.drawable.my_logo),
                contentDescription = "TGT Logo",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Sheet
            AuthBottomSheet(
                title = stringResource(R.string.enter_verification_code),
                description = "Please enter the 6-digit code sent to your registered mobile number",
                content = {
                    OTPInput { enteredOTP ->
                        otp =enteredOTP
                    }
                },
                buttonText = stringResource(R.string.sign_in),
                onButtonClick = {
                    onSuccess.invoke()
                },
                mobileNumber = mobileNumber,
                onMobileNumberEditClick = onMobileNumberChange,
                isLoading = state.value is OTPScreenUiState.Loading
            )
        }
    }
}

