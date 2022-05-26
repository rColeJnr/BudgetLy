package com.rick.data_categories.persistence

import com.rick.data_categories.domain.Category
import com.rick.data_categories.domain.ICategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val categoriesDao: CategoriesDao
): ICategoryRepository {

    override fun getCategories(): Flow<List<Category>> =
        categoriesDao.getCategories()


    override fun getCategory(name: String): Flow<Category> =
        categoriesDao.getCategory(name)

}