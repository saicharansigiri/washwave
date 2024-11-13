package com.raghav.washwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.raghav.washwave.ui.authscreens.LoginScreen
import com.raghav.washwave.ui.authscreens.OTPScreen
import com.raghav.washwave.ui.navigation.BottomScreens
import com.raghav.washwave.ui.navigation.LoginScreen
import com.raghav.washwave.ui.navigation.OTPScreen
import com.raghav.washwave.ui.theme.WashWaveTheme
import com.raghav.washwave.ui.viewmodels.LoginViewModel
import com.raghav.washwave.ui.viewmodels.OTPViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WashWaveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    MyAuthRoot(navController, innerPadding){
                      MainActivity.startActivity(this)
                    }
                }
            }
        }
    }
}

@Composable
fun MyAuthRoot(
    navHostController: NavHostController,
    innerPadding: PaddingValues,
    onLoginSuccess: () -> Unit = {}
) {
    NavHost(
        navController = navHostController,
        startDestination = LoginScreen,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        composable<LoginScreen> { _ ->
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                viewModel,
                navHostController,
                innerPadding,
            )
        }

        composable<OTPScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<OTPScreen>()
            val viewModel = hiltViewModel<OTPViewModel>()
            OTPScreen(
                viewModel,
                mobileNumber = args.mobileNumber,
                onSuccess = onLoginSuccess,
                onMobileNumberChange = {
                    viewModel.resetUiState()
                    navHostController.popBackStack()
                }
            )
        }
    }
}
