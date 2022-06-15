package com.rick.data_categories.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rick.data_categories.domain.Category

@Database(entities = [Category::class], version = 1, exportSchema = false)
abstract class CategoryDatabase: RoomDatabase() {

    abstract val categoriesDao: CategoriesDao

}