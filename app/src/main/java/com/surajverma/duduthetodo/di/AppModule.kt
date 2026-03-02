package com.surajverma.duduthetodo.di

import android.app.Application
import androidx.room.Room
import com.surajverma.duduthetodo.data.local.database.TodoDatabase
import com.surajverma.duduthetodo.data.repository.TodoRepositoryImpl
import com.surajverma.duduthetodo.domain.repository.TodoRepository
import com.surajverma.duduthetodo.domain.usecase.AddTodoUseCase
import com.surajverma.duduthetodo.domain.usecase.DeleteTodoUseCase
import com.surajverma.duduthetodo.domain.usecase.GetAllTodosUseCase
import com.surajverma.duduthetodo.domain.usecase.GetTodoByIdUseCase
import com.surajverma.duduthetodo.domain.usecase.ToggleTodoCompletionUseCase
import com.surajverma.duduthetodo.domain.usecase.UpdateTodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.todoDao())
    }

    @Provides
    @Singleton
    fun provideAddTodoUseCase(repository: TodoRepository): AddTodoUseCase {
        return AddTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTodoUseCase(repository: TodoRepository): DeleteTodoUseCase {
        return DeleteTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllTodosUseCase(repository: TodoRepository): GetAllTodosUseCase {
        return GetAllTodosUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleTodoCompletionUseCase(repository: TodoRepository): ToggleTodoCompletionUseCase {
        return ToggleTodoCompletionUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateTodoUseCase(repository: TodoRepository): UpdateTodoUseCase {
        return UpdateTodoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTodoByIdUseCase(
        repository: TodoRepository
    ): GetTodoByIdUseCase {
        return GetTodoByIdUseCase(repository)
    }

}