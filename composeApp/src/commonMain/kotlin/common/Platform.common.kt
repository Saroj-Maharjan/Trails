package common

import androidx.compose.runtime.Composable

expect object Platform {
    @Composable fun ReportDrawnWhen(predicate: () -> Boolean)

    val type: PlatformType
}

enum class PlatformType {
    IOS,
    ANDROID
}