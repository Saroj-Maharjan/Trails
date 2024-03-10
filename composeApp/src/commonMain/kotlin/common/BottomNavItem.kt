package common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.Screen
import screen.HomeScreen
import screen.main.MainScreen

private const val HOME_SCREEN_NAME = "Home"
private const val SAVED_SCREEN_NAME = "Saved"
private const val ACTIVITY_SCREEN_NAME = "Activity"
private const val PROFILE_SCREEN_NAME = "Profile"

sealed class BottomNavItem(val title: String, val icon: ImageVector) {
    abstract val screen: Screen

    data object Home : BottomNavItem(HOME_SCREEN_NAME, Icons.Filled.Home) {
        override val screen: Screen = MainScreen
    }

    data object Saved : BottomNavItem(SAVED_SCREEN_NAME, Icons.Filled.Bookmark) {
        override val screen: Screen = MainScreen
    }

    data object Activity : BottomNavItem(ACTIVITY_SCREEN_NAME, Icons.Filled.LocalActivity) {
        override val screen: Screen = MainScreen
    }

    data object Profile : BottomNavItem(PROFILE_SCREEN_NAME, Icons.Filled.People) {
        override val screen: Screen = MainScreen
    }
}