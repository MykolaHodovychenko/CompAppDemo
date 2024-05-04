package ua.opnu.compapp.data.model

import java.time.LocalDateTime
import java.util.Date

data class Note(
    val id: Long,
    val title: String,
    val contents: String,
    val isFavorite: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)