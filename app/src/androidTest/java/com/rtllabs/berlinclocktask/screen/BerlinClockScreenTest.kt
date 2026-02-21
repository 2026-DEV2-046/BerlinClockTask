package com.rtllabs.berlinclocktask.screen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.rtllabs.berlinclocktask.domain.BerlinClockConverter
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockRow
import com.rtllabs.berlinclocktask.domain.entity.BerlinClockSegment
import com.rtllabs.berlinclocktask.domain.entity.SegmentColor
import com.rtllabs.berlinclocktask.presentation.BerlinClockUiState
import com.rtllabs.berlinclocktask.presentation.BerlinClockViewModel
import com.rtllabs.berlinclocktask.presentation.SystemTimeProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class BerlinClockScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var converter: BerlinClockConverter

    private lateinit var viewModel: BerlinClockViewModel

    @Inject
    lateinit var fakeTimeProvider: SystemTimeProvider


    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = BerlinClockViewModel(converter, fakeTimeProvider)
    }


    private val fakeUiState = BerlinClockUiState(
        currentTime = "12:00:00",
        secondsRow = BerlinClockRow(listOf(BerlinClockSegment(true, SegmentColor.YELLOW))),
        fiveHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(false, SegmentColor.GRAY) }),
        oneHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(false, SegmentColor.GRAY) }),
        fiveMinutesRow = BerlinClockRow(List(11) {
            BerlinClockSegment(
                false,
                SegmentColor.GRAY
            )
        }),
        oneMinutesRow = BerlinClockRow(List(4) { BerlinClockSegment(false, SegmentColor.GRAY) })
    )

    private val fakeUiStateUpdate = BerlinClockUiState(
        currentTime = "12:00:01",
        secondsRow = BerlinClockRow(listOf(BerlinClockSegment(true, SegmentColor.YELLOW))),
        fiveHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(false, SegmentColor.GRAY) }),
        oneHoursRow = BerlinClockRow(List(4) { BerlinClockSegment(false, SegmentColor.GRAY) }),
        fiveMinutesRow = BerlinClockRow(List(11) {
            BerlinClockSegment(
                false,
                SegmentColor.GRAY
            )
        }),
        oneMinutesRow = BerlinClockRow(List(4) { BerlinClockSegment(false, SegmentColor.GRAY) })
    )

    @Test
    fun berlinClockScreenDisplaysTitle() {
        val stateFlow = MutableStateFlow(fakeUiState)

        composeTestRule.setContent {
            BerlinClockScreen("Berlin Clock", uiStateFlow = stateFlow)
        }

        composeTestRule.onNodeWithText("Berlin Clock").assertIsDisplayed()
    }

    @Test
    fun berlinClockScreenDisplaysCurrentTime() {
        val stateFlow = MutableStateFlow(fakeUiState)

        composeTestRule.setContent {
            BerlinClockScreen("Berlin Clock", uiStateFlow = stateFlow)
        }

        composeTestRule.onNodeWithText("12:00:00").assertIsDisplayed()
    }

    @Test
    fun berlinClockScreenUpdatesWhenUiStateChanges() {
        val stateFlow = MutableStateFlow(
            fakeUiState
        )

        composeTestRule.setContent {
            BerlinClockScreen("Berlin Clock",uiStateFlow = stateFlow)
        }

        composeTestRule.onNodeWithText("12:00:00").assertIsDisplayed()

        stateFlow.value = fakeUiStateUpdate

        composeTestRule.onNodeWithText("12:00:01").assertIsDisplayed()
    }

    @Test
    fun berlinClockScreenDisplaysYellowSecondsLampOn() {
        val stateFlow = MutableStateFlow(
            fakeUiState
        )

        composeTestRule.setContent {
            BerlinClockScreen(name = "Berlin Clock", uiStateFlow = stateFlow)
        }

        composeTestRule.onNodeWithTag("segment-YELLOW-true").assertExists()
    }

    @Test
    fun berlinClockScreenDisplaysFiveRows() {
        val stateFlow = MutableStateFlow(
            fakeUiState
        )

        composeTestRule.setContent {
            BerlinClockScreen("Berlin Clock",uiStateFlow = stateFlow)
        }

        composeTestRule.onAllNodesWithTag("lamp-row").assertCountEquals(5)
    }



}