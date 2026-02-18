package com.rtllabs.berlinclocktask.domain

import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment

class BerlinClockConverter {
    internal fun generateSecondsRow(seconds: Int): BerlinClockSegment {
        return BerlinClockSegment(
            isLampOn = seconds % 2 == 0
        )
    }
}