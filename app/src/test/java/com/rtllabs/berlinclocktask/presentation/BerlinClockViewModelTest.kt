package com.rtllabs.berlinclocktask.presentation

import com.rtllabs.berlinclocktask.MainDispatcherRule
import com.rtllabs.berlinclocktask.domain.BerlinClockConverter
import com.rtllabs.berlinclocktask.domain.entity.BerlinClock
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.time.LocalTime

@OptIn(ExperimentalCoroutinesApi::class)
class BerlinClockViewModelTest {

    private var berlinClockConverter: BerlinClockConverter = mockk<BerlinClockConverter>()
    private var fakeTimeProvider: FakeTimeProvider = FakeTimeProvider(
        LocalTime.of(0, 0, 0))
    private val fakeClockData = BerlinClock(
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


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Test
    fun viewModelStateUpdateShouldReturnCorrectBerlinDataAndCurrentTime() = runTest {
        fakeTimeProvider.setTime(LocalTime.of(12, 0, 0))

        every { berlinClockConverter.convert(hour = fakeTimeProvider.getCurrentTime().hour,
            minute = fakeTimeProvider.getCurrentTime().minute,
            second = fakeTimeProvider.getCurrentTime().second) } returns fakeClockData

        val viewModel = BerlinClockViewModel(berlinClockConverter, fakeTimeProvider)

        runCurrent()
        val initialState = viewModel.uiState.value
        assertEquals("12:00:00", initialState.currentTime)

        val initialStateBerlinClock= BerlinClock(
            secondsRow = initialState.secondsRow,
            fiveHoursRow = initialState.fiveHoursRow,
            oneHoursRow = initialState.oneHoursRow,
            fiveMinutesRow = initialState.fiveMinutesRow,
            oneMinutesRow = initialState.oneMinutesRow
        )
        assertEquals(fakeClockData, initialStateBerlinClock)

        viewModel.dispose()
    }

    @Test
    fun viewModelEmitsCorrectTimeOnTimeAdvance() = runTest {
        val fakeTimeProvider = FakeTimeProvider(
            LocalTime.of(23, 59, 59))
        val converter = mockk<BerlinClockConverter>()

        every { converter.convert(23, 59, 59) } returns fakeClockData

        val viewModel = BerlinClockViewModel(converter, fakeTimeProvider)

        runCurrent()
        val initialState = viewModel.uiState.value
        val initialStateBerlinClock= BerlinClock(
            secondsRow = initialState.secondsRow,
            fiveHoursRow = initialState.fiveHoursRow,
            oneHoursRow = initialState.oneHoursRow,
            fiveMinutesRow = initialState.fiveMinutesRow,
            oneMinutesRow = initialState.oneMinutesRow
        )
        assertEquals("23:59:59", initialState.currentTime)
        assertEquals(fakeClockData, initialStateBerlinClock)

        fakeTimeProvider.setTime(LocalTime.of(0, 0, 0))
        every { converter.convert(0, 0, 0) } returns fakeClockData
        advanceTimeBy(1000)
        runCurrent()


        val updatedState = viewModel.uiState.value
        val updateStateBerlinClock= BerlinClock(
            secondsRow = initialState.secondsRow,
            fiveHoursRow = initialState.fiveHoursRow,
            oneHoursRow = initialState.oneHoursRow,
            fiveMinutesRow = initialState.fiveMinutesRow,
            oneMinutesRow = initialState.oneMinutesRow
        )
        assertEquals("00:00:00", updatedState.currentTime)
        assertEquals(fakeClockData, updateStateBerlinClock)

        viewModel.dispose()
    }

    @Test
    fun disposeStopsClockUpdates() = runTest {
        val fakeTimeProvider = FakeTimeProvider(LocalTime.of(10, 0, 0))
        val converter = mockk<BerlinClockConverter>()

        every { converter.convert(any(), any(), any()) } returns fakeClockData

        val viewModel = BerlinClockViewModel(converter, fakeTimeProvider)
        runCurrent()
        val initialState = viewModel.uiState.value

        viewModel.dispose()
        fakeTimeProvider.setTime(LocalTime.of(11, 0, 0))
        advanceTimeBy(2000)
        runCurrent()

        val afterDisposeState = viewModel.uiState.value
        assertEquals(initialState.currentTime, afterDisposeState.currentTime)
    }


}