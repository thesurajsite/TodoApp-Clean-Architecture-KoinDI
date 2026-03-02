package com.surajverma.duduthetodo.presentation.todo_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.surajverma.duduthetodo.presentation.todo_list.components.TodoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    onNavigateToAddEdit: (Int?) -> Unit,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Dudu The Todo") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateToAddEdit(null)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "null"
                )
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.todos.isEmpty()) {
                Text(
                    text = "Click on the + to Add Todo",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        state.todos,
                        { it.id }
                    ) { todo ->
                        TodoItem(
                            todo = todo,
                            onToggleComplete = {
                                viewModel.toggleTodoCompletion(todo.id)
                            },
                            onDelete = {
                                viewModel.deleteTodo(todo)
                            },
                            onClick = {
                                onNavigateToAddEdit(todo.id)
                            }
                        )
                    }
                }
            }
        }
    }
}
