package com.raghav.washwave.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.raghav.washwave.ui.navigation.BottomScreens

@Composable
fun BottomBar(navController: NavController) {

    val screens = remember {
        listOf(
            BottomScreens.Home,
            BottomScreens.Orders,
            BottomScreens.Rates,
        )
    }

    val showBottomNav = screens.any { screen ->
        navController.currentBackStackEntryAsState().value?.destination?.route == screen.route::class.qualifiedName
    }

    val unselectedIconColor = Color(0xFF094BAC).copy(alpha = 0.5f)

    if(showBottomNav){
        NavigationBar {
            // Wrap the NavigationBar in a Box for background customization
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            screens.forEach { screen ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == screen.route::class.qualifiedName } == true

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(screen.icon),
                            contentDescription = screen.name,
                            tint = if (isSelected) MaterialTheme.colorScheme.primary else unselectedIconColor
                        )
                    },
                    label = {
                        Text(
                            text = screen.name,
                            style = MaterialTheme.typography.bodyMedium, // Use theme typography
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else unselectedIconColor
                        )
                    },
                    alwaysShowLabel = true // Always show labels even when unselected
                )
            }
        }
    }
}