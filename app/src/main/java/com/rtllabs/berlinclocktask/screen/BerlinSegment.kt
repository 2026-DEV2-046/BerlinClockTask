package com.rtllabs.berlinclocktask.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import com.rtllabs.berlinclocktask.presentation.BerlinClockUiState
import com.rtllabs.berlinclocktask.ui.theme.BerlinClockTaskTheme
import com.rtllabs.berlinclocktask.utils.TAG_FOR_TESTING_SEGMENT
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BerlinSegment(
    segment: BerlinClockSegment,
    isCircle: Boolean = false,
    lampCount: Int = 0
) {
    val boxSizeModifier = when (lampCount) {
        1 -> {
            Modifier.size(60.dp)
        }

        11 -> {
            Modifier.size(28.dp, 40.dp)
        }

        else -> {
            Modifier.size(90.dp, 40.dp)
        }
    }

    Box(
        modifier = boxSizeModifier
            .testTag("$TAG_FOR_TESTING_SEGMENT${segment.color}-${segment.isLampOn}")
            .background(
                when (segment.color) {
                    SegmentColor.RED -> if (segment.isLampOn) Color.Red else Color.DarkGray
                    SegmentColor.YELLOW -> if (segment.isLampOn) Color.Yellow else Color.DarkGray
                    SegmentColor.GRAY -> Color.DarkGray
                },
                shape = if (isCircle) CircleShape else RoundedCornerShape(2.dp)
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
        fiveMinutesRow = BerlinClockRow(List(11) {
            BerlinClockSegment(
                it % 3 == 0,
                SegmentColor.YELLOW
            )
        }),
        oneMinutesRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 1, SegmentColor.YELLOW) })
    )

    val stateFlow = remember { MutableStateFlow(previewState) }
    val row = stateFlow.collectAsState().value.secondsRow.segments.first()
    BerlinClockTaskTheme {
        BerlinSegment(segment = row)
    }
}