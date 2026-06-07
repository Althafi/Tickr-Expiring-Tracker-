package com.project.tickr.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationLogDto(
    @SerialName("id") val id: Long,
    @SerialName("item_id") val itemId: Long,
    @SerialName("scheduled_date") val scheduledDate: String,
    @SerialName("is_sent") val isSent: Boolean,
    @SerialName("created_at") val createdAt: String
)
