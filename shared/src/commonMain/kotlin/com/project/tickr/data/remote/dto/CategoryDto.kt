package com.project.tickr.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerialName("id") val id: Long,
    @SerialName("user_id") val userId: String? = null,
    @SerialName("name") val name: String,
    @SerialName("icon_name") val iconName: String,
    @SerialName("color_hex") val colorHex: String,
    @SerialName("created_at") val createdAt: String
)
