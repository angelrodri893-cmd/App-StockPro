package com.example.appstockpro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaEdicion(
    navController: NavController,
    viewModel: StockViewModel,
    productoId: Int
) {
    val producto = viewModel.obtenerProducto(productoId)

    if (producto == null) {
        Text("Producto no encontrado")
        return
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(producto.nombre, style = MaterialTheme.typography.headlineSmall)
        Text(producto.descripcion, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${producto.stockActual}",
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(
                onClick = { viewModel.actualizarStock(productoId, producto.stockActual - 1) },
                enabled = producto.stockActual > 0
            ) { Text("-1") }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { viewModel.actualizarStock(productoId, producto.stockActual + 1) }
            ) { Text("+1") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Guardar y Volver")
        }
    }
}

