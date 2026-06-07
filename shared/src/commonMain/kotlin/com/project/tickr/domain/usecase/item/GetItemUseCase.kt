package com.project.tickr.domain.usecase.item

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.Item
import com.project.tickr.domain.repository.ItemRepository

class GetItemUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(id: Long): DataResult<Item> =
        repository.getItem(id)
}
