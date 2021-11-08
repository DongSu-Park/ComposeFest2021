package flore.dspark.week1_basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import flore.dspark.week1_basicscodelab.ui.theme.Week1_BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week1_BasicsCodelabTheme {
                // 앱 테마의 이름은 프로젝트 네이밍에 따라 다르게 표시 될 수 있음.
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp(){
    // Reusing composables
    Surface(color = MaterialTheme.colors.background) {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String) {
    // 머테리얼 컬러로 Surface 처리
    // Modifier로 24dp 만큼 페딩 처리
    Surface(color = MaterialTheme.colors.primary) {
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Week1_BasicsCodelabTheme {
//        Greeting("Android")
        Greeting(name = "Compose Codelab!!")
    }
}