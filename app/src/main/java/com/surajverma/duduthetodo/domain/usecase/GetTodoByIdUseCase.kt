package com.surajverma.duduthetodo.domain.usecase

import com.surajverma.duduthetodo.domain.model.Todo
import com.surajverma.duduthetodo.domain.repository.TodoRepository

class GetTodoByIdUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: Int): Todo? {
        if (id == -1) {
            return null
        }
        return repository.getTodoById(id)
    }
}
