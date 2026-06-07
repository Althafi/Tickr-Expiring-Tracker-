package com.project.tickr.domain.usecase.category

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.repository.CategoryRepository

class DeleteCategoryUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(id: Long): DataResult<Unit> =
        repository.deleteCategory(id)
}
