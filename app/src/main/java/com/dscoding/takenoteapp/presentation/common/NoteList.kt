package com.dscoding.takenoteapp.presentation.common

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.domain.model.Note
import com.dscoding.takenoteapp.presentation.list_notes.components.NoteItem

@Composable
fun NoteList(
    notes: List<Note>,
    emptyMessage: String,
    showGridView: Boolean = false,
    showDeleteButton: Boolean = false,
    onNoteClicked: (Note) -> Unit = {},
    onDeleteClicked: (Note) -> Unit = {},
) {
    val generalMargin = dimensionResource(R.dimen.margin)

    if (notes.isEmpty()) {
        EmptyListAlert(emptyMessage = emptyMessage)
    } else {
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
            columns = StaggeredGridCells.Fixed(if (showGridView) 2 else 1),
            verticalItemSpacing = generalMargin,
            horizontalArrangement = Arrangement.spacedBy(generalMargin)
        ) {
            itemsIndexed(notes) { index, note ->
                NoteItem(
                    note = note,
                    isLastItem = index == notes.size - 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNoteClicked(note) },
                    showDeleteButton = showDeleteButton,
                    onDeleteClick = { onDeleteClicked(note) }
                )
            }
        }
    }
}