package com.rick.data_categories.domain

import kotlinx.coroutines.flow.Flow

class GetCategories(
    private val repository: ICategoryRepository
) {

    operator fun invoke(): Flow<List<Category>> =
        repository.getCategories()
}