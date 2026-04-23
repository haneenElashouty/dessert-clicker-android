package com.example.dessertclicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dessertclicker.ui.theme.DessertClickerTheme
import com.example.dessertclicker.viewmodel.DessertViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // 1. Initialize the ViewModel
            val viewModel: DessertViewModel = viewModel()

            // 2. Collect the State (The "Observer")
            val uiState by viewModel.uiState.collectAsState()

            // 3. Pass data down, and events up
            DessertClickerScreen(
                revenue = uiState.revenue,
                dessertsSold = uiState.dessertsSold,
                currentDessertImageId = uiState.currentDessertImageId,
                onDessertClick = { viewModel.onDessertClicked() }
            )
            }
        }
    }



@Composable

fun DessertClickerScreen(
    revenue: Int,
    dessertsSold: Int,
    currentDessertImageId: Int,
    onDessertClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TransactionInfo(revenue, dessertsSold)


            Spacer(modifier = Modifier.height(32.dp))


            DessertImage(
                imageId = currentDessertImageId,
                onClick = onDessertClick
            )
        }
    }
}

@Composable
fun TransactionInfo(revenue: Int, dessertsSold: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Total Revenue: $$revenue",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Desserts Sold: $dessertsSold",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DessertImage(imageId: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,

        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DessertClickerTheme {
        // We manually provide the data instead of asking the ViewModel
        DessertClickerScreen(
            revenue = 50, // Dummy data for preview
            dessertsSold = 5, // Dummy data for preview
            currentDessertImageId = R.drawable.nuts, // Use a real resource ID
            onDessertClick = { /* Do nothing in preview */ }
        )
    }
}