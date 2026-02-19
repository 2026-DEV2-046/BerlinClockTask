package com.rtllabs.berlinclocktask.domain.entity

data class BerlinClock(
    val secondsRow: BerlinClockRow,
    val fiveHoursRow: BerlinClockRow,
    val oneHoursRow: BerlinClockRow,
    val fiveMinutesRow: BerlinClockRow,
)
