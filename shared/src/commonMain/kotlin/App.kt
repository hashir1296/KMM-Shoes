import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import composables.ShoesListingScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.BirdClass

@Composable
fun BirdsAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = MaterialTheme.colors.copy(primary = Color.Black),
                  shapes = MaterialTheme.shapes.copy(AbsoluteCutCornerShape(0.dp),
                                                     AbsoluteCutCornerShape(0.dp),
                                                     AbsoluteCutCornerShape(0.dp))) {
        content()
    }
}

@Composable
fun App() {
    BirdsAppTheme {
        val birdsViewModel: BirdsViewModel = getViewModel<BirdsViewModel>(Unit,
                                                                          viewModelFactory { BirdsViewModel() })
        //BirdsPage(birdsViewModel)
        ShoesListingScreen()
    }
}

@Composable
fun BirdsPage(birdsViewModel: BirdsViewModel) {
    val uiState by birdsViewModel.birdUIState.collectAsState()
    Column(modifier = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            uiState.categories.forEach {
                Button(modifier = Modifier.aspectRatio(1.0f)
                    .fillMaxSize()
                    .weight(1.0f),
                       onClick = { birdsViewModel.selectCategory(it ?: "") }) {
                    Text(it ?: "")
                }
            }
        }
        AnimatedVisibility(uiState.selectedImages.isNotEmpty()) {
            LazyVerticalGrid(columns = if (isDesktop) GridCells.Adaptive(50.dp)
            else GridCells.Fixed(2),
                             horizontalArrangement = Arrangement.spacedBy(5.dp),
                             verticalArrangement = Arrangement.spacedBy(5.dp),
                             modifier = Modifier.fillMaxSize()
                                 .padding(5.dp),
                             content = {
                                 items(uiState.selectedImages) {
                                     BirdImageCell(it)
                                 }
                             })
        }
    }
}

@Composable
fun BirdImageCell(it: BirdClass) {
    KamelImage(asyncPainterResource("https://sebi.io/demo-image-api/${it.path}"),
               contentDescription = "${it.category} by${it.author}",
               contentScale = ContentScale.Crop,
               modifier = Modifier.fillMaxWidth()
                   .aspectRatio(1f))
}

expect fun getPlatformName(): String

val isDesktop = getPlatformName().contains("desktop",
                                           true)

/*
fun getMyString(): StringDesc {
    return StringDesc.Resource(MR.strings.explore_collections)
}*/
