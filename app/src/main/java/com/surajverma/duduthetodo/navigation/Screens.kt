package com.surajverma.duduthetodo.navigation

sealed class Screen(val route: String) {
    object TodoList : Screen("todo_list")
    object AddEditTodo : Screen("add_edit_todo")
}