package com.project.tickr.domain.usecase.item

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.Item
import com.project.tickr.domain.repository.ItemRepository

class GetItemsByUserUseCase(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(userId: String): DataResult<List<Item>> =
        repository.getItemsByUser(userId)
}
