package com.rick.screen_categories.di

import com.rick.data_categories.domain.CategoriesUseCases
import com.rick.data_categories.domain.GetCategories
import com.rick.data_categories.domain.GetCategory
import com.rick.data_categories.domain.ICategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CategoriesRepositoryModule {

    @Singleton
    @Provides
    fun provideCategoriesUseCases(repository: ICategoryRepository): CategoriesUseCases =
        CategoriesUseCases(
            GetCategories(repository),
            GetCategory(repository)
        )

}