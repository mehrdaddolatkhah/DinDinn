package com.dindinn.app.di

import android.content.Context
import com.dindinn.app.data.repository.OrderRepositoryImpl
import com.dindinn.app.domain.repository.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOrderRepository(
        @ApplicationContext context: Context
    ): OrderRepository {
        return OrderRepositoryImpl(context)
    }
}