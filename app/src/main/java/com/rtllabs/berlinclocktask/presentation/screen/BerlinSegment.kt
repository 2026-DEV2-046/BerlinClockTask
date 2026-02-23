package com.rtllabs.berlinclocktask.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.rtllabs.berlinclocktask.utils.TAG_FOR_TESTING_SEGMENT
import com.rtllabs.berlinclocktask.utils.toShape
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BerlinSegment(
    segment: BerlinClockSegment,
    shape: SegmentShape = SegmentShape.RECTANGLE,
    segmentRowType: SegmentRowType=SegmentRowType.FOUR_SEGMENT_LAMP
) {

    val boxSizeModifier = when (segmentRowType) {
        SegmentRowType.ONE_SEGMENT_LAMP -> Modifier.size(60.dp)
        SegmentRowType.ELEVEN_SEGMENT_LAMP -> Modifier.size(28.dp, 40.dp)
        SegmentRowType.FOUR_SEGMENT_LAMP ->  Modifier.size(90.dp, 40.dp)
    }

    Box(
        modifier = boxSizeModifier
            .testTag("$TAG_FOR_TESTING_SEGMENT${segment.color}-${segment.isLampOn}-${segmentRowType}-${shape}")
            .background(
                when (segment.color) {
                    SegmentColor.RED -> if (segment.isLampOn) Color.Red else Color.DarkGray
                    SegmentColor.YELLOW -> if (segment.isLampOn) Color.Yellow else Color.DarkGray
                    SegmentColor.GRAY -> Color.DarkGray
                },
                shape =  shape.toShape()
            )
    )
}

@PreviewLightDark
@Composable
fun BerlinSegmentPreview() {
    val previewState = BerlinClockUiState(
        currentTime = "12:34:56",
        secondsRow = BerlinClockRow(listOf(BerlinClockSegment(true, SegmentColor.YELLOW))),
        fiveHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        oneHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 2, SegmentColor.RED) }),
        fiveMinutesRow = BerlinClockRow(List(11) {
            BerlinClockSegment(
                false,
                SegmentColor.GRAY
            )
        }),
        oneMinutesRow = BerlinClockRow(List(4) { BerlinClockSegment(it < 1, SegmentColor.YELLOW) })
    )

    val stateFlow = MutableStateFlow(previewState)
    val row = stateFlow.collectAsState().value.secondsRow.segments.first()
    BerlinClockTaskTheme {
        BerlinSegment(segment = row)
    }
}