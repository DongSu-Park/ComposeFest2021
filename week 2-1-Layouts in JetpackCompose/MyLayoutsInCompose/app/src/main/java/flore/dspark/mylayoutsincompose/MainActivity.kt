package flore.dspark.mylayoutsincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import flore.dspark.mylayoutsincompose.ui.theme.MyLayoutsInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLayoutsInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PhotographerCard()
                }
            }
        }
    }
}


@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(4.dp)) // 라운드 처리
        .background(MaterialTheme.colors.surface)
        .clickable(onClick = { /** onClick function */ }) // 클릭 시 이벤트 처리
        .padding(16.dp)) {
        Surface(modifier = Modifier.size(50.dp), // 원형 Surface
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
        {
            // todo Image goes here
        }
        Column (
            modifier = Modifier // 글자 부분은 8dp 페딩에 가운데 정렬처리
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Park Dong Su", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PhotographerCardPreview(){
    MyLayoutsInComposeTheme {
        PhotographerCard()
    }
}