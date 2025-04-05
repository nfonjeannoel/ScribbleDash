package com.camgist.scribbledash.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.camgist.scribbledash.R
import com.camgist.scribbledash.core.presentation.Route
import com.camgist.scribbledash.ui.theme.NavBarBackground
import com.camgist.scribbledash.ui.theme.NavBarSelected
import com.camgist.scribbledash.ui.theme.NavBarUnselected
import com.camgist.scribbledash.ui.theme.White

data class BottomNavigationItem(
    val route: String,
    val title: String,
    val iconResourceId: Int
)

@Composable
fun ScribbleDashBottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavigationItem(
            route = Route.HomeScreen.route,
            title = "Home",
            iconResourceId = R.drawable.bottom_nav_star
        ),
        BottomNavigationItem(
            route = Route.SettingsScreen.route,
            title = "Stats",
            iconResourceId = R.drawable.bottom_nav_stats
        )
    )
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .background(White),
        containerColor = White,
        tonalElevation = 0.dp,
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResourceId),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) NavBarSelected else NavBarUnselected
                    )
                },
                label = null,
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(Route.HomeScreen.route) {
                                saveState = true
                            }
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavBarSelected,
                    indicatorColor = White,
                    unselectedIconColor = NavBarUnselected
                )
            )
        }
    }
} 
