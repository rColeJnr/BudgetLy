package com.rick.budgetly.feature_options.categories.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rick.budgetly.feature_options.categories.domain.Category

@Database(entities = [Category::class], version = 1, exportSchema = false)
abstract class CategoryDatabase: RoomDatabase() {

    abstract val categoriesDao: CategoriesDao

}