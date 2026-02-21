package com.rtllabs.berlinclocktask.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import com.rtllabs.berlinclocktask.presentation.BerlinClockUiState
import com.rtllabs.berlinclocktask.ui.theme.BerlinClockTaskTheme
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BerlinSegment(segment: BerlinClockSegment, lampSize: Dp) {
    Box(
        modifier = Modifier
            .size(lampSize)
            .testTag("segment-${segment.color}-${segment.isLampOn}")
            .background(
                when (segment.color) {
                    SegmentColor.RED -> if (segment.isLampOn) Color.Red else Color.DarkGray
                    SegmentColor.YELLOW -> if (segment.isLampOn) Color.Yellow else Color.DarkGray
                    SegmentColor.GRAY -> Color.DarkGray
                },
                shape = CircleShape
            )
    )
}

@Preview(showBackground = true)
@Composable
fun BerlinSegmentPreview() {
    val previewState = BerlinClockUiState(
        currentTime = "12:34:56",
        secondsRow = BerlinClockRow(listOf(BerlinClockSegment(true, SegmentColor.YELLOW))),
        fiveHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        oneHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        fiveMinutesRow = BerlinClockRow(List(11) { BerlinClockSegment(it % 3 == 0, SegmentColor.YELLOW) }),
        oneMinutesRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 1, SegmentColor.YELLOW) }) )

    val stateFlow = remember { MutableStateFlow(previewState) }
    val row=stateFlow.collectAsState().value.secondsRow.segments.first()
    BerlinClockTaskTheme {
        BerlinSegment(segment = row, lampSize = 20.dp)
    }
}