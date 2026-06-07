package com.project.tickr.domain.usecase.notification

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.NotificationLog
import com.project.tickr.domain.repository.NotificationRepository

class GetNotificationsByItemUseCase(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(itemId: Long): DataResult<List<NotificationLog>> =
        repository.getNotificationsByItem(itemId)
}
