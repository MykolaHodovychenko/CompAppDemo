package ua.opnu.compapp.ui.screens.allnotes

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import ua.opnu.compapp.MyApp
import ua.opnu.compapp.data.model.Note

class AllNotesViewModel : ViewModel() {

    private val repository = MyApp.instance.repository

    val notes: Flow<List<Note>> = repository.notes

}