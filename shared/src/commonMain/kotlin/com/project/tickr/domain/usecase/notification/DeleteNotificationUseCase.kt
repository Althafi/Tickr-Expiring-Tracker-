package com.project.tickr.domain.usecase.notification

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.repository.NotificationRepository

class DeleteNotificationUseCase(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(id: Long): DataResult<Unit> =
        repository.deleteNotification(id)
}
