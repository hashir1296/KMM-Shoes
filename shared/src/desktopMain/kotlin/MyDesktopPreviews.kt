import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composables.ItemCard

@Preview
@Composable
fun ItemCardPreview() {
    ItemCard(modifier = Modifier.height(100.dp), heading = "Nike Air Max", pictureUrl = "", onClick = {})
}

@Preview
@Composable
fun AppPreview() {
    App()
}