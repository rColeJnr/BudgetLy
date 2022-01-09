package com.rick.budgetly.feature_options.categories.persistence

import com.rick.budgetly.feature_options.categories.domain.Category
import com.rick.budgetly.feature_options.categories.domain.ICategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val categoriesDao: CategoriesDao
): ICategoryRepository {

    override fun getCategories(): Flow<List<Category>> =
        categoriesDao.getCategories()


    override fun getCategory(name: String): Flow<Category> =
        categoriesDao.getCategory(name)

}