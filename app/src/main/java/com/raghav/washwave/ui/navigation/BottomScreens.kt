package com.raghav.washwave.ui.navigation

import com.raghav.washwave.R
import kotlinx.serialization.Serializable


@Serializable
sealed class BottomScreens<T>(val name: String, val icon: Int, val route: T) {

    @Serializable
    data object Home :
        BottomScreens<HomeScreen>(name = "Home", icon = R.drawable.ic_home, route = HomeScreen)

    @Serializable
    data object Orders : BottomScreens<OrderScreen>(name = "My Orders", icon = R.drawable.ic_form, route = OrderScreen)


    @Serializable
    data object Rates : BottomScreens<RateListScreen>(name = "Rates", icon = R.drawable.ic_edit, route = RateListScreen)



}


