package com.rtllabs.berlinclocktask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rtllabs.berlinclocktask.screen.BerlinClockScreen
import com.rtllabs.berlinclocktask.ui.theme.BerlinClockTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BerlinClockTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BerlinClockScreen(
                        name = "Berlin Clock",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BerlinClockTaskTheme {
        BerlinClockScreen("Berlin Clock")
    }
}