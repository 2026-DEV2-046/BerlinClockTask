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
import com.rtllabs.berlinclocktask.data.SystemTimeProvider
import com.rtllabs.berlinclocktask.presentation.screen.BerlinClockScreen
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

    private lateinit var stateFlow: MutableStateFlow<BerlinClockUiState>

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

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = BerlinClockViewModel(converter, fakeTimeProvider)
        stateFlow = MutableStateFlow(
            fakeUiState
        )
        composeTestRule.setContent {
            BerlinClockScreen("Berlin Clock", uiStateFlow = stateFlow)
        }
    }

    @Test
    fun berlinClockScreenDisplaysTitle() {

        composeTestRule.onNodeWithText("Berlin Clock").assertIsDisplayed()
    }

    @Test
    fun berlinClockScreenDisplaysCurrentTime() {

        composeTestRule.onNodeWithText("12:00:00").assertIsDisplayed()
    }

    @Test
    fun berlinClockScreenUpdatesWhenUiStateChanges() {

        composeTestRule.onNodeWithText("12:00:00").assertIsDisplayed()

        stateFlow.value = fakeUiStateUpdate

        composeTestRule.onNodeWithText("12:00:01").assertIsDisplayed()
    }

    @Test
    fun berlinClockScreenDisplaysYellowSecondsLampOn() {

        composeTestRule.onNodeWithTag("segment-YELLOW-true-ONE_SEGMENT_LAMP-CIRCLE").assertExists()
    }

    @Test
    fun berlinClockScreenDisplaysGrayFourSegmentRowLampOffCount12() {

        composeTestRule.onAllNodesWithTag("segment-GRAY-false-FOUR_SEGMENT_LAMP-RECTANGLE").assertCountEquals(12)
    }

    @Test
    fun berlinClockScreenDisplaysGrayElevenSegmentRowLampOffCount11() {

        composeTestRule.onAllNodesWithTag("segment-GRAY-false-ELEVEN_SEGMENT_LAMP-RECTANGLE").assertCountEquals(11)
    }

    @Test
    fun berlinClockScreenDisplaysFiveRows() {

        composeTestRule.onAllNodesWithTag("lamp-row").assertCountEquals(5)
    }


}