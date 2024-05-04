package ua.opnu.compapp.ui.screens.editnote

import androidx.compose.runtime.Composable
import ua.opnu.compapp.ui.components.ScreenLabel

@Composable
fun EditNoteScreen(id: Long) {
    ScreenLabel(text = "EDIT NOTE WITH ID $id")
}