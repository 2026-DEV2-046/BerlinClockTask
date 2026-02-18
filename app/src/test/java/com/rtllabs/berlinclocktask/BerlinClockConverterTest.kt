package com.rtllabs.berlinclocktask

import com.rtllabs.berlinclocktask.domain.BerlinClockConverter
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import org.junit.Assert.*

import org.junit.Test

class BerlinClockConverterTest {

    @Test
    fun secondsLampShouldOffWhenSecondIsOdd() {
        val berlinClockConverter= BerlinClockConverter()
        val secondsLamp = berlinClockConverter.generateSecondsRow(1)

        assertFalse(secondsLamp.isLampOn)
    }

    @Test
    fun secondsLampShouldOnWhenSecondIsEven() {
        val berlinClockConverter= BerlinClockConverter()

        val secondsLamp = berlinClockConverter.generateSecondsRow(2)

        assertTrue(secondsLamp.isLampOn)
    }

    @Test
    fun secondsLampShouldColorGrayWhenSecondIsOdd() {
        val berlinClockConverter= BerlinClockConverter()

        val secondsLamp = berlinClockConverter.generateSecondsRow(1)

        assertEquals(SegmentColor.GRAY, secondsLamp.color)
    }

}