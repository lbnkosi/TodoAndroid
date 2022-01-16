package com.lbnkosi.todoapp.di

import com.lbnkosi.data.db.TaskDao
import com.lbnkosi.data.repository.TaskRepository
import com.lbnkosi.data.source.TaskDataSource
import com.lbnkosi.domain.usecase.TaskUseCase
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