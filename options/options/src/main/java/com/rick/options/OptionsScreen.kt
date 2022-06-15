package com.rick.options

enum class OptionsScreen {
    Options,
    Overview,
    Transactions,
    Categories,
    Settings;

    companion object {
        // idk how this keeps track of the backstack
        fun fromRoute(route: String?) : OptionsScreen =
            when (route?.substringBefore("/")){
                Options.name -> Options
                Overview.name -> Overview
                Transactions.name -> Transactions
                Categories.name -> Categories
                Settings.name -> Settings
                null -> Options
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
    }
}