package com.raghav.washwave.ui.authscreens


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.raghav.washwave.R
import com.raghav.washwave.ui.components.AuthBottomSheet
import com.raghav.washwave.ui.viewmodels.LoginScreenUiState
import com.raghav.washwave.ui.viewmodels.LoginViewModel
import com.raghav.washwave.ui.navigation.OTPScreen

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    val state: State<LoginScreenUiState> = viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val mobileNumber = viewModel.phoneNumberState.collectAsStateWithLifecycle()
    val signInIntent by viewModel.signInIntent.collectAsState()

    if (state.value is LoginScreenUiState.Success) {
        navController.navigate(OTPScreen(mobileNumber.value))
        viewModel.resetUiState()
    }
    if (state.value is LoginScreenUiState.GoogleLoginSuccess) {

    }
    val coroutineScope = rememberCoroutineScope()


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->

        }
    )

    LaunchedEffect(signInIntent) {
        signInIntent?.let { intentSender ->
            launcher.launch(IntentSenderRequest.Builder(intentSender).build())
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)),
    ) {
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

            AuthBottomSheet(
                title = stringResource(R.string.welcome_back),
                description = stringResource(R.string.enter_your_mobile_number_to_continue),
                content = {
                    Column {
//                        Spacer(modifier = Modifier.height(16.dp))
//                        SignInButton(
//                            signInClicked = {
//
//                            },
//                            icon = R.drawable.ic_google_logo,
//                            text = "Sign in with google"
//                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = mobileNumber.value,
                            onValueChange = { mobileNumber ->
                                viewModel.updatePhoneNumber(
                                    mobileNumber.take(10)
                                )
                            },
                            label = { Text(stringResource(R.string.mobile_number)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Phone
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (state.value is LoginScreenUiState.Error) {
                            Text(
                                text = (state.value as LoginScreenUiState.Error).message,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                },
                buttonText = stringResource(R.string.sign_in),
                onButtonClick = {
                    viewModel.validatePhoneNumber(context = context)
                },
                isLoading = state.value is LoginScreenUiState.Loading
            )
        }
    }


}
