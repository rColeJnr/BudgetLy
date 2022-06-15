package com.rick.data_categories.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    val amountSpent: Float,
    val icon: Int,
)

enum class CategoryImages (image: ImageVector){
    FOOD(Icons.Default.Fastfood),
    TRANSPORT(Icons.Default.EmojiTransportation),
    GROCERIES(Icons.Default.LocalGroceryStore),
    SHOPPING(Icons.Default.ShoppingBag),
    HEALTH(Icons.Default.HealthAndSafety),
    LEISURE(Icons.Default.Gamepad),
    FAMILY(Icons.Default.People)

}

class CategoryException(message: String): Exception(message)
