import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import common.navigation.provideCircuitNavigator
import common.ui.TrailTheme
import common.ui.TrailTopBar
import screen.HomeScreen
import screen.HomeScreenPresenter
import screen.HomeScreenPresenterFactory
import screen.HomeUIFactory
import screen.main.MainScreenPresenterFactory
import screen.main.MainUIFactory

@Composable
fun TrailApp(
    modifier: Modifier = Modifier
) {
    TrailTheme {
        val circuitConfig = Circuit.Builder()
            .addUiFactories(
                listOf(
                    HomeUIFactory(),
//                    MainUIFactory(),
                )
            )
            .addPresenterFactories(
                listOf(
                    HomeScreenPresenterFactory(),
//                    MainScreenPresenterFactory()
                )
            )
            .build()

        CircuitCompositionLocals(circuitConfig) {
            val backStack = rememberSaveableBackStack {
                push(HomeScreen)
            }

            val navigator = provideCircuitNavigator(backStack) {
                println("Is this being called?")
            }

            Surface(modifier = modifier) {
                Column {
                    NavigableCircuitContent(
                        navigator = navigator,
                        backStack = backStack,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}