package com.rtllabs.berlinclocktask.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import com.rtllabs.berlinclocktask.presentation.BerlinClockUiState
import com.rtllabs.berlinclocktask.ui.theme.BerlinClockTaskTheme
import com.rtllabs.berlinclocktask.utils.SegmentRowType
import com.rtllabs.berlinclocktask.utils.SegmentShape
import com.rtllabs.berlinclocktask.utils.TAG_FOR_TESTING_ROW
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BerlinRow(
    row: BerlinClockRow,
    shape: SegmentShape = SegmentShape.RECTANGLE,
    segmentRowType: SegmentRowType=SegmentRowType.FOUR_SEGMENT_LAMP
) {

    Row(
        modifier = Modifier.testTag(TAG_FOR_TESTING_ROW),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        row.segments.forEach { segment ->
            BerlinSegment(segment = segment, shape, segmentRowType)
        }
    }
}

@PreviewLightDark
@Composable
fun BerlinRowPreview() {
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

    val stateFlow = MutableStateFlow(previewState)
    val row = stateFlow.collectAsState().value.secondsRow
    BerlinClockTaskTheme {
        BerlinRow(row = row)
    }
}


