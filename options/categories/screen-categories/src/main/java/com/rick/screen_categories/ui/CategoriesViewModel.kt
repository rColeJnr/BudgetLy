package com.rick.screen_categories.ui

import androidx.lifecycle.ViewModel
import com.rick.data_categories.domain.CategoriesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCases: CategoriesUseCases
) : ViewModel(){
}