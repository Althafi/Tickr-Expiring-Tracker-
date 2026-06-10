package com.project.tickr.domain.model

data class ExpiringItem(
    val id: Long,
    val name: String,
    val categoryId: Long?,
    val categoryName: String,
    val categoryColorHex: String,
    val expiryDate: String,
    val imageUrl: String?,
    val quantity: Int,
    val unit: String,
    val daysUntilExpiry: Int,
    val urgency: Urgency,
)
