package com.rtllabs.berlinclocktask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtllabs.berlinclocktask.domain.BerlinClockConverter
import com.rtllabs.berlinclocktask.domain.TimeProvider
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class BerlinClockViewModel(
    private val converter: BerlinClockConverter = BerlinClockConverter(),
    private val timeProvider: TimeProvider = SystemTimeProvider()
) : ViewModel() {

    private val _uiState = MutableStateFlow(BerlinClockUiState())
    val uiState: StateFlow<BerlinClockUiState> = _uiState

    init {
        startClockTimer()
    }

    private fun startClockTimer() {
        viewModelScope.launch {
                val time = timeProvider.getCurrentTime()
                val result = converter.convert(
                    time.hour,
                    time.minute,
                    time.second
                )
                _uiState.value =BerlinClockUiState(
                    secondsRow = result.secondsRow,
                    fiveHoursRow = result.fiveHoursRow,
                    oneHoursRow = result.oneHoursRow,
                    fiveMinutesRow = result.fiveMinutesRow,
                    oneMinutesRow = result.oneMinutesRow,
                    currentTime = time
                        .format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                )
        }
    }

    fun dispose() {
        viewModelScope.cancel()
    }

}