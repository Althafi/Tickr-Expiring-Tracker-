package com.project.tickr.domain.usecase.item

import com.project.tickr.core.result.DataResult
import com.project.tickr.core.validation.Validators
import com.project.tickr.domain.model.Item
import com.project.tickr.domain.repository.ItemRepository

class CreateItemUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(item: Item): DataResult<Item> {
        Validators.requireNotBlank(item.name, "name")?.let {
            return DataResult.Error(it)
        }
        Validators.requireIsoDate(item.expiryDate, "expiry_date")?.let {
            return DataResult.Error(it)
        }
        return repository.createItem(item)
    }
}
