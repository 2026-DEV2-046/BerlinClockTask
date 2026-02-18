package com.rtllabs.berlinclocktask.domain

class BerlinClockConverter {
    internal fun generateSecondsRow(seconds: Int): Boolean {
        return seconds % 2 == 0
    }
}