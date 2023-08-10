package composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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
        Column(modifier = Modifier.fillMaxSize()
            .background(Color.Black)) {
            Row(modifier = Modifier.fillMaxWidth()
                .weight(2f),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Explore\nCollections",
                     color = Color.White,
                     fontSize = 100.sp,
                     modifier = Modifier.weight(1f))
                Box(modifier = Modifier.clip(RoundedCornerShape(40))
                    .background(Color.DarkGray)
                    .weight(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "Search",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 100.sp,
                    )
                }
            }
        }
    }
}

