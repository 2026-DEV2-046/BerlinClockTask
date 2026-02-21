package com.rtllabs.berlinclocktask.utils

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalTime.formatToClock(): String {
    return this.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
}