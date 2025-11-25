package com.example.ictemergencymangementmodelict_emm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ictemergencymangementmodelict_emm.ui.theme.ICTEmergencyMangementModelICTEMMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ICTEmergencyMangementModelICTEMMTheme {
                ICTEmergencyMangementModelICTEMMApp()
            }
        }
    }
}

@Composable
fun ICTEmergencyMangementModelICTEMMApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val destinations = AppDestinations.entries
    val currentDestination = destinations.find { it.route == currentRoute }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            destinations.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            destination.icon,
                            contentDescription = destination.label
                        )
                    },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = {
                        navController.navigate(destination.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppDestinations.HOME.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(AppDestinations.HOME.route) {
                    WelcomeScreen(onNavigateToMessages = { navController.navigate("messages") })
                }
                composable(AppDestinations.FAVORITES.route) {
                    Text("Favorites Screen")
                }
                composable(AppDestinations.PROFILE.route) {
                    ProfileScreen(onNavigateToPolicy = { navController.navigate("policy") })
                }
                composable("policy") {
                    UserPolicyScreen(onBack = { navController.popBackStack() })
                }
                composable("messages") {
                    MessageScreen(onBack = { navController.popBackStack() })
                }
            }
        }
    }
}

enum class AppDestinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    HOME("home", "Home", Icons.Default.Home),
    FAVORITES("favorites", "Favorites", Icons.Default.Favorite),
    PROFILE("profile", "Profile", Icons.Default.AccountBox),
}
