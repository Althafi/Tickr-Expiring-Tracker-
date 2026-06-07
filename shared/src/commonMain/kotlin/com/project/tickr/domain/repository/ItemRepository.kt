package com.project.tickr.domain.repository

import com.project.tickr.core.result.DataResult
import com.project.tickr.domain.model.Item

interface ItemRepository {
    suspend fun getItems(): DataResult<List<Item>>
    suspend fun getItemsByUser(userId: String): DataResult<List<Item>>
    suspend fun getItem(id: Long): DataResult<Item>
    suspend fun createItem(item: Item): DataResult<Item>
    suspend fun updateItem(item: Item): DataResult<Item>
    suspend fun deleteItem(id: Long): DataResult<Unit>
}
