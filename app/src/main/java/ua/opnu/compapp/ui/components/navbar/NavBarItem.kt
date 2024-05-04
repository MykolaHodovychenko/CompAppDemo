package ua.opnu.compapp.ui.components.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tag
import androidx.compose.ui.graphics.vector.ImageVector
import ua.opnu.compapp.MyApp
import ua.opnu.compapp.R
import ua.opnu.compapp.ui.navigation.Graph

sealed class NavBarItem(val route: String, val title: String, val icon: ImageVector) {

    companion object {
        val screens = listOf(NavBarItem.AllNotes, NavBarItem.Favorites, NavBarItem.Tags, NavBarItem.Settings)
    }

    object AllNotes : NavBarItem(
        route = Graph.ALL_NOTES,
        title = MyApp.instance.getString(R.string.navbar_label_allnotes),
        icon = Icons.Default.Description
    )

    object Favorites : NavBarItem(
        route = Graph.FAVORITES,
        title = MyApp.instance.getString(R.string.navbar_label_favorites),
        icon = Icons.Default.Favorite
    )

    object Tags : NavBarItem(
        route = Graph.TAGS,
        title = MyApp.instance.getString(R.string.navbar_label_tags),
        icon = Icons.Default.Tag
    )

    object Settings : NavBarItem(
        route = Graph.SETTINGS,
        title = MyApp.instance.getString(R.string.navbar_label_settings),
        icon = Icons.Default.Settings
    )
}