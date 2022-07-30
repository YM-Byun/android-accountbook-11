package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.woowahan.accountbook.ui.navigate.BottomNavItem
import com.woowahan.accountbook.ui.theme.OffWhite
import com.woowahan.accountbook.ui.theme.Purple
import com.woowahan.accountbook.ui.theme.White
import com.woowahan.accountbook.ui.theme.White50

@Composable
fun BottomNaviBar(navController: NavController) {
    val items = arrayOf(
        BottomNavItem.ItemList,
        BottomNavItem.Calendar,
        BottomNavItem.Analysis,
        BottomNavItem.Settings
    )

    BottomNavigation(
        backgroundColor = Purple,
        contentColor = OffWhite,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = White,
                unselectedContentColor = White50,
                selected = currentRoute == item.screenRoute
            )
        }
    }
}