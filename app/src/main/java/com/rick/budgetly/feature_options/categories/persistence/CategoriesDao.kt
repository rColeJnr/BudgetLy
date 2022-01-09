package com.rick.budgetly.feature_options.categories.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rick.budgetly.feature_options.categories.domain.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM category ORDER BY name")
    fun getCategories(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE name = :name")
    fun getCategory(name: String): Flow<Category>

}