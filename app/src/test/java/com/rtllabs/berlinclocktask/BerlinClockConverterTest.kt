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

    @Test
    fun secondsLampShouldColorYellowWhenSecondIsEven() {
        val berlinClockConverter= BerlinClockConverter()

        val secondsLamp = berlinClockConverter.generateSecondsRow(2)

        assertEquals(SegmentColor.YELLOW, secondsLamp.color)
    }

    @Test
    fun fiveHours4LampsShouldOffWhenHour4() {
        val berlinClockConverter= BerlinClockConverter()

        val segments = berlinClockConverter.generateFiveHoursRow(4)
        val fiveHoursLamps = segments.map { it.isLampOn }


        assertEquals(listOf(false, false, false, false), fiveHoursLamps)
    }

    @Test
    fun fiveHours4LampsOutOf1ShouldTurnOnWhenHour5() {
        val berlinClockConverter= BerlinClockConverter()

        val segments = berlinClockConverter.generateFiveHoursRow(5)
        val fiveHoursLamps = segments.map { it.isLampOn }


        assertEquals(listOf(true, false, false, false), fiveHoursLamps)
    }


}