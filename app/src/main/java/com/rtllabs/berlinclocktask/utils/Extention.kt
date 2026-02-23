package com.rtllabs.berlinclocktask.utils

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalTime.formatToClock(): String {
    return this.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
}

fun SegmentShape.toShape(): Shape = when (this) {
    SegmentShape.CIRCLE -> CircleShape
    SegmentShape.RECTANGLE -> RoundedCornerShape(2.dp)
}