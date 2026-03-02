package com.surajverma.duduthetodo.presentation.todo_list

import com.surajverma.duduthetodo.domain.model.Todo

data class TodoListState(
    val todos: List<Todo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)