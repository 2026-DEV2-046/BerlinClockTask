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

@OptIn(ExperimentalCoroutinesApi::class)
class BerlinClockViewModelTest {

    private var berlinClockConverter: BerlinClockConverter = mockk<BerlinClockConverter>()
    private var fakeTimeProvider: FakeTimeProvider = FakeTimeProvider(
        LocalTime.of(0, 0, 0)
    )
    private val fakeClockData = BerlinClock(
        secondsRow = BerlinClockRow(
            segments = listOf(
                BerlinClockSegment(
                    isLampOn = true, color = SegmentColor.YELLOW
                )
            )
        ),
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

        every {
            berlinClockConverter.convert(
                hour = fakeTimeProvider.getCurrentTime().hour,
                minute = fakeTimeProvider.getCurrentTime().minute,
                second = fakeTimeProvider.getCurrentTime().second
            )
        } returns fakeClockData

        val viewModel = BerlinClockViewModel(berlinClockConverter, fakeTimeProvider)
        viewModel.uiState.test {
            skipItems(1)
            val state = awaitItem()
            val initialStateBerlinClock= BerlinClock(
                secondsRow = state.secondsRow,
                fiveHoursRow = state.fiveHoursRow,
                oneHoursRow = state.oneHoursRow,
                fiveMinutesRow = state.fiveMinutesRow,
                oneMinutesRow = state.oneMinutesRow
            )

            assertEquals("12:00:00", state.currentTime)
            assertEquals(fakeClockData, initialStateBerlinClock)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun viewModelEmitsCorrectTimeOnTimeAdvance() = runTest {
        val fakeTimeProvider = FakeTimeProvider(
            LocalTime.of(23, 59, 59)
        )

        every { berlinClockConverter.convert(23, 59, 59) } returns fakeClockData
        val viewModel = BerlinClockViewModel(berlinClockConverter, fakeTimeProvider)

        viewModel.uiState.test {
            skipItems(1)
            val first = awaitItem()
            val berlinClock = BerlinClock(
                secondsRow = first.secondsRow,
                fiveHoursRow = first.fiveHoursRow,
                oneHoursRow = first.oneHoursRow,
                fiveMinutesRow = first.fiveMinutesRow,
                oneMinutesRow = first.oneMinutesRow
            )

            assertEquals("23:59:59", first.currentTime)
            assertEquals(fakeClockData, berlinClock)

            fakeTimeProvider.setTime(LocalTime.of(0, 0, 0))
            every { berlinClockConverter.convert(0, 0, 0) } returns fakeClockData

            val second = awaitItem()
            val berlinClockUpdate = BerlinClock(
                secondsRow = second.secondsRow,
                fiveHoursRow = second.fiveHoursRow,
                oneHoursRow = second.oneHoursRow,
                fiveMinutesRow = second.fiveMinutesRow,
                oneMinutesRow = second.oneMinutesRow
            )

            assertEquals("00:00:00", second.currentTime)
            assertEquals(fakeClockData, berlinClockUpdate)

            cancelAndIgnoreRemainingEvents()
        }
    }


}