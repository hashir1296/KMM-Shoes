package composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemCard(modifier: Modifier = Modifier,
             heading: String,
             pictureUrl: String,
             onClick: () -> Unit) {
    Card(modifier = modifier.then(Modifier.wrapContentSize()),
         shape = RoundedCornerShape(10.dp),
         content = {
             Column {
                 Text(
                     heading,
                     fontSize = 20.sp,
                     color = Color.White,
                     maxLines = 2,
                 )
             }
         })
}
