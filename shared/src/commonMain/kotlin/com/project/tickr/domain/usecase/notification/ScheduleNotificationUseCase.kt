package com.project.tickr.domain.usecase.notification

import com.project.tickr.core.result.AppError
import com.project.tickr.core.result.DataResult
import com.project.tickr.core.util.DateTimeUtil
import com.project.tickr.core.validation.Validators
import com.project.tickr.domain.model.NotificationLog
import com.project.tickr.domain.repository.NotificationRepository

class ScheduleNotificationUseCase(
    private val repository: NotificationRepository,
    private val dateTime: DateTimeUtil
) {
    suspend operator fun invoke(log: NotificationLog): DataResult<NotificationLog> {
        Validators.requireIsoDate(log.scheduledDate, "scheduled_date")?.let {
            return DataResult.Error(it)
        }
        if (dateTime.isPast(log.scheduledDate)) {
            return DataResult.Error(AppError.Validation("scheduled_date", "cannot be in the past"))
        }
        return repository.createNotification(log)
    }
}
