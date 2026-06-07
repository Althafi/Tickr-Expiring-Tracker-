package com.project.tickr.data.remote.datasource

import com.project.tickr.core.result.DataResult
import com.project.tickr.data.remote.dto.NotificationLogDto
import io.github.jan.supabase.SupabaseClient

class NotificationRemoteDataSource(client: SupabaseClient) : BaseRemoteDataSource(client) {

    private val table = "notifications_log"

    suspend fun getAll(): DataResult<List<NotificationLogDto>> = safeApiCall {
        db.from(table).select().decodeList<NotificationLogDto>()
    }

    suspend fun getByItemId(itemId: Long): DataResult<List<NotificationLogDto>> = safeApiCall {
        db.from(table).select { filter { eq("item_id", itemId) } }.decodeList<NotificationLogDto>()
    }

    suspend fun getById(id: Long): DataResult<NotificationLogDto> = safeApiCall {
        db.from(table).select { filter { eq("id", id) } }.decodeSingle<NotificationLogDto>()
    }

    suspend fun insert(dto: NotificationLogDto): DataResult<NotificationLogDto> = safeApiCall {
        db.from(table).insert(dto) { select() }.decodeSingle<NotificationLogDto>()
    }

    suspend fun delete(id: Long): DataResult<Unit> = safeApiCall {
        db.from(table).delete { filter { eq("id", id) } }
        Unit
    }
}
