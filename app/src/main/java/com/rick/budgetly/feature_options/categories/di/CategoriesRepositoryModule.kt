package com.rick.budgetly.feature_options.categories.di

import com.rick.budgetly.feature_options.categories.domain.CategoriesUseCases
import com.rick.budgetly.feature_options.categories.domain.GetCategories
import com.rick.budgetly.feature_options.categories.domain.GetCategory
import com.rick.budgetly.feature_options.categories.domain.ICategoryRepository
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