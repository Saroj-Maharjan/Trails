package screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.retained.rememberRetained
import common.BottomNavItem
import common.Platform
import common.composable.Fab
import common.composable.HomeTabScaffold
import common.ui.TrailTheme
import screen.HomeScreen.HomeEvents.ChildNav
import screen.HomeScreen.HomeEvents.ClickNavItem

@Composable
fun HomeScreenContent(
    state: HomeScreen.HomeState,
    modifier: Modifier = Modifier
) {
    var contentComposed by rememberRetained { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxWidth(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = Color.Transparent,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
            ) {
                Icon(Icons.Filled.Add, null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            TrailTheme {
                BottomNavigationBar(selectedIndex = state.selectedIndex) { index ->
                    state.eventSink(ClickNavItem(index))
                }
            }
        }
    ) { paddingValue ->
        contentComposed = true
        val screen = state.navItems[state.selectedIndex].screen

        CircuitContent(
            screen,
            modifier.padding(paddingValue),
            onNavEvent = { event -> state.eventSink(ChildNav(event)) }
        )
    }

    Platform.ReportDrawnWhen { contentComposed }
}

@Composable
fun BottomNavigationBar(selectedIndex: Int, onSelectedIndex: (Int) -> Unit) {
    // These are the buttons on the NavBar, they dictate where we navigate too
    val items = remember {
        listOf(
            BottomNavItem.Home,
            BottomNavItem.Saved,
            BottomNavItem.Activity,
            BottomNavItem.Profile
        )
    }
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        floatingActionButton = {
            Fab {

            }
        }, actions = {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                    label = { Text(text = item.title) },
                    alwaysShowLabel = true,
                    selected = selectedIndex == index,
                    onClick = { onSelectedIndex(index) },
                )
            }
        })
}