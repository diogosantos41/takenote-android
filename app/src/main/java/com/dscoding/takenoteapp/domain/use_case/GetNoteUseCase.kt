package com.dscoding.takenoteapp.domain.use_case

import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.domain.repository.NoteRepository

class GetNoteUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}