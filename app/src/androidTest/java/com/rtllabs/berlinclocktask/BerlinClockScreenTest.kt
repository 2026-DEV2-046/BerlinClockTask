package com.rtllabs.berlinclocktask

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.rtllabs.berlinclocktask.screen.BerlinClockScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class BerlinClockScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun berlinClockScreenDisplaysTitle() {
        composeTestRule.setContent {
            BerlinClockScreen("Berlin Clock")
        }

        composeTestRule.onNodeWithText("Berlin Clock").assertIsDisplayed()
    }


}
