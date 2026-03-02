package com.surajverma.duduthetodo.navigation

import com.surajverma.duduthetodo.presentation.add_edit_todo.AddEditTodoScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.surajverma.duduthetodo.presentation.todo_list.TodoListScreen

@Composable
fun TodoNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.TodoList.route
    ) {
        composable(Screen.TodoList.route) {
            TodoListScreen(
                onNavigateToAddEdit = { todoId ->
                    if (todoId != null) {
                        navController.navigate(Screen.AddEditTodo.route + "/$todoId")
                    } else {
                        navController.navigate(Screen.AddEditTodo.route + "/-1")
                    }
                }
            )
        }

        composable(
            route = Screen.AddEditTodo.route + "/{todoId}",
            arguments = listOf(
                navArgument("todoId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditTodoScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}