package com.rtllabs.berlinclocktask.presentation

import com.rtllabs.berlinclocktask.domain.TimeProvider
import java.time.LocalTime

class SystemTimeProvider : TimeProvider{
    override fun getCurrentTime(): LocalTime {
        return LocalTime.now()
    }
}