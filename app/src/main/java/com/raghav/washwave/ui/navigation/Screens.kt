package com.raghav.washwave.ui.navigation

import kotlinx.serialization.Serializable


sealed class Screen


@Serializable
data object HomeScreen : Screen()

@Serializable
data object OrderScreen : Screen()

@Serializable
data object RateListScreen : Screen()

@Serializable
data object CheckoutScreen : Screen()
