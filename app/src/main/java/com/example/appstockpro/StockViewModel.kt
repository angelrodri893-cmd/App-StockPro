package com.example.appstockpro

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


class StockViewModel : ViewModel() {

    //aqui se simula una base de datos con una lista de productos.
    val productos = mutableStateListOf(
        Producto(1, "Laptop", "Laptop de alta gama con procesador Intel i7, 16GB RAM, 512GB SSD.", 1200.0, 10),
        Producto(2, "Smartphone", "Smartphone con pantalla AMOLED, 128GB almacenamiento, cámara de 48MP.", 800.0, 20),
        Producto(3, "Auriculares", "Auriculares inalámbricos con cancelación de ruido, batería de larga duración.", 150.0, 15),
        Producto(4, "Smartwatch", "Smartwatch con monitor de frecuencia cardíaca, GPS, resistente al agua.", 200.0, 8),
        Producto(5, "Tablet", "Tablet con pantalla de 10 pulgadas, 64GB almacenamiento, batería de larga duración.", 300.0, 12)
    )

    //aqui se agrega las funcionalidades del negocio, como obtener un producto por su ID, actualizar el stock, calcular el valor total del inventario, etc.

    fun obtenerProducto(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val index = productos.indexOfFirst { it.id == id }
        if (index != -1) {
            productos[index] = productos[index].copy(stockActual = nuevaCantidad)
        }
    }

    fun calcularValorTotalInventario(): Double {
        return productos.sumOf { it.precio * it.stockActual }
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {
        return productos.filter { it.stockActual == 0 }
    }

    fun contarProductosEnCero(): Int {
        return productos.count { it.stockActual == 0 }
    }
}