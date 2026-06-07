package com.project.tickr.domain.usecase.notification

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.NotificationLog
import com.project.tickr.domain.repository.NotificationRepository

class GetNotificationsUseCase(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(): DataResult<List<NotificationLog>> =
        repository.getNotifications()
}
