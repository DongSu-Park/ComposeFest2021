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
        /**
         *  setContentView 와 같은 역할을 담당.
         *  Compose의 경우 xml을 이용하지 않고 직접 코드로 UI를 작업
         * */
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


/**
 * Composable 어노테이션 (UI 설계)
 *  해당 함수에서는 UI 단에서 TextView 대신 사용하는 Text 함수를 사용
 *  이것을 Composable function 이라 부르고 해당 단에서 UI를 설계
 * */
@Composable
fun Greeting(name: String) {
    // 머테리얼 컬러로 Surface 처리
    // Modifier로 24dp 만큼 페딩 처리
    Surface(color = MaterialTheme.colors.primary) {
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}

/**
 * Preview 어노테이션 (프리뷰 빌드)
 *  Greeting 함수로 String 인스턴스를 넘겨주고 Text function 에 있는 Text로 프리뷰 구성
 *  프리뷰 탭에서 빌드를 해줘야 내용이 표시 됨.
 *  단 프리뷰 어노테이션을 붙인 함수 내용은 실제 앱 빌드에서는 영향을 미치지 않음
 *  예를 들어 프리뷰 어노테이션을 붙인 Greeting 함수에서는 'Compose Codelab!!' 내용이 들어가지만
 *  실제 빌드에서는 setContent 의 Surface 함수 내 Greeting 의 'Android' 내용이 들어감
 *  xml에서 작업한 내용을 볼 때 프리뷰 어노테이션이 붙인 함수에서 담당하게 됨.
 * */
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Week1_BasicsCodelabTheme {
//        Greeting("Android")
        Greeting(name = "Compose Codelab!!")
    }
}