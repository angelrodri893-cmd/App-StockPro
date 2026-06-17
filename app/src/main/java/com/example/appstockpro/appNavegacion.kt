package com.example.appstockpro

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavegacion() {

    val navController = rememberNavController()

    //viewModel compartido entre pantallas
    val viewModel: StockViewModel = viewModel()

    NavHost(navController, startDestination = "login"){
        composable("login") {
            PantallaLogin(navController)
        }
        composable("catalogo/{nombreOperario}") { backStack ->
            val nombre = backStack.arguments?.getString("nombreOperario") ?: ""
            PantallaCatalogo(navController, viewModel, nombre)
        }
        composable("edicion/{productoId}") { backStack ->
            val id = backStack.arguments?.getString("productoId")?.toInt() ?: 0
            PantallaEdicion(navController, viewModel, id)
        }
        composable("reporte") {
            PantallaReporte(navController, viewModel)
        }
    }
}