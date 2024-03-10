package common.navigation

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.Navigator

/*
* Provide implementation of [Navigator] for IOS apps.
* */
@Composable
actual fun provideCircuitNavigator(
    backStack: SaveableBackStack,
    onRootPop: () -> Unit
): Navigator {
    return rememberCircuitNavigator(
        backstack = backStack,
        onRootPop = onRootPop,
    )
}