package com.rtllabs.berlinclocktask.presentation

import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow

data class BerlinClockUiState(
    val secondsRow: BerlinClockRow =BerlinClockRow( segments = emptyList()),
    val fiveHoursRow: BerlinClockRow=BerlinClockRow( segments = emptyList()),
    val oneHoursRow: BerlinClockRow=BerlinClockRow( segments = emptyList()),
    val fiveMinutesRow: BerlinClockRow= BerlinClockRow( segments = emptyList()),
    val oneMinutesRow: BerlinClockRow =BerlinClockRow( segments = emptyList()),
    val currentTime: String ="00:00:00"
)
