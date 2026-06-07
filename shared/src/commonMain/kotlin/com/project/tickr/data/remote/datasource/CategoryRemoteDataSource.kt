package com.project.tickr.data.remote.datasource

import com.project.tickr.core.result.DataResult
import com.project.tickr.data.remote.dto.CategoryDto
import io.github.jan.supabase.SupabaseClient

class CategoryRemoteDataSource(client: SupabaseClient) : BaseRemoteDataSource(client) {

    private val table = "categories"

    suspend fun getAll(): DataResult<List<CategoryDto>> = safeApiCall {
        db.from(table).select().decodeList<CategoryDto>()
    }

    suspend fun getByUserId(userId: String): DataResult<List<CategoryDto>> = safeApiCall {
        db.from(table).select { filter { eq("user_id", userId) } }.decodeList<CategoryDto>()
    }

    suspend fun getById(id: Long): DataResult<CategoryDto> = safeApiCall {
        db.from(table).select { filter { eq("id", id) } }.decodeSingle<CategoryDto>()
    }

    suspend fun insert(dto: CategoryDto): DataResult<CategoryDto> = safeApiCall {
        db.from(table).insert(dto) { select() }.decodeSingle<CategoryDto>()
    }

    suspend fun update(id: Long, dto: CategoryDto): DataResult<CategoryDto> = safeApiCall {
        db.from(table).update(dto) { filter { eq("id", id) }; select() }.decodeSingle<CategoryDto>()
    }

    suspend fun delete(id: Long): DataResult<Unit> = safeApiCall {
        db.from(table).delete { filter { eq("id", id) } }
        Unit
    }
}
