package com.example.dessertclicker.viewmodel

import com.example.dessertclicker.R

data class DessertUiState(
    val revenue: Int = 0,         // Total money earned
    val dessertsSold: Int = 0,    // Total number of clicks
    val currentDessertIndex: Int = 0, // Which dessert from the list are we showing?
    val currentDessertPrice: Int = 5, // Price of the current dessert
    val currentDessertImageId: Int = R.drawable.nuts // Current image to display
)