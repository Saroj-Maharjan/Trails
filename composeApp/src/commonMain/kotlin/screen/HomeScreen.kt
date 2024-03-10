package screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.foundation.NavEvent
import com.slack.circuit.foundation.onNavEvent
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import common.BottomNavItem
import common.CommonParcelize
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import screen.HomeScreen.HomeEvents.ChildNav
import screen.HomeScreen.HomeEvents.ClickNavItem
import screen.HomeScreen.HomeEvents.FollowingClicked
import screen.HomeScreen.HomeEvents.ForYouClicked


@CommonParcelize
object HomeScreen : Screen {
    data class HomeState(
        val navItems: ImmutableList<BottomNavItem> =
            persistentListOf(
                BottomNavItem.Home,
                BottomNavItem.Saved,
                BottomNavItem.Activity,
                BottomNavItem.Profile
            ),
        val selectedIndex: Int = 0,
        val eventSink: (HomeEvents) -> Unit = {}
    ) : CircuitUiState

    sealed interface HomeEvents : CircuitUiEvent {
        data class ClickNavItem(val index: Int) : HomeEvents

        data class ChildNav(val navEvent: NavEvent) : HomeEvents

        data object FollowingClicked : HomeEvents
        data object ForYouClicked : HomeEvents
    }
}

class HomeScreenPresenter(
    private val navigator: Navigator,
) : Presenter<HomeScreen.HomeState> {

    @Composable
    override fun present(): HomeScreen.HomeState {
        var selectedIndex by remember { mutableIntStateOf(0) }
        return HomeScreen.HomeState(selectedIndex = selectedIndex) { event ->
            when (event) {
                is ClickNavItem -> selectedIndex = event.index
                is ChildNav -> navigator.onNavEvent(event.navEvent)
                is FollowingClicked -> {

                }

                is ForYouClicked -> {

                }

                else -> {

                }
            }
        }
    }

}

class HomeUIFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            HomeScreen -> {
                homeUI()
            }
            else -> null
        }
    }
}

private fun homeUI() = ui<HomeScreen.HomeState> { state, modifier ->
    HomeScreenContent(state)
}

class HomeScreenPresenterFactory : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is HomeScreen -> HomeScreenPresenter(navigator)
            else -> null
        }
    }

}