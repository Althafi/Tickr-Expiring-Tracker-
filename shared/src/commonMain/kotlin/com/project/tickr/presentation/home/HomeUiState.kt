package com.project.tickr.presentation.home

import com.project.tickr.domain.model.CategoryGroup
import com.project.tickr.domain.model.CategorySlice
import com.project.tickr.domain.model.WasteTrend

data class HomeUiState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val nowEpochMillis: Long = 0L,
    val displayDate: String = "",
    val displayTime: String = "",
    val wasteTrend: WasteTrend? = null,
    val slices: List<CategorySlice> = emptyList(),
    val totalItems: Int = 0,
    val expiringGroups: List<CategoryGroup> = emptyList(),
    val criticalCount: Int = 0,
    val error: String? = null,
) {
    val isEmpty: Boolean
        get() = !isLoading && error == null && expiringGroups.isEmpty() && slices.isEmpty()
}
