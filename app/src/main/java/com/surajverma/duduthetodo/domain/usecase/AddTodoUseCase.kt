package com.surajverma.duduthetodo.domain.usecase

import com.surajverma.duduthetodo.domain.model.Todo
import com.surajverma.duduthetodo.domain.repository.TodoRepository

class AddTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) {
        if (todo.title.isBlank()) {
            throw IllegalArgumentException("Please Enter the name")
        }
        repository.insertTodo(todo)
    }
}