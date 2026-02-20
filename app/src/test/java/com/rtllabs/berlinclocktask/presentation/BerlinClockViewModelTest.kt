package com.rtllabs.berlinclocktask.presentation

import app.cash.turbine.test
import com.rtllabs.berlinclocktask.MainDispatcherRule
import com.rtllabs.berlinclocktask.domain.BerlinClockConverter
import com.rtllabs.berlinclocktask.domain.entity.BerlinClock
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalCoroutinesApi::class)
class BerlinClockViewModelTest {

    private var berlinClockConverter: BerlinClockConverter = mockk<BerlinClockConverter>()
    private var fakeTimeProvider: FakeTimeProvider = FakeTimeProvider(
        LocalTime.of(0, 0, 0))

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Test
    fun viewModelStateUpdateShouldReturnCorrectBerlinDataAndCurrentTime() = runTest {
        fakeTimeProvider.setTime(LocalTime.of(12, 0, 0))

        val fakeClockData = BerlinClock(
            secondsRow = BerlinClockRow(segments = listOf(BerlinClockSegment(
                isLampOn = true, color = SegmentColor.YELLOW))),
            fiveHoursRow = BerlinClockRow(segments = List(4) {
                BerlinClockSegment(isLampOn = false, color = SegmentColor.GRAY)
            }),
            oneHoursRow = BerlinClockRow(segments = List(4) {
                BerlinClockSegment(isLampOn = false, color = SegmentColor.GRAY)
            }),
            fiveMinutesRow = BerlinClockRow(segments = List(11) {
                BerlinClockSegment(isLampOn = false, color = SegmentColor.GRAY)
            }),
            oneMinutesRow = BerlinClockRow(segments = List(4) {
                BerlinClockSegment(isLampOn = false, color = SegmentColor.GRAY)
            }),
        )

        val expectedTime =
            fakeTimeProvider.getCurrentTime().format(
                DateTimeFormatter.ofPattern("HH:mm:ss"))

        every { berlinClockConverter.convert(hour = fakeTimeProvider.getCurrentTime().hour,
            minute = fakeTimeProvider.getCurrentTime().minute,
            second = fakeTimeProvider.getCurrentTime().second) } returns fakeClockData

        val viewModel = BerlinClockViewModel(berlinClockConverter, fakeTimeProvider)


        viewModel.uiState.test {
            skipItems(1)
            val emission = awaitItem()
            val emissionBerlinClock= BerlinClock(
                secondsRow = emission.secondsRow,
                fiveHoursRow = emission.fiveHoursRow,
                oneHoursRow = emission.oneHoursRow,
                fiveMinutesRow = emission.fiveMinutesRow,
                oneMinutesRow = emission.oneMinutesRow
            )

            assertEquals(fakeClockData, emissionBerlinClock)
            assertEquals(expectedTime, emission.currentTime)
            cancelAndIgnoreRemainingEvents()
        }
    }

}