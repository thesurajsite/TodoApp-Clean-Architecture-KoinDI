package com.surajverma.duduthetodo.presentation.todo_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajverma.duduthetodo.domain.model.Todo
import com.surajverma.duduthetodo.domain.usecase.DeleteTodoUseCase
import com.surajverma.duduthetodo.domain.usecase.GetAllTodosUseCase
import com.surajverma.duduthetodo.domain.usecase.ToggleTodoCompletionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val toggleTodoCompletionUseCase: ToggleTodoCompletionUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TodoListState())
    val state: State<TodoListState> = _state

    private var getTodosJob: Job? = null

    init {
        getTodos()
    }

    private fun getTodos() {
        getTodosJob?.cancel()
        getTodosJob = getAllTodosUseCase()
            .onEach { todos ->
                _state.value = _state.value.copy(
                    todos = todos,
                    isLoading = false
                )
            }
            .launchIn(viewModelScope)
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }

    fun toggleTodoCompletion(todoId: Int) {
        viewModelScope.launch {
            toggleTodoCompletionUseCase(todoId)
        }
    }
}
