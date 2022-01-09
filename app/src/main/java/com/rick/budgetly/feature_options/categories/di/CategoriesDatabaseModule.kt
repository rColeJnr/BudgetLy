package com.rick.budgetly.feature_options.categories.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rick.budgetly.feature_options.categories.domain.ICategoryRepository
import com.rick.budgetly.feature_options.categories.persistence.CategoryDatabase
import com.rick.budgetly.feature_options.categories.persistence.CategoryRepositoryImpl
import com.rick.budgetly.feature_options.categories.util.CATEGORIES_DATABASE_NAME
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