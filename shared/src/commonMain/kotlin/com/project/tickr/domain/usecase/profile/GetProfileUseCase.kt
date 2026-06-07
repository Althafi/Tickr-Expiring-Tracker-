package com.project.tickr.domain.usecase.profile

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.Profile
import com.project.tickr.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(userId: String): DataResult<Profile> =
        repository.getProfile(userId)
}
