package com.rick.screen_categories.ui

import com.rick.data_categories.domain.Category

sealed class CategoriesEvents {

    object ToggleEditMode: CategoriesEvents()
    object Save: CategoriesEvents()
    object Cancel: CategoriesEvents()
    data class Delete(val category: Category): CategoriesEvents()
    data class NameEntered(val name: String): CategoriesEvents()
    data class ImageEntered(val imageVector: Int): CategoriesEvents()

}