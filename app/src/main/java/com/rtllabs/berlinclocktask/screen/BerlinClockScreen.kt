package com.rtllabs.berlinclocktask.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rtllabs.berlinclocktask.presentation.BerlinClockUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BerlinClockScreen(
    name: String, modifier: Modifier = Modifier, uiStateFlow: StateFlow<BerlinClockUiState>
) {
    val uiState by uiStateFlow.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = name, color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold
            )
            Text(
                text = uiState.currentTime,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }
}