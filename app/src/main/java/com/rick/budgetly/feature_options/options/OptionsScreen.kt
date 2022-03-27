package com.rick.budgetly.feature_options.options

enum class OptionsScreen {
    Options,
    Overview,
    Transactions,
    Categories;

    companion object {
        // idk how this keeps track of the backstack
        fun fromRoute(route: String?) : OptionsScreen =
            when (route?.substringBefore("/")){
                Options.name -> Options
                Overview.name -> Overview
                Transactions.name -> Transactions
                Categories.name -> Categories
                null -> Options
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}