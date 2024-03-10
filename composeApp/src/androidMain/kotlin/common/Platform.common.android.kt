package common

import androidx.compose.runtime.Composable
import androidx.activity.compose.ReportDrawnWhen as PlatformReportDrawnWhen

actual object Platform {
    @Composable
    actual fun ReportDrawnWhen(predicate: () -> Boolean) {
        PlatformReportDrawnWhen(predicate)
    }

    actual val type: PlatformType
        get() = PlatformType.ANDROID

}