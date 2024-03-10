package common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.BackStack
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.runtime.Navigator

@Composable
expect fun provideCircuitNavigator(
    backStack: SaveableBackStack,
    onRootPop: () -> Unit
): Navigator