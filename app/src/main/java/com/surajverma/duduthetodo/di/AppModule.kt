package com.surajverma.duduthetodo.di

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
import com.surajverma.duduthetodo.presentation.add_edit_todo.AddEditTodoViewModel
import com.surajverma.duduthetodo.presentation.todo_list.TodoListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Database
    single {
        Room.databaseBuilder(
            androidApplication(),
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    // Repository
    single<TodoRepository>{
        TodoRepositoryImpl(get<TodoDatabase>().todoDao())
    }

    // ViewModel
    viewModel {
        TodoListViewModel(get(),
            get(),
            get()
        )
    }

    viewModel {
        AddEditTodoViewModel(
            addTodoUseCase = get(),
            updateTodoUseCase = get(),
            getTodoByIdUseCase = get(),
            savedStateHandle = get()
        )
    }

    // UseCases
    single { AddTodoUseCase(get()) }
    single { DeleteTodoUseCase(get()) }
    single { GetAllTodosUseCase(get()) }
    single { ToggleTodoCompletionUseCase(get()) }
    single { UpdateTodoUseCase(get()) }
    single { GetTodoByIdUseCase(get()) }
}