package com.example.appstockpro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun PantallaCatalogo(

    navController: NavController,
    viewModel: StockViewModel,
    nombreOperario: String

){

    var mostrarCriticos by remmenber { mutableStateOf(false)}

    val listaMostrada = if (mostrarCriticos)
        viewModel.obtenerProductosEnRiesgo()
    else
        viewModel.productos

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onCLick = { navController.navigate("reporte") }) {
                Icon(Icons.Filled.Assessment, contentDescription = "Reporte")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()){

            Text(
                text = "Operario: $nombreOperario",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Button(onClick = { mostrarCriticos = false }) { Text("Ver Todo") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { mostrarCriticos = true }) { Text("Stock Crítico") }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(listaMostrada) { producto ->
                    Card(
                        modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clickable {navController.navigate("edicion/${producto.id}")}
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Precio: $${producto.precio}")
                            Text(
                                text = "Stock: ${producto.stockActual}",
                                color = if (producto.stockActual < 5) Color.Red else Color.Unspecified
                            )
                        }
                    }
                }
            }
        }
    }
}