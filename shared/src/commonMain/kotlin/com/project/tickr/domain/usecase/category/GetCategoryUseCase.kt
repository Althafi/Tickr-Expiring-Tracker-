package com.project.tickr.domain.usecase.category

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.Category
import com.project.tickr.domain.repository.CategoryRepository

class GetCategoryUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(id: Long): DataResult<Category> =
        repository.getCategory(id)
}
