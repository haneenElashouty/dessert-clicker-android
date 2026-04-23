package com.example.dessertclicker

import com.example.dessertclicker.viewmodel.DessertViewModel
import org.junit.Test
import org.junit.Assert.assertEquals

class DessertViewModelTest {
    @Test
    fun dessertViewModel_Click_RevenueAndSoldIncreased() {
        // Arrange
        val viewModel = DessertViewModel()

        // Act
        viewModel.onDessertClicked()

        // Assert
        val state = viewModel.uiState.value
        assertEquals(5, state.revenue) // First dessert is 5$
        assertEquals(1, state.dessertsSold)
    }
}