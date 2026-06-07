package com.project.tickr.data.mapper

import com.project.tickr.data.remote.dto.NotificationLogDto
import com.project.tickr.domain.model.NotificationLog

fun NotificationLogDto.toDomain() = NotificationLog(
    id = id,
    itemId = itemId,
    scheduledDate = scheduledDate,
    isSent = isSent,
    createdAt = createdAt
)

fun NotificationLog.toDto() = NotificationLogDto(
    id = id,
    itemId = itemId,
    scheduledDate = scheduledDate,
    isSent = isSent,
    createdAt = createdAt
)
