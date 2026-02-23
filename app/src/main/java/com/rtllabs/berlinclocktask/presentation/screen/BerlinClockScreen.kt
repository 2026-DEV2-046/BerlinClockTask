package com.rtllabs.berlinclocktask.presentation.screen

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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import com.rtllabs.berlinclocktask.presentation.BerlinClockUiState
import com.rtllabs.berlinclocktask.ui.theme.BerlinClockTaskTheme
import com.rtllabs.berlinclocktask.utils.SegmentRowType
import com.rtllabs.berlinclocktask.utils.SegmentShape
import kotlinx.coroutines.flow.MutableStateFlow
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BerlinRow(
                    uiState.secondsRow,
                    shape = SegmentShape.CIRCLE,
                    segmentRowType = SegmentRowType.ONE_SEGMENT_LAMP
                )
                BerlinRow(
                    uiState.fiveHoursRow,
                )
                BerlinRow(
                    uiState.oneHoursRow,
                )
                BerlinRow(
                    uiState.fiveMinutesRow,
                    segmentRowType = SegmentRowType.ELEVEN_SEGMENT_LAMP
                )
                BerlinRow(
                    uiState.oneMinutesRow,
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun BerlinClockScreenPreview() {
    val previewState = BerlinClockUiState(
        currentTime = "12:34:56",
        secondsRow = BerlinClockRow(listOf(BerlinClockSegment(true, SegmentColor.YELLOW))),
        fiveHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        oneHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        fiveMinutesRow = BerlinClockRow(List(11) {
            BerlinClockSegment(
                (it+1) % 3 == 0,
                SegmentColor.RED
            )
        }),
        oneMinutesRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 1, SegmentColor.YELLOW) })
    )

    val stateFlow = MutableStateFlow(previewState)
    BerlinClockTaskTheme {
        BerlinClockScreen("Berlin Clock", uiStateFlow = stateFlow)
    }
}