package com.project.tickr.domain.usecase.profile

import com.project.tickr.core.result.AppError
import com.project.tickr.core.result.DataResult
import com.project.tickr.core.validation.Validators
import com.project.tickr.domain.model.Profile
import com.project.tickr.domain.repository.ProfileRepository

class UpsertProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(profile: Profile): DataResult<Profile> {
        Validators.requireMaxLength(profile.fullName, 100, "full_name")?.let {
            return DataResult.Error(it)
        }
        return repository.upsertProfile(profile)
    }
}
