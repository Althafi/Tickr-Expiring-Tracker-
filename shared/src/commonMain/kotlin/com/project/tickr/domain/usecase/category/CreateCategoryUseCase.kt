package com.project.tickr.domain.usecase.category

import com.project.tickr.core.result.DataResult
import com.project.tickr.core.validation.Validators
import com.project.tickr.domain.model.Category
import com.project.tickr.domain.repository.CategoryRepository

class CreateCategoryUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(category: Category): DataResult<Category> {
        Validators.requireNotBlank(category.name, "name")?.let {
            return DataResult.Error(it)
        }
        Validators.requireNotBlank(category.iconName, "icon_name")?.let {
            return DataResult.Error(it)
        }
        Validators.requireHexColor(category.colorHex, "color_hex")?.let {
            return DataResult.Error(it)
        }
        return repository.createCategory(category)
    }
}
