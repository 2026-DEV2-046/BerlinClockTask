package com.rtllabs.berlinclocktask.presentation

import com.rtllabs.berlinclocktask.domain.TimeProvider
import java.time.LocalTime
import javax.inject.Inject

class SystemTimeProvider @Inject constructor() : TimeProvider {
    override fun getCurrentTime(): LocalTime {
        return LocalTime.now()
    }
}