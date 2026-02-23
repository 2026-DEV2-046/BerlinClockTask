package com.rtllabs.berlinclocktask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rtllabs.berlinclocktask.presentation.BerlinClockViewModel
import com.rtllabs.berlinclocktask.presentation.screen.BerlinClockScreen
import com.rtllabs.berlinclocktask.ui.theme.BerlinClockTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: BerlinClockViewModel = hiltViewModel()


            BerlinClockTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BerlinClockScreen(
                        name = getString(R.string.app_name),
                        modifier = Modifier.padding(innerPadding),
                        uiStateFlow = viewModel.uiState
                    )
                }
            }
        }
    }
}