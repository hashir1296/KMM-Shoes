package composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
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
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Explore\nCollections",
                 color = Color.White,
                 fontSize = 40.sp,
                 lineHeight = 55.sp,
                 fontWeight = FontWeight.Medium)

            RoundBox(modifier = Modifier.wrapContentHeight().padding(5.dp),
                     colors = colorStops.reversed()) {
                RoundBox(colors = colorStops,
                         modifier = Modifier.height(100.dp).padding(vertical = 10.dp,
                                                     horizontal = 25.dp)) {
                    Image(painter = painterResource("search-icon.xml"),
                          contentDescription = null)
                }
            }
        }
        Spacer(Modifier.height(40.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            listOfBrands().forEach { brandName ->
                RoundBox(modifier = Modifier.wrapContentHeight().padding(horizontal = 20.dp,
                                                     vertical = 18.dp),
                         colors = colorStops, shape = RoundedCornerShape(70)) {
                    Text(text = brandName, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun RoundBox(
        colors: List<Color>,
        modifier: Modifier = Modifier,
        shape : Shape= RoundedCornerShape(40),
        content: @Composable () -> Unit,
) {
    Box(modifier = Modifier.wrapContentWidth()
        .clip(shape)
        .background(brush = Brush.verticalGradient(colors))
        .then(modifier),
        contentAlignment = Alignment.Center) {
        content()
    }
}

fun listOfBrands() = listOf("All",
                            "Nike",
                            "Adidas",
                            "Puma")

