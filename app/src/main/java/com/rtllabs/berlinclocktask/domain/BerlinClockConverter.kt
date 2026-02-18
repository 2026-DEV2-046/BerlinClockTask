package com.rtllabs.berlinclocktask.domain

import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor

class BerlinClockConverter {
    internal fun generateSecondsRow(seconds: Int): BerlinClockSegment {
        return BerlinClockSegment(
            isLampOn = seconds % 2 == 0,
            color = if (seconds % 2 ==0) SegmentColor.YELLOW else SegmentColor.GRAY
        )
    }

    fun generateFiveHoursRow(hour: Int): List<Boolean> {
        return listOf(false, false, false, false)
    }
}