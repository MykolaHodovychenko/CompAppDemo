package ua.opnu.compapp.ui.screens.editnote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ua.opnu.compapp.ui.theme.AppTypography

@Composable
fun EditNoteScreen(
    id: Long,
    navController: NavController,
    viewModel: EditNoteViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val createMode = id == -1L

    if (!createMode) {
        viewModel.getNoteById(id)
    }

    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        ScreenTitle()
        Spacer(modifier = Modifier.height(32.dp))
        NoteTitle(viewModel, uiState)
        Spacer(modifier = Modifier.height(32.dp))
        NoteText(viewModel, uiState)
        Spacer(modifier = Modifier.weight(1.0f))
        Buttons(
            onAddClick = {
                if (createMode)
                    viewModel.addNote()
                else
                    viewModel.updateNote()
                navController.navigateUp()
            },
            onClearClick = {
                focusManager.clearFocus()
                viewModel.clearForm()
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ScreenTitle() {
    Text(text = "Add Note", textAlign = TextAlign.Center, style = AppTypography.headlineSmall)
}

@Composable
fun NoteTitle(viewModel: EditNoteViewModel, uiState: EditNoteUiState) {

    OutlinedTextField(
        value = uiState.title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onValueChange = viewModel::setTitle,
        label = { Text("Enter note title") },
        singleLine = true,
        maxLines = 1,
        textStyle = AppTypography.bodyMedium
    )
}

@Composable
fun NoteText(viewModel: EditNoteViewModel, uiState: EditNoteUiState) {

    OutlinedTextField(
        value = uiState.text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onValueChange = viewModel::setText,
        label = { Text("Enter note title") },
        singleLine = true,
        maxLines = 1,
        textStyle = AppTypography.bodyMedium
    )
}


@Composable
fun Buttons(onClearClick: () -> Unit, onAddClick: () -> Unit) {
    Row {
        Button(
            onClick = onClearClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
                .padding(horizontal = 8.dp)
        ) {
            Text("Clear")
        }
        Button(
            onClick = onAddClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
                .padding(horizontal = 8.dp)
        ) {
            Text("Add Note")
        }
    }
}
