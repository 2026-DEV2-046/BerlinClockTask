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

    @Test
    fun fiveHours4LampsOutOf1ShouldColorRedWhenHour5() {
        val berlinClockConverter= BerlinClockConverter()

        val segments = berlinClockConverter.generateFiveHoursRow(5)
        val fiveHoursLampsColor = segments.map { it.color }


        assertEquals(listOf(SegmentColor.RED, SegmentColor.GRAY, SegmentColor.GRAY, SegmentColor.GRAY), fiveHoursLampsColor)
    }

    @Test
    fun oneHours4LampsShouldOffWhenHour5() {
        val berlinClockConverter= BerlinClockConverter()

        val segments = berlinClockConverter.generateOneHoursRow(5)
        val oneHoursLamps = segments.map { it.isLampOn }


        assertEquals(listOf(false, false, false, false), oneHoursLamps)
    }

    @Test
    fun oneHours4LampsOutOf1ShouldTurnOnWhenHour6() {
        val berlinClockConverter= BerlinClockConverter()

        val segments = berlinClockConverter.generateOneHoursRow(6)
        val oneHoursLamps = segments.map { it.isLampOn }
        val oneHoursLampsColor = segments.map { it.color }

        assertEquals(listOf(true, false, false, false), oneHoursLamps)
        assertEquals(listOf(SegmentColor.RED, SegmentColor.GRAY, SegmentColor.GRAY, SegmentColor.GRAY), oneHoursLampsColor)

    }

    @Test
    fun oneHours4LampsOutOf4ShouldTurnOnAndColorRedWhenHour9() {
        val berlinClockConverter= BerlinClockConverter()

        val segments = berlinClockConverter.generateOneHoursRow(9)
        val oneHoursLamps = segments.map { it.isLampOn }
        val oneHoursLampsColor = segments.map { it.color }


        assertEquals(listOf(true, true, true, true), oneHoursLamps)
        assertEquals(listOf(SegmentColor.RED, SegmentColor.RED, SegmentColor.RED, SegmentColor.RED), oneHoursLampsColor)

    }

    @Test
    fun fiveMinutes11LampsShouldOffWhenMinutes4() {
        val berlinClockConverter= BerlinClockConverter()

        val segments = berlinClockConverter.generateFiveMinutesRow(4)
        val fiveMinuteLampsCount = segments.count { it.isLampOn }


        assertEquals(0, fiveMinuteLampsCount)
    }



}