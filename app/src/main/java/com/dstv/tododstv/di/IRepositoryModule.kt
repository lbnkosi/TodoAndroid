package com.dstv.tododstv.di

import com.dstv.domain.repository.ITaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTaskRepository(taskRepository: ITaskRepository): ITaskRepository

}