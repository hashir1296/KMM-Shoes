package composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ShoesListingScreen() {
    val colorStopsBg = listOf(Color(32,
                                  32,
                                  43),
                            Color(66,
                                  66,
                                  81))

    val colorStops = listOf(Color(45,
                                    43,
                                    57),
                              Color(66,
                                    66,
                                    81))

    Column(modifier = Modifier.fillMaxSize()
        .background(brush = Brush.verticalGradient(colorStopsBg))
        .padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth()
            .weight(2f),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Explore\nCollections",
                 color = Color.White,
                 fontSize = 40.sp, lineHeight = 55.sp, fontWeight = FontWeight.Medium)
            Box(modifier = Modifier.height(100.dp)
                .wrapContentWidth()
                .clip(RoundedCornerShape(40))
                .background(brush = Brush.verticalGradient(colorStops))
                .padding(vertical = 10.dp,
                         horizontal = 25.dp),
                contentAlignment = Alignment.Center) {
                Image(painter = painterResource("search-icon.xml"),
                      contentDescription = null)
            }
        }
    }

}

