package com.dstv.tododstv.di

import com.dstv.data.db.TaskDao
import com.dstv.data.repository.TaskRepository
import com.dstv.data.source.TaskDataSource
import com.dstv.domain.usecase.TaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesTaskUseCase(taskRepository: TaskRepository): TaskUseCase = TaskUseCase(taskRepository)

    @Singleton
    @Provides
    fun providesTaskDataSource(taskDao: TaskDao): TaskDataSource = TaskDataSource(taskDao)

}