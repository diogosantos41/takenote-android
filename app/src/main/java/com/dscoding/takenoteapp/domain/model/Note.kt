package com.dscoding.takenoteapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dscoding.takenoteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(LightPink, LightRed, LightYellow, LightGreen, LightBlue)
    }
}