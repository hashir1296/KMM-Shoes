import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView() {
    val systemUIController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUIController,
                     useDarkIcons) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUIController.setSystemBarsColor(color = Color.Red,
                                              darkIcons = useDarkIcons)

        systemUIController.setStatusBarColor(Color(32,
                                                   32,
                                                   43))
        systemUIController.setNavigationBarColor( Color(
                53,
                54,
                61,
        ))

        onDispose {}
    }
    App()
}

