package com.project.tickr.domain.usecase.category

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.Category
import com.project.tickr.domain.repository.CategoryRepository

class GetCategoriesUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): DataResult<List<Category>> =
        repository.getCategories()
}
