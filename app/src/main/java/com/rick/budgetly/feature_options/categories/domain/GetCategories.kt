package com.rick.budgetly.feature_options.categories.domain

import kotlinx.coroutines.flow.Flow

class GetCategories(
    private val repository: ICategoryRepository
) {

    operator fun invoke(): Flow<List<Category>> =
        repository.getCategories()
}