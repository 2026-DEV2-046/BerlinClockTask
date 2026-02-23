package com.rtllabs.berlinclocktask.domain

import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import org.junit.Assert
import org.junit.Test

class BerlinClockConverterTest {

    @Test
    fun secondsLampShouldOffWhenSecondIsOdd() {
        val berlinClockConverter = BerlinClockConverter()
        val berlinClock = berlinClockConverter.convert(0, 0, 1)
        val secondsLamp = berlinClock.secondsRow.segments.first()

        Assert.assertFalse(secondsLamp.isLampOn)
    }

    @Test
    fun secondsLampShouldOnWhenSecondIsEven() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 0, 2)
        val secondsLamp = berlinClock.secondsRow.segments.first()

        Assert.assertTrue(secondsLamp.isLampOn)
    }

    @Test
    fun secondsLampShouldColorGrayWhenSecondIsOdd() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 0, 1)
        val secondsLamp = berlinClock.secondsRow.segments.first()

        Assert.assertEquals(SegmentColor.GRAY, secondsLamp.color)
    }

    @Test
    fun secondsLampShouldColorYellowWhenSecondIsEven() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 0, 2)
        val secondsLamp = berlinClock.secondsRow.segments.first()

        Assert.assertEquals(SegmentColor.YELLOW, secondsLamp.color)
    }

    @Test
    fun fiveHoursRow4LampsShouldOffWhenHour4() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(4, 0, 0)
        val fiveHoursLamps = berlinClock.fiveHoursRow.segments.map { it.isLampOn }


        Assert.assertEquals(listOf(false, false, false, false), fiveHoursLamps)
    }

    @Test
    fun fiveHoursRow4LampsOutOf1ShouldTurnOnWhenHour5() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(5, 0, 0)
        val fiveHoursLamps = berlinClock.fiveHoursRow.segments.map { it.isLampOn }


        Assert.assertEquals(listOf(true, false, false, false), fiveHoursLamps)
    }

    @Test
    fun fiveHoursRow4LampsOutOf1ShouldColorRedWhenHour5() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(5, 0, 0)
        val fiveHoursLampsColor = berlinClock.fiveHoursRow.segments.map { it.color }


        Assert.assertEquals(
            listOf(
                SegmentColor.RED,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY
            ), fiveHoursLampsColor
        )
    }

    @Test
    fun oneHoursRow4LampsShouldOffWhenHour5() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(5, 0, 0)
        val oneHoursLamps = berlinClock.oneHoursRow.segments.map { it.isLampOn }


        Assert.assertEquals(listOf(false, false, false, false), oneHoursLamps)
    }

    @Test
    fun oneHoursRow4LampsOutOf1ShouldTurnOnAndColorRedWhenHour6() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(6, 0, 0)
        val oneHoursLamps = berlinClock.oneHoursRow.segments.map { it.isLampOn }
        val oneHoursLampsColor = berlinClock.oneHoursRow.segments.map { it.color }

        Assert.assertEquals(listOf(true, false, false, false), oneHoursLamps)
        Assert.assertEquals(
            listOf(
                SegmentColor.RED,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY
            ), oneHoursLampsColor
        )

    }

    @Test
    fun oneHoursRow4LampsOutOf4ShouldTurnOnAndColorRedWhenHour9() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(9, 0, 0)
        val oneHoursLamps = berlinClock.oneHoursRow.segments.map { it.isLampOn }
        val oneHoursLampsColor = berlinClock.oneHoursRow.segments.map { it.color }


        Assert.assertEquals(listOf(true, true, true, true), oneHoursLamps)
        Assert.assertEquals(
            listOf(
                SegmentColor.RED,
                SegmentColor.RED,
                SegmentColor.RED,
                SegmentColor.RED
            ), oneHoursLampsColor
        )

    }

    @Test
    fun fiveMinutesRow11LampsShouldOffWhenMinutes4() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 4, 0)
        val fiveMinuteLampsCount = berlinClock.fiveMinutesRow.segments.count { it.isLampOn }


        Assert.assertEquals(0, fiveMinuteLampsCount)
    }

    @Test
    fun fiveMinutesRow11LampsOutOff1ShouldOnWhenMinutes5() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 5, 0)
        val fiveMinuteLampsCount = berlinClock.fiveMinutesRow.segments.count { it.isLampOn }


        Assert.assertEquals(1, fiveMinuteLampsCount)
    }

    @Test
    fun fiveMinutesRow11LampsOutOf2ShouldTurnOnAndColorYellowWhenMinutes10() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 10, 0)
        val fiveMinuteLampsOnCount = berlinClock.fiveMinutesRow.segments.count { it.isLampOn }
        val fiveMinuteLampsColor = berlinClock.fiveMinutesRow.segments.map { it.color }


        Assert.assertEquals(2, fiveMinuteLampsOnCount)
        Assert.assertEquals(
            listOf(
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY
            ), fiveMinuteLampsColor
        )
    }

    @Test
    fun fiveMinutesRow11LampsOutOf3ShouldTurnOnAnd2ColorYellow1ColorRedWhenMinutes15() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 15, 0)
        val fiveMinuteLampsOnCount = berlinClock.fiveMinutesRow.segments.count { it.isLampOn }
        val fiveMinuteLampsColor = berlinClock.fiveMinutesRow.segments.map { it.color }


        Assert.assertEquals(3, fiveMinuteLampsOnCount)
        Assert.assertEquals(
            listOf(
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.RED,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY
            ), fiveMinuteLampsColor
        )
    }

    @Test
    fun fiveMinutesRow11LampsShouldTurnOnAndEvery3ColorRedOtherColorYellowWhenMinutes59() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 59, 0)
        val fiveMinuteLampsOnCount = berlinClock.fiveMinutesRow.segments.count { it.isLampOn }
        val fiveMinuteLampsColor = berlinClock.fiveMinutesRow.segments.map { it.color }


        Assert.assertEquals(11, fiveMinuteLampsOnCount)
        Assert.assertEquals(
            listOf(
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.RED,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.RED,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.RED,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW
            ), fiveMinuteLampsColor
        )
    }

    @Test
    fun oneMinutesRow4LampsShouldTurnOffWhenMinutes55() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 55, 0)
        val oneMinuteLampsOnCount = berlinClock.oneMinutesRow.segments.count { it.isLampOn }

        Assert.assertEquals(0, oneMinuteLampsOnCount)
    }

    @Test
    fun oneMinutesRow4LampsOutOf1ShouldTurnOnWhenMinutes56() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 56, 0)
        val oneMinuteLampsOnCount = berlinClock.oneMinutesRow.segments.count { it.isLampOn }

        Assert.assertEquals(1, oneMinuteLampsOnCount)
    }

    @Test
    fun oneMinutesRow4LampsShouldTurnOnAndColorYellowWhenMinutes59() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(0, 59, 0)
        val oneMinuteLampsOnCount = berlinClock.oneMinutesRow.segments.count { it.isLampOn }
        val oneMinuteLampsColor = berlinClock.oneMinutesRow.segments.map { it.color }

        Assert.assertEquals(4, oneMinuteLampsOnCount)
        Assert.assertEquals(
            listOf(
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW
            ), oneMinuteLampsColor
        )

    }

    // 00_00_00 time stamp should return all lamp off except seconds lamp
    @Test
    fun converterShouldReturnAllLampOffExceptSecondsLampWhenHoursZeroMinutesZeroSecondZero() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(hour = 0, minute = 0, second = 0)
        val secondsRow = berlinClock.secondsRow
        val fiveHoursRow = berlinClock.fiveHoursRow
        val oneHoursRow = berlinClock.oneHoursRow
        val fiveMinutesRow = berlinClock.fiveMinutesRow
        val oneMinutesRow = berlinClock.oneMinutesRow

        Assert.assertEquals(1, secondsRow.segments.count { it.isLampOn })
        Assert.assertEquals(0, fiveHoursRow.segments.count { it.isLampOn })
        Assert.assertEquals(0, oneHoursRow.segments.count { it.isLampOn })
        Assert.assertEquals(0, fiveMinutesRow.segments.count { it.isLampOn })
        Assert.assertEquals(0, oneMinutesRow.segments.count { it.isLampOn })
    }

    @Test
    fun converterShouldLightCorrectLampsWhenInputMidday_12_34_54() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(hour = 12, minute = 34, second = 54)
        val secondsRow = berlinClock.secondsRow
        val fiveHoursRow = berlinClock.fiveHoursRow
        val oneHoursRow = berlinClock.oneHoursRow
        val fiveMinutesRow = berlinClock.fiveMinutesRow
        val oneMinutesRow = berlinClock.oneMinutesRow

        Assert.assertEquals(1, secondsRow.segments.count { it.isLampOn })
        Assert.assertEquals(2, fiveHoursRow.segments.count { it.isLampOn })
        Assert.assertEquals(2, oneHoursRow.segments.count { it.isLampOn })
        Assert.assertEquals(6, fiveMinutesRow.segments.count { it.isLampOn })
        Assert.assertEquals(4, oneMinutesRow.segments.count { it.isLampOn })
        Assert.assertEquals(
            listOf(SegmentColor.YELLOW),
            secondsRow.segments.map { it.color })
        Assert.assertEquals(
            listOf(
                SegmentColor.RED,
                SegmentColor.RED,
                SegmentColor.GRAY,
                SegmentColor.GRAY
            ),
            fiveHoursRow.segments.map { it.color })
        Assert.assertEquals(
            listOf(
                SegmentColor.RED,
                SegmentColor.RED,
                SegmentColor.GRAY,
                SegmentColor.GRAY
            ),
            oneHoursRow.segments.map { it.color })

        Assert.assertEquals(
            listOf(
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.RED,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.RED,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY,
                SegmentColor.GRAY
            ), fiveMinutesRow.segments.map { it.color })

        Assert.assertEquals(
            listOf(
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW
            ),
            oneMinutesRow.segments.map { it.color })
    }

    @Test
    fun converterShouldLightCorrectLampsWhenInputEndOfDay_23_59_59() {
        val berlinClockConverter = BerlinClockConverter()

        val berlinClock = berlinClockConverter.convert(hour = 23, minute = 59, second = 59)
        val secondsRow = berlinClock.secondsRow
        val fiveHoursRow = berlinClock.fiveHoursRow
        val oneHoursRow = berlinClock.oneHoursRow
        val fiveMinutesRow = berlinClock.fiveMinutesRow
        val oneMinutesRow = berlinClock.oneMinutesRow

        Assert.assertEquals(0, secondsRow.segments.count { it.isLampOn })
        Assert.assertEquals(4, fiveHoursRow.segments.count { it.isLampOn })
        Assert.assertEquals(3, oneHoursRow.segments.count { it.isLampOn })
        Assert.assertEquals(11, fiveMinutesRow.segments.count { it.isLampOn })
        Assert.assertEquals(4, oneMinutesRow.segments.count { it.isLampOn })
        Assert.assertEquals(
            listOf(SegmentColor.GRAY),
            secondsRow.segments.map { it.color })
        Assert.assertEquals(
            listOf(
                SegmentColor.RED,
                SegmentColor.RED,
                SegmentColor.RED,
                SegmentColor.RED
            ),
            fiveHoursRow.segments.map { it.color })
        Assert.assertEquals(
            listOf(
                SegmentColor.RED,
                SegmentColor.RED,
                SegmentColor.RED,
                SegmentColor.GRAY
            ),
            oneHoursRow.segments.map { it.color })

        Assert.assertEquals(
            listOf(
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.RED,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.RED,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.RED,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW
            ),
            fiveMinutesRow.segments.map { it.color })

        Assert.assertEquals(
            listOf(
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW,
                SegmentColor.YELLOW
            ),
            oneMinutesRow.segments.map { it.color })
    }


}