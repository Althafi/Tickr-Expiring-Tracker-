package com.project.tickr.domain.model

data class Category(
    val id: Long,
    val userId: String?,
    val name: String,
    val iconName: String,
    val colorHex: String,
    val createdAt: String
)
