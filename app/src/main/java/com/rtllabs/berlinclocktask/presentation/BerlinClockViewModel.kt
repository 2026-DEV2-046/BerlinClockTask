package com.rtllabs.berlinclocktask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtllabs.berlinclocktask.domain.BerlinClockConverter
import com.rtllabs.berlinclocktask.domain.TimeProvider
import com.rtllabs.berlinclocktask.utils.formatToClock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import javax.inject.Inject

@HiltViewModel
class BerlinClockViewModel @Inject constructor(
    private val converter: BerlinClockConverter,
    private val timeProvider: TimeProvider
) : ViewModel() {

    val uiState: StateFlow<BerlinClockUiState> =
        flow {
            while (currentCoroutineContext().isActive) {
                emit(timeProvider.getCurrentTime())
                delay(1000)
            }
        }.map { time ->
            val result = converter.convert(
                time.hour,
                time.minute,
                time.second
            )
            BerlinClockUiState(
                secondsRow = result.secondsRow,
                fiveHoursRow = result.fiveHoursRow,
                oneHoursRow = result.oneHoursRow,
                fiveMinutesRow = result.fiveMinutesRow,
                oneMinutesRow = result.oneMinutesRow,
                currentTime = time.formatToClock()
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            BerlinClockUiState()
        )
}