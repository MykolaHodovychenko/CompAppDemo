package ua.opnu.compapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ua.opnu.compapp.ui.components.navbar.NavBar
import ua.opnu.compapp.ui.screens.allnotes.AllNotesScreen
import ua.opnu.compapp.ui.screens.editnote.EditNoteScreen
import ua.opnu.compapp.ui.screens.favorites.FavoritesScreen
import ua.opnu.compapp.ui.screens.settings.SettingsScreen
import ua.opnu.compapp.ui.screens.tags.TagsScreen
import ua.opnu.compapp.ui.screens.viewnote.ViewNoteScreen

private const val CREATE_NOTE_CODE = -1

@Composable
fun RootGraph(navController: NavHostController = rememberNavController()) {

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route

    val showFab = route?.let { it in Graph.ALL_NOTES } ?: false
    val showNavBar = route?.let { !(Graph.VIEW_NOTE in it || Graph.EDIT_NOTE in it) } ?: true

    Scaffold(

        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },

        floatingActionButton = {
            if (showFab)
                FAB(navController = navController)
        },

        bottomBar = {
            if (showNavBar)
                NavBar(navController = navController)
        }

    ) {
        NavHost(
            navController,
            modifier = Modifier.padding(it),
            startDestination = Graph.ALL_NOTES
        ) {
            composable(route = Graph.ALL_NOTES) {
                AllNotesScreen(
                    navController,
                    snackBarHostState,
                    scope
                )
            }
            composable(route = Graph.FAVORITES) { FavoritesScreen() }
            composable(route = Graph.TAGS) { TagsScreen() }
            composable(route = Graph.SETTINGS) { SettingsScreen() }

            composable(
                route = "${Graph.VIEW_NOTE}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) { entry ->
                ViewNoteScreen(id = entry.arguments?.getLong("id") ?: -1)
            }

            composable(
                route = "${Graph.EDIT_NOTE}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.LongType })
            ) { entry ->
                EditNoteScreen(id = entry.arguments?.getLong("id") ?: -1)
            }
        }
    }
}

@Composable
fun FAB(navController: NavHostController) {
    FloatingActionButton(onClick = { navController.navigate("${Graph.EDIT_NOTE}/${CREATE_NOTE_CODE}") }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Create Note")
    }
}

object Graph {
    const val ALL_NOTES = "all_notes"
    const val FAVORITES = "favorites"
    const val TAGS = "tags"
    const val SETTINGS = "settings"
    const val VIEW_NOTE = "view_note"
    const val EDIT_NOTE = "edit_note"
}