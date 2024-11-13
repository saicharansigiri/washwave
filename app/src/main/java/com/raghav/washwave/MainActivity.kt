package com.raghav.washwave

import android.content.Context
import android.content.Intent
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
import com.raghav.washwave.ui.components.BottomBar
import com.raghav.washwave.ui.navigation.BottomScreens
import com.raghav.washwave.ui.navigation.CheckoutScreen
import com.raghav.washwave.ui.navigation.HomeScreen
import com.raghav.washwave.ui.navigation.OrderScreen
import com.raghav.washwave.ui.navigation.RateListScreen
import com.raghav.washwave.ui.screens.CheckoutScreen
import com.raghav.washwave.ui.screens.HomeScreen
import com.raghav.washwave.ui.screens.MyOrderScreen
import com.raghav.washwave.ui.screens.RatesScreen
import com.raghav.washwave.ui.theme.WashWaveTheme
import com.raghav.washwave.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    companion object {
        const val TAG = "MainActivity"

        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WashWaveTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar(navController) },

                    ) { innerPadding ->
                    AppRoot(innerPadding, navController)
                }
            }
        }
    }
}

@Composable
fun AppRoot(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomScreens.Home.route,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {

        composable<HomeScreen> {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewmodel = viewModel,
                navHostController = navController
            )
        }

        composable<OrderScreen> {
            MyOrderScreen()
        }

        composable<RateListScreen> {
            RatesScreen()
        }

        composable<CheckoutScreen> {
            CheckoutScreen()
        }

    }

}