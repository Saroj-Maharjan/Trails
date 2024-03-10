package common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeTabScaffold(
    flowingFeed: @Composable () -> Unit,
    trailFeed: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            .fillMaxSize()
    ) {
        Text(
            "Trails",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        val activeTab = remember {
            mutableStateOf(Tab.Following)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            Spacer(modifier = Modifier.width(24.dp))

            Row(horizontalArrangement = Arrangement.Center) {
                Tab(Tab.Following.label, activeTab.value == Tab.Following) {
                    activeTab.value = Tab.Following
                }

                Spacer(modifier = Modifier.width(8.dp))

                Tab(Tab.ForYou.label, activeTab.value == Tab.ForYou) {
                    activeTab.value = Tab.ForYou
                }

            }

            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(24.dp)
            )


        }

        Spacer(modifier = Modifier.height(24.dp))


        when (activeTab.value) {
            Tab.Following -> flowingFeed()

            Tab.ForYou -> trailFeed()
        }
    }

}

@Composable
fun Tab(
    text: String,
    isActive: Boolean,
    onSelect: () -> Unit,
) {
    val color =
        if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
    val fontWeight = if (isActive) FontWeight.Bold else FontWeight.Light
    val thickness = if (isActive) 4.dp else 0.dp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .widthIn(max = 80.dp)
            .clickable { onSelect() }) {
        Text(
            text,
            color = color,
            fontWeight = fontWeight,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))
        Box(
            Modifier
                .height(thickness)
                .fillMaxWidth()
                .background(color = color)
        )
    }
}

enum class Tab(
    val id: Int,
    val label: String
) {
    Following(0, "Following"),
    ForYou(1, "For You")
}