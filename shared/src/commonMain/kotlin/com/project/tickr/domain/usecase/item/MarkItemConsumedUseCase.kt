package com.project.tickr.domain.usecase.item

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.Item
import com.project.tickr.domain.repository.ItemRepository

class MarkItemConsumedUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(item: Item, consumed: Boolean): DataResult<Item> {
        val updated = item.copy(isConsumed = consumed)
        return repository.updateItem(updated)
    }
}
