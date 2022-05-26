package com.rick.screen_categories.di

import android.content.Context
import androidx.room.Room
import com.rick.data_categories.domain.ICategoryRepository
import com.rick.data_categories.persistence.CategoryDatabase
import com.rick.data_categories.persistence.CategoryRepositoryImpl
import com.rick.data_categories.util.CATEGORIES_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CategoriesDatabaseModule {

    @Provides
    @Singleton
    fun providesCategoriesDatabase(@ApplicationContext context: Context): CategoryDatabase =
        Room.databaseBuilder(
            context,
            CategoryDatabase::class.java,
            CATEGORIES_DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideCategoriesRepository(categoriesDatabase: CategoryDatabase): ICategoryRepository =
        CategoryRepositoryImpl(categoriesDatabase.categoriesDao)

}