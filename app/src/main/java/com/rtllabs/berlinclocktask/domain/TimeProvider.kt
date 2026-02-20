package com.rtllabs.berlinclocktask.domain

import java.time.LocalTime

interface TimeProvider {
    fun getCurrentTime(): LocalTime
}