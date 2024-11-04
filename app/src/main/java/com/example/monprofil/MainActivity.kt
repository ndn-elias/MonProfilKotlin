package com.example.monprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.monprofil.ui.theme.MonProfilTheme
import kotlinx.serialization.Serializable
import androidx.navigation.NavDestination.Companion.hasRoute

@Serializable class DestinationProfile
@Serializable class DestinationFilm
@Serializable class DestinationSerie
@Serializable class DestinationActeur


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            MonProfilTheme {
                Scaffold(
                    bottomBar = {
                        if (currentDestination?.hasRoute<DestinationProfile>() == false) {
                            NavigationBar {

                                //1er item navbar
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            Icons.Filled.Search,
                                            contentDescription = "Catalogue des films"
                                        )
                                    },
                                    label = {Text("Films")},
                                    selected = currentDestination?.hasRoute<DestinationFilm>() == true,
                                    onClick = { navController.navigate(DestinationFilm()) })

                                //2e item navbar
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.List,
                                            contentDescription = "Catalogue des séries"
                                        )
                                    },
                                    label = {Text("Séries")},
                                    selected = currentDestination?.hasRoute<DestinationSerie>() == true,
                                    onClick = { navController.navigate(DestinationSerie()) })

                                //3e item navbar
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.Star,
                                            contentDescription = "Catalogue des acteurs",

                                        )
                                    },
                                    label = {Text("Acteurs")},
                                    selected = currentDestination?.hasRoute<DestinationActeur>() == true,
                                    onClick = { navController.navigate(DestinationActeur()) })


                            }

                        }

                    })
                { innerPadding ->
                    NavHost(navController = navController, startDestination = DestinationProfile()){
                        composable<DestinationProfile> {ProfileScreen(navController, innerPadding, windowSizeClass)}
                        composable<DestinationFilm> {FilmScreen(innerPadding, windowSizeClass)}
                        composable<DestinationSerie> {SerieScreen(innerPadding, windowSizeClass)}
                        composable<DestinationActeur> {ActeurScreen(innerPadding, windowSizeClass)}
                    }

                }
            }
        }
    }
}

