package com.woowahan.accountbook.ui.navigate

import com.woowahan.accountbook.R

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {
    object ItemList : BottomNavItem("내역", R.drawable.ic_document_list, ITEM_LIST)
    object Calendar : BottomNavItem("달력", R.drawable.ic_calendar_month, CALENDAR)
    object Analysis : BottomNavItem("통계", R.drawable.ic_graph, ANALYSIS)
    object Settings : BottomNavItem("설정", R.drawable.ic_settings, SETTINGS)
    object AddItem : BottomNavItem("내역 등록", 0, ADD_ITEM)
}