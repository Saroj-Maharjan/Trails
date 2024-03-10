package common

import androidx.compose.runtime.Composable

actual object Platform {
    @Composable
    actual fun ReportDrawnWhen(predicate: () -> Boolean) {
        // No-op
    }

    actual val type: PlatformType
        get() = PlatformType.IOS

}