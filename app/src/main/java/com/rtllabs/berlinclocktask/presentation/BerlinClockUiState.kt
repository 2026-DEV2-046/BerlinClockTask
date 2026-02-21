package com.rtllabs.berlinclocktask.presentation

import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.utils.DAY_START_TIME

data class BerlinClockUiState(
    val secondsRow: BerlinClockRow =BerlinClockRow( segments = emptyList()),
    val fiveHoursRow: BerlinClockRow=BerlinClockRow( segments = emptyList()),
    val oneHoursRow: BerlinClockRow=BerlinClockRow( segments = emptyList()),
    val fiveMinutesRow: BerlinClockRow= BerlinClockRow( segments = emptyList()),
    val oneMinutesRow: BerlinClockRow =BerlinClockRow( segments = emptyList()),
    val currentTime: String =DAY_START_TIME
)
