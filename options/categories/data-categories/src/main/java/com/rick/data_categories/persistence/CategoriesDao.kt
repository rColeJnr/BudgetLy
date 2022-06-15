package com.rick.data_categories.persistence

import androidx.room.Dao
import androidx.room.Query
import com.rick.data_categories.domain.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM category ORDER BY name")
    fun getCategories(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE name = :name")
    fun getCategory(name: String): Flow<Category>

}