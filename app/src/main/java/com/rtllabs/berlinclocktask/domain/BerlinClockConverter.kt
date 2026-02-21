package com.rtllabs.berlinclocktask.domain

import com.rtllabs.berlinclocktask.domain.entity.BerlinClock
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import com.rtllabs.berlinclocktask.utils.ELEVEN_SEGMENT_LAMP_COUNT
import com.rtllabs.berlinclocktask.utils.FOUR_SEGMENT_LAMP_COUNT
import javax.inject.Inject

class BerlinClockConverter @Inject constructor() {

    fun convert(hour: Int, minute: Int, second: Int): BerlinClock {
        return BerlinClock(
            secondsRow = generateSecondsRow(second),
            fiveHoursRow = generateFiveHoursRow(hour),
            oneHoursRow = generateOneHoursRow(hour),
            fiveMinutesRow = generateFiveMinutesRow(minute),
            oneMinutesRow = generateOneMinutesRow(minute)
        )
    }

    private fun generateSecondsRow(seconds: Int): BerlinClockRow {
        return BerlinClockRow(
            segments = listOf(
                BerlinClockSegment(
                    isLampOn = seconds % 2 == 0,
                    color = if (seconds % 2 == 0) SegmentColor.YELLOW else SegmentColor.GRAY
                )
            )
        )
    }

    private fun generateFiveHoursRow(hour: Int): BerlinClockRow {
        val result = hour / 5

        return BerlinClockRow(
            segments = List(FOUR_SEGMENT_LAMP_COUNT) { index ->
                BerlinClockSegment(
                    isLampOn = index < result,
                    color = if (index < result) SegmentColor.RED else SegmentColor.GRAY
                )
            }
        )
    }

    private fun generateOneHoursRow(hour: Int): BerlinClockRow {
        val remainder = hour % 5
        return BerlinClockRow(
            segments = List(FOUR_SEGMENT_LAMP_COUNT) { index ->
                BerlinClockSegment(
                    isLampOn = index < remainder,
                    color = if (index < remainder) SegmentColor.RED else SegmentColor.GRAY
                )
            }
        )
    }

    private fun generateFiveMinutesRow(minute: Int): BerlinClockRow {
        val result = minute / 5

        return BerlinClockRow(
            segments = List(ELEVEN_SEGMENT_LAMP_COUNT) { index ->
                val isOn = index < result
                val color =
                    if (isOn) if ((index + 1) % 3 == 0) SegmentColor.RED else SegmentColor.YELLOW else SegmentColor.GRAY
                BerlinClockSegment(
                    isLampOn = isOn,
                    color = color
                )
            }
        )
    }

    private fun generateOneMinutesRow(minute: Int): BerlinClockRow {
        val remainder = minute % 5
        return BerlinClockRow(
            segments = List(FOUR_SEGMENT_LAMP_COUNT) { index ->
                BerlinClockSegment(
                    isLampOn = index < remainder,
                    color = if (index < remainder) SegmentColor.YELLOW else SegmentColor.GRAY
                )
            }
        )
    }
}