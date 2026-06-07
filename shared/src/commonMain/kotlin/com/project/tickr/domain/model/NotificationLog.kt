package com.project.tickr.domain.model

data class NotificationLog(
    val id: Long,
    val itemId: Long,
    val scheduledDate: String,
    val isSent: Boolean,
    val createdAt: String
)
