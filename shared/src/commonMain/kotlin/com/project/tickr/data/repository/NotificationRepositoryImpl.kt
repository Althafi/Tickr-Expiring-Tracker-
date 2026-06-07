package com.project.tickr.data.repository

import com.project.tickr.core.result.DataResult
import com.project.tickr.core.result.mapData
import com.project.tickr.data.mapper.toDomain
import com.project.tickr.data.mapper.toDto
import com.project.tickr.data.remote.datasource.NotificationRemoteDataSource
import com.project.tickr.domain.model.NotificationLog
import com.project.tickr.domain.repository.NotificationRepository

class NotificationRepositoryImpl(
    private val remote: NotificationRemoteDataSource
) : NotificationRepository {

    override suspend fun getNotifications(): DataResult<List<NotificationLog>> =
        remote.getAll().mapData { list -> list.map { it.toDomain() } }

    override suspend fun getNotificationsByItem(itemId: Long): DataResult<List<NotificationLog>> =
        remote.getByItemId(itemId).mapData { list -> list.map { it.toDomain() } }

    override suspend fun getNotification(id: Long): DataResult<NotificationLog> =
        remote.getById(id).mapData { it.toDomain() }

    override suspend fun createNotification(notification: NotificationLog): DataResult<NotificationLog> =
        remote.insert(notification.toDto()).mapData { it.toDomain() }

    override suspend fun deleteNotification(id: Long): DataResult<Unit> =
        remote.delete(id)
}
