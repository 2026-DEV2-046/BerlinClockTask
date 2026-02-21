package com.rtllabs.berlinclocktask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import com.rtllabs.berlinclocktask.presentation.BerlinClockUiState
import com.rtllabs.berlinclocktask.presentation.BerlinClockViewModel
import com.rtllabs.berlinclocktask.screen.BerlinClockScreen
import com.rtllabs.berlinclocktask.ui.theme.BerlinClockTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

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
                        name = "Berlin Clock",
                        modifier = Modifier.padding(innerPadding),
                        uiStateFlow = viewModel.uiState
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val previewState = BerlinClockUiState(
        currentTime = "12:34:56",
        secondsRow = BerlinClockRow(listOf(BerlinClockSegment(true, SegmentColor.YELLOW))),
        fiveHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        oneHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        fiveMinutesRow = BerlinClockRow(List(11) { BerlinClockSegment(it % 3 == 0, SegmentColor.YELLOW) }),
        oneMinutesRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 1, SegmentColor.YELLOW) }) )

    val stateFlow = remember { MutableStateFlow(previewState) }
    BerlinClockTaskTheme {
        BerlinClockScreen("Berlin Clock", uiStateFlow = stateFlow)
    }
}