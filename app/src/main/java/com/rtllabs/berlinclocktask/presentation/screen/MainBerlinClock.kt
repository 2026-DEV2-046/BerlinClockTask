package com.rtllabs.berlinclocktask.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rtllabs.berlinclocktask.presentation.BerlinClockViewModel

@Composable
fun MainBerlinClock(
    name: String,
    modifier: Modifier = Modifier,
    viewModel: BerlinClockViewModel
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    /*BerlinClockScreen(
        name = name,
        modifier = modifier,
        uiState = uiState
    )*/

}