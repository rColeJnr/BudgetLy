package com.rick.data_categories.domain

import kotlinx.coroutines.flow.Flow

class GetCategory(
    private val repository: ICategoryRepository
) {

    operator fun invoke(name: String): Flow<Category> =
        repository.getCategory(name)
}