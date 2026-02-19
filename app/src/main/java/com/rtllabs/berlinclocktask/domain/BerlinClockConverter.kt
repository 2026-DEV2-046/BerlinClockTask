package com.rtllabs.berlinclocktask.domain

import com.rtllabs.berlinclocktask.domain.entity.BerlinClock
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor

class BerlinClockConverter {

    fun convert(hour: Int, second: Int): BerlinClock {
        return BerlinClock(
            secondsRow = generateSecondsRow(second),
            fiveHoursRow = generateFiveHoursRow(hour),
        )
    }

    private fun generateSecondsRow(seconds: Int): BerlinClockRow {
        return BerlinClockRow(
            segments = listOf(
                BerlinClockSegment(
                    isLampOn = seconds % 2 == 0,
                    color = if (seconds % 2 == 0) SegmentColor.YELLOW else SegmentColor.GRAY
                )
            )
        )
    }

    private fun generateFiveHoursRow(hour: Int): BerlinClockRow {
        val result = hour / 5

        return BerlinClockRow(
            segments = List(4) { index ->
                BerlinClockSegment(
                    isLampOn = index < result,
                    color = if (index < result) SegmentColor.RED else SegmentColor.GRAY
                )
            }
        )
    }

    internal fun generateOneHoursRow(hour: Int): List<BerlinClockSegment> {
        val remainder= hour % 5
        return List(4){ index ->
            BerlinClockSegment(
                isLampOn = index < remainder,
                color = if(index < remainder) SegmentColor.RED else SegmentColor.GRAY
            )
        }
    }

    internal fun generateFiveMinutesRow(minute: Int) : List<BerlinClockSegment>{
        val result = minute/5

       return List(11){ index ->
           val isOn= index < result
           val color= if(isOn) if ((index+1) % 3 ==0) SegmentColor.RED else SegmentColor.YELLOW  else SegmentColor.GRAY
           BerlinClockSegment(
               isLampOn = isOn,
               color = color
           )
       }
    }

    internal fun generateOneMinutesRow(minute: Int): List<BerlinClockSegment> {
        val remainder= minute % 5
        return List(4){ index ->
            BerlinClockSegment(
                isLampOn = index <remainder,
                color = if(index < remainder) SegmentColor.YELLOW else SegmentColor.GRAY
            )
        }
    }
}