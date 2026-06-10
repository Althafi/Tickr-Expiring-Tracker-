package com.project.tickr.presentation.home

import com.project.tickr.presentation.common.mvi.UiAction
import com.project.tickr.presentation.navigation.Destination

sealed interface HomeAction : UiAction {
    data object Load : HomeAction
    data object Refresh : HomeAction
    data object OpenNotifications : HomeAction
    data object SeeAllCategories : HomeAction
    data object OpenAddItem : HomeAction
    data class ClickItem(val id: Long) : HomeAction
    data class TabSelected(val destination: Destination) : HomeAction
}
