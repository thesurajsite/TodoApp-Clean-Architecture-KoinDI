package com.surajverma.duduthetodo.presentation.add_edit_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajverma.duduthetodo.domain.model.Todo
import com.surajverma.duduthetodo.domain.usecase.AddTodoUseCase
import com.surajverma.duduthetodo.domain.usecase.GetTodoByIdUseCase
import com.surajverma.duduthetodo.domain.usecase.UpdateTodoUseCase
import kotlinx.coroutines.launch


class AddEditTodoViewModel (
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddEditTodoState())
    val state: State<AddEditTodoState> = _state

    private var currentTodoId: Int? = null

    init {
        savedStateHandle.get<Int>("todoId")?.let { todoId ->
            viewModelScope.launch {
                val todo = getTodoByIdUseCase(todoId)
                if (todo != null) {
                    currentTodoId = todo.id
                    _state.value = _state.value.copy(
                        title = todo.title,
                        description = todo.description
                    )
                }
            }
        }
    }

    fun onTitleChange(value: String) {
        _state.value = _state.value.copy(title = value)
    }

    fun onDescriptionChange(value: String) {
        _state.value = _state.value.copy(description = value)
    }

    fun saveTodo() {
        viewModelScope.launch {
            val todo = Todo(
                id = currentTodoId ?: 0,
                title = state.value.title,
                description = state.value.description
            )

            if (currentTodoId != null) {
                updateTodoUseCase(todo)
            } else {
                addTodoUseCase(todo)
            }
        }
    }
}
