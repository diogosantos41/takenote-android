package com.dscoding.takenoteapp.presentation.list_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.use_case.NoteUseCases
import com.dscoding.takenoteapp.domain.use_case.PreferencesUseCases
import com.dscoding.takenoteapp.domain.util.NoteOrder
import com.dscoding.takenoteapp.domain.util.OrderType
import com.dscoding.takenoteapp.utils.extensions.logDeleteNote
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    private val preferencesUseCases: PreferencesUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private lateinit var noteToDelete: Note

    private var getPreferencesJob: Job? = null

    private var getNotesJob: Job? = null

    init {
        getPreferences()
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.ClickDeleteNote -> {
                _state.value = state.value.copy(
                    showDeleteConfirmationDialog = true
                )
                noteToDelete = event.note
            }
            is NotesEvent.ConfirmDeleteNote -> {
                Firebase.analytics.logDeleteNote()
                viewModelScope.launch {
                    noteUseCases.deleteNote(noteToDelete)
                }
                _state.value = state.value.copy(
                    showDeleteConfirmationDialog = false
                )
            }
            is NotesEvent.ShowConfirmDeleteNoteDialog -> {
                _state.value = state.value.copy(
                    showDeleteConfirmationDialog = event.toShowDialog
                )
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is NotesEvent.ToggleGridList -> {
                _state.value = state.value.copy(
                    isGridListSelected = !state.value.isGridListSelected
                )
            }
        }
    }

    private fun getPreferences() {
        getPreferencesJob?.cancel()
        getPreferencesJob = preferencesUseCases.getPreferences()
            .onEach { preferences ->
                _state.value = state.value.copy(
                    isGreetingSectionVisible = preferences.showGreeting
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}