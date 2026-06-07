package com.project.tickr.data.remote.datasource

import com.project.tickr.core.result.DataResult
import com.project.tickr.data.remote.dto.ProfileDto
import io.github.jan.supabase.SupabaseClient

class ProfileRemoteDataSource(client: SupabaseClient) : BaseRemoteDataSource(client) {

    private val table = "profiles"

    suspend fun getById(userId: String): DataResult<ProfileDto> = safeApiCall {
        db.from(table).select { filter { eq("id", userId) } }.decodeSingle<ProfileDto>()
    }

    suspend fun upsert(dto: ProfileDto): DataResult<ProfileDto> = safeApiCall {
        db.from(table).upsert(dto) { select() }.decodeSingle<ProfileDto>()
    }
}
