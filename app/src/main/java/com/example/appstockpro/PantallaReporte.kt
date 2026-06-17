package com.example.appstockpro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun PantallaReporte(
    navController: NavController,
    viewModel: StockViewModel
) {
    val total = viewModel.calcularValorTotalInventario()
    val enCero = viewModel.contarProductosEnCero()

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Capital Invertido Total", style = MaterialTheme.typography.titleLarge)
        Text(
            text = "$${"%.2f".format(total)}",
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Productos con stock en cero: $enCero")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al Catálogo")
        }
    }
}