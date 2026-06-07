package com.project.tickr.data.repository

import com.project.tickr.core.result.DataResult
import com.project.tickr.core.result.mapData
import com.project.tickr.data.mapper.toDomain
import com.project.tickr.data.mapper.toDto
import com.project.tickr.data.remote.datasource.ProfileRemoteDataSource
import com.project.tickr.domain.model.Profile
import com.project.tickr.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val remote: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun getProfile(userId: String): DataResult<Profile> =
        remote.getById(userId).mapData { it.toDomain() }

    override suspend fun upsertProfile(profile: Profile): DataResult<Profile> =
        remote.upsert(profile.toDto()).mapData { it.toDomain() }
}
