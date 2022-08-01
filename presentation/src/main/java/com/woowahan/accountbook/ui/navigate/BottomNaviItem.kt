package com.woowahan.accountbook.ui.navigate

import com.woowahan.accountbook.R

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {
    object ItemList : BottomNavItem("내역", R.drawable.ic_document_list, ITEM_LIST)
    object Calendar : BottomNavItem("달력", R.drawable.ic_calendar_month, CALENDAR)
    object Analysis : BottomNavItem("통계", R.drawable.ic_graph, ANALYSIS)
    object Settings : BottomNavItem("설정", R.drawable.ic_settings, SETTINGS)
    object AddRecordItem : BottomNavItem("내역 등록", 0, ADD_ITEM)
    object AddPayments : BottomNavItem("결제 수단 추가하기", 0, ADD_PAYMENTS)
    object AddIncome : BottomNavItem("수입 카테고리 추기", 0, ADD_INCOME)
    object AddSpending : BottomNavItem("지출 카테고리 추가", 0, ADD_SPENDING)
}