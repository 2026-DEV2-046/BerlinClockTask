package com.rtllabs.berlinclocktask.presentation

import com.rtllabs.berlinclocktask.domain.TimeProvider
import java.time.LocalTime

class FakeTimeProvider (private var currentTime: LocalTime): TimeProvider {
    override fun getCurrentTime(): LocalTime =currentTime
    fun setTime(time: LocalTime) {
        currentTime = time
    }
}