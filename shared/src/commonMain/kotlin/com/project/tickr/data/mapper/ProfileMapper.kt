package com.project.tickr.data.mapper

import com.project.tickr.data.remote.dto.ProfileDto
import com.project.tickr.domain.model.Profile

fun ProfileDto.toDomain() = Profile(
    id = id,
    fullName = fullName,
    updatedAt = updatedAt
)

fun Profile.toDto() = ProfileDto(
    id = id,
    fullName = fullName,
    updatedAt = updatedAt
)
