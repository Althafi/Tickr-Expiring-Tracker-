package com.project.tickr.data.mapper

import com.project.tickr.data.remote.dto.CategoryDto
import com.project.tickr.domain.model.Category

fun CategoryDto.toDomain() = Category(
    id = id,
    userId = userId,
    name = name,
    iconName = iconName,
    colorHex = colorHex,
    createdAt = createdAt
)

fun Category.toDto() = CategoryDto(
    id = id,
    userId = userId,
    name = name,
    iconName = iconName,
    colorHex = colorHex,
    createdAt = createdAt
)
