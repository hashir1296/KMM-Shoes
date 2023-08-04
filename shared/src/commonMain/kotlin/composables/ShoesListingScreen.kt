package composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.resources.compose.stringResource
@Composable
fun ShoesListingScreen() {
    val colorStops = arrayOf(1f to Color(33,
                                         32,
                                         44),
                             0.4f to Color(33,
                                           32,
                                           44))

    Surface(modifier = Modifier.fillMaxSize()
        .background(brush = Brush.verticalGradient(colorStops = colorStops))) {
        Column(modifier = Modifier.fillMaxSize()) {
        }
    }
}