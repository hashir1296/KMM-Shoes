import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import composables.ItemCard

@Preview
@Composable
fun MainViewPreview() {
    MainView()
}


@Preview
@Composable
fun ItemCardPreview() {
    ItemCard(modifier = Modifier.height(100.dp), heading = "Nike Air Max", pictureUrl = "", onClick = {})
}