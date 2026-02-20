package com.rtllabs.berlinclocktask.di

import com.rtllabs.berlinclocktask.domain.TimeProvider
import com.rtllabs.berlinclocktask.presentation.SystemTimeProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BerlinClockModule {
    @Binds
    abstract fun bindTimeProvider( impl: SystemTimeProvider): TimeProvider
}