package com.dscoding.takenoteapp.presentation.list_notes

import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.util.NoteOrder
import com.dscoding.takenoteapp.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
