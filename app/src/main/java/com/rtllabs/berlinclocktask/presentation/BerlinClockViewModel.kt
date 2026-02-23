package com.rtllabs.berlinclocktask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtllabs.berlinclocktask.domain.BerlinClockConverter
import com.rtllabs.berlinclocktask.domain.TimeProvider
import com.rtllabs.berlinclocktask.utils.DATE_FORMAT
import com.rtllabs.berlinclocktask.utils.formatToClock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerlinClockViewModel @Inject constructor(
    private val converter: BerlinClockConverter,
    private val timeProvider: TimeProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(BerlinClockUiState())
    val uiState: StateFlow<BerlinClockUiState> = _uiState

    init {
        startClockTimer()
    }

    private fun startClockTimer() {
        viewModelScope.launch {
            while (isActive) {
                val time = timeProvider.getCurrentTime()
                val result = converter.convert(
                    time.hour,
                    time.minute,
                    time.second
                )
                _uiState.value = BerlinClockUiState(
                    secondsRow = result.secondsRow,
                    fiveHoursRow = result.fiveHoursRow,
                    oneHoursRow = result.oneHoursRow,
                    fiveMinutesRow = result.fiveMinutesRow,
                    oneMinutesRow = result.oneMinutesRow,
                    currentTime = time.formatToClock()
                )

                delay(1000)
            }
        }
    }

    fun dispose() {
        viewModelScope.cancel()
    }
}