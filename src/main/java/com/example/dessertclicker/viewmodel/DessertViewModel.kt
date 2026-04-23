package com.example.dessertclicker.viewmodel

import com.example.dessertclicker.DataModel.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel {
    // 1. The "Secret Vault" (Private & Mutable)
    private val _uiState = MutableStateFlow(DessertUiState())

    // 2. The "Public Window" (Public & Read-only)
    // This is what the UI will "collect"
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update { currentState ->
            val dessertSold = currentState.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertSold)

            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = dessertSold,
                currentDessertIndex = nextDessertIndex,
                currentDessertImageId = Datasource.dessertList[nextDessertIndex].imageId,
                currentDessertPrice = Datasource.dessertList[nextDessertIndex].price
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in Datasource.dessertList.indices) {
            if (dessertsSold >= Datasource.dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list is sorted by startProductionAmount,
                // so we can stop as soon as we find one we haven't reached.
                break
            }
        }
        return dessertIndex
    }

}