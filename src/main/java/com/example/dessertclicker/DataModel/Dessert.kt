package com.example.dessertclicker.DataModel

data class Dessert(
    val imageId: Int,    // The resource ID for the image (e.g., R.drawable.cupcake)
    val price: Int,      // How much money you get per click
    val startProductionAmount: Int // How many total clicks are needed to unlock this dessert
)
