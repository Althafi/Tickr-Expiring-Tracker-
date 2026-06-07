package com.project.tickr.domain.usecase.item

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.repository.ItemRepository

class DeleteItemUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(id: Long): DataResult<Unit> =
        repository.deleteItem(id)
}
