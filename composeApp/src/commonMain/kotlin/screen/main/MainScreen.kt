package screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.foundation.onNavEvent
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import common.CommonParcelize

@CommonParcelize
object MainScreen : Screen {
    data class State(
        val isRefreshing: Boolean,

        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object Refresh : Event
    }
}

class MainScreenPresenter(
    private val navigator: Navigator,
) : Presenter<MainScreen.State> {

    @Composable
    override fun present(): MainScreen.State {
        var isRefreshing by remember { mutableStateOf(false) }
        if (isRefreshing) {
            LaunchedEffect(Unit) {
//                petRepo.refreshData()
                isRefreshing = false
            }
        }

        return MainScreen.State(isRefreshing) { event ->
            when (event) {
                is MainScreen.Event.Refresh -> {

                }
            }
        }
    }

}

class MainUIFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            MainScreen -> {
                MainUI()
            }

            else -> null
        }
    }
}

private fun MainUI() = ui<MainScreen.State> { state, modifier ->
    MainScreenContent(state)
}

class MainScreenPresenterFactory : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext
    ): Presenter<*>? {
        return when (screen) {
            is MainScreen -> MainScreenPresenter(navigator)
            else -> null
        }
    }

}