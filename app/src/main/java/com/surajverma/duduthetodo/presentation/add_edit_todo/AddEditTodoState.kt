package com.surajverma.duduthetodo.presentation.add_edit_todo

data class AddEditTodoState(
    val title: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)