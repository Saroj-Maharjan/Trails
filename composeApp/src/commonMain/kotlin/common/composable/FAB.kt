package common.composable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun Fab(modifier: Modifier = Modifier,
        onclick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onclick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp)
    ){
        Icon(
            Icons.Default.LocationSearching,
            contentDescription = "Location",
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )
    }

}