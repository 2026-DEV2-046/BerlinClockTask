package com.rtllabs.berlinclocktask.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import com.rtllabs.berlinclocktask.presentation.BerlinClockUiState
import com.rtllabs.berlinclocktask.ui.theme.BerlinClockTaskTheme
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.rtllabs.berlinclocktask.utils.TAG_FOR_TESTING_ROW

@Composable
fun BerlinRow(row: BerlinClockRow, lampSize: Dp, isCircle: Boolean= false) {
    Row(
        modifier = Modifier.testTag(TAG_FOR_TESTING_ROW),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        row.segments.forEach { segment ->
            BerlinSegment(segment = segment,lampSize,isCircle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BerlinRowPreview() {
    val previewState = BerlinClockUiState(
        currentTime = "12:34:56",
        secondsRow = BerlinClockRow(listOf(BerlinClockSegment(true, SegmentColor.YELLOW))),
        fiveHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        oneHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        fiveMinutesRow = BerlinClockRow(List(11) { BerlinClockSegment(it % 3 == 0, SegmentColor.YELLOW) }),
        oneMinutesRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 1, SegmentColor.YELLOW) }) )

    val stateFlow = remember { MutableStateFlow(previewState) }
    val row=stateFlow.collectAsState().value.secondsRow
    BerlinClockTaskTheme {
        BerlinRow(row = row, lampSize = 20.dp,)
    }
}


