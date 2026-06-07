package com.project.tickr.domain.usecase.category

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.Category
import com.project.tickr.domain.repository.CategoryRepository

class GetCategoriesByUserUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(userId: String): DataResult<List<Category>> =
        repository.getCategoriesByUser(userId)
}
