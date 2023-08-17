package composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun ShoesListingScreen() {
    val colorStopsBg = listOf(
            Color(
                    32,
                    32,
                    43
            ),
            Color(
                    53,
                    54,
                    61,
            )
    )

    val colorStops = listOf(
            Color(
                    45,
                    43,
                    57
            ),
            Color(
                    66,
                    66,
                    81
            )
    )

    Box(
            modifier = Modifier.fillMaxSize()
                    .background(brush = Brush.verticalGradient(colorStopsBg))
                    .padding(20.dp)
    ) {
        Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                        text = "Explore\nCollections",
                        color = Color.White,
                        fontSize = 40.sp,
                        lineHeight = 55.sp,
                        fontWeight = FontWeight.Medium
                )

                RoundBox(
                        modifier = Modifier.wrapContentHeight()
                                .padding(5.dp),
                        colors = colorStopsBg,shape =  RoundedCornerShape(80)
                ) {
                    RoundBox(
                            colors = colorStops,
                            modifier = Modifier.height(100.dp)
                                    .padding(
                                            vertical = 25.dp,
                                            horizontal = 20.dp
                                    ), shape =  RoundedCornerShape(80)
                    ) {
                        Image(
                                painter = painterResource("search.xml"),
                                contentDescription = null
                        )
                    }
                }
            }

            MiddleContent(
                    Modifier.weight(.5f)
                            .padding(bottom = 30.dp),
                    colorStops
            )

            BottomBar(modifier = Modifier)
        }
    }
}

@Composable
fun RoundBox(
        colors: List<Color>,
        modifier: Modifier = Modifier,
        shape: Shape = RoundedCornerShape(40),
        content: @Composable () -> Unit,
) {
    Box(
            modifier = Modifier.wrapContentWidth()
                    .clip(shape)
                    .background(brush = Brush.verticalGradient(colors))
                    .then(modifier),
            contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun ItemCard(
        modifier: Modifier = Modifier,
        isCurrentItem: Boolean,
        shadowColors: List<Color>,
        content: @Composable () -> Unit,

        ) {
    Card(
            modifier = modifier.shadow(
                    elevation = 10.dp,
                    spotColor = Color.Red,
                    shape = RoundedCornerShape(20.dp),
            ),
            shape = RoundedCornerShape(20.dp),
            content = content,
            elevation = 5.dp
    )
}


fun listOfBrands() = listOf(
        "All",
        "Nike",
        "Adidas",
        "Puma"
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
    ) {

        Box(modifier = Modifier.size(60.dp).drawBehind {
            this.drawCircle(
                    Brush.radialGradient(listOf(Color(
                            107,
                            217,
                            244
                    ), Color.Transparent)),
                    alpha = 0.15f,
            )
        }) {
            Image(
                    painter = painterResource("running-shoe.xml"),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp).align(Alignment.Center),
                    colorFilter = ColorFilter.tint(
                            Color(
                                    107,
                                    217,
                                    244
                            )
                    )
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MiddleContent(
        modifier: Modifier = Modifier,
        colors: List<Color>
) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(initialPage = 1)
    val colorsForPagerItem = listOf(
            Color(
                    80,
                    94,
                    182
            ),
            Color(
                    107,
                    195,
                    226
            ),
            Color(
                    235,
                    212,
                    209
            )
    )
    Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
                modifier = Modifier.horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOfBrands().forEach { brandName ->
                RoundBox(
                        modifier = Modifier.wrapContentHeight()
                                .padding(
                                        horizontal = 20.dp,
                                        vertical = 18.dp
                                ),
                        colors = colors,
                        shape = RoundedCornerShape(70)
                ) {
                    Text(
                            text = brandName,
                            color = Color.White,
                            maxLines = 1
                    )
                }
            }
        }

        HorizontalPager(
                pageCount = listOfBrands().size,
                state = pagerState,
                pageSpacing = -20.dp,
                contentPadding = PaddingValues(horizontal = 30.dp)
        ) { page ->
            val pageOffset =
                    ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
            val pageSize by animateFloatAsState(
                    targetValue = if (pageOffset != 0.0f) 0.75f else 1f,
                    animationSpec = tween(300)
            )
            Box(modifier = Modifier.background(Color.Transparent)
                    .fillMaxSize()
                    .graphicsLayer {
                        scaleX = pageSize
                        scaleY = pageSize

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(
                                        0f,
                                        1f
                                )
                        )
                    }) {
                Box(
                        modifier = Modifier.background(
                                Brush.verticalGradient(colorsForPagerItem),
                                RoundedCornerShape(40.dp)
                        )
                                .fillMaxSize()
                ) {

                }
            }
        }
    }
}




