package com.project.tickr.domain.repository

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfile(userId: String): DataResult<Profile>
    suspend fun upsertProfile(profile: Profile): DataResult<Profile>
}
