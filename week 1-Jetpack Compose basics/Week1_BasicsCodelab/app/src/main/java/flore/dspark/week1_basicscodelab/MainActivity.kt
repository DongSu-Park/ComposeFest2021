package flore.dspark.week1_basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import flore.dspark.week1_basicscodelab.ui.theme.Week1_BasicsCodelabTheme

/**
 * Week1 - Jetpack Compose basics
 *  메소드 네이밍 가이드라인
 *      - Compose 함수 이름 생성시 첫글자는 대문자로 진행
 * */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 앱 테마의 이름은 프로젝트 네이밍에 따라 다르게 표시 될 수 있음.
            Week1_BasicsCodelabTheme {
//                MyApp()
                MyAppOnBoarding()
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

@Composable
private fun MyAppRaw(names : List<String> = listOf("World", "Compose")){
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names){
            GreetingRaw(name = name)
        }
    }
}

@Composable
private fun GreetingRaw(name : String){
    // columns and rows
    // Surface의 영역에 Modifier로 패딩처리
    // Column의 영역에 fillMaxWith()로 수평부분을 꽉차게 처리
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)) {
            Text(text = "Hello,")
            Text(text = name)
        }
    }
}

@Composable
private fun MyAppButton(names : List<String> = listOf("World", "Compose")){
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names){
            GreetingButton(name = name)
        }
    }
}

@Composable
private fun GreetingButton(name : String){
    // Surface -> Raw -> Column - OutlinedButton 순의 부모, 자식 레이아웃 구성
    // OutlinedButton 을 활용한 버튼 추가

    // State 변경이 필요한 경우 Remember 컴포저블로 객체 저장
    // 수정이 필요한 객체인 경우 mutableStateOf()로 접근하고 해당 value 값에 따라 초기값 구성
    // 객체의 값 변경이 필요한경우 객체이름.value 로 설정
    val expanded = remember {
        mutableStateOf(false)
    }

    // ture = 48.dp, false = 0.dp
    // 버튼을 누를 경우 아래로 확장
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                Text(
                    if (expanded.value) "Show less" else "Show more"
                )
            }
        }
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

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreviewRaw(){
    // 320dp의 넓이로 프리뷰
    Week1_BasicsCodelabTheme {
        MyAppRaw()
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreviewButton(){
    Week1_BasicsCodelabTheme {
        MyAppButton()
    }
}

@Composable
fun MyAppOnBoarding(){
    // remember, mutableStateOf 를 활용한 상태 저장. 초기값은 ture
    var shouldShowOnBoarding by remember { mutableStateOf(true)}

    // 처음 값이 true 로 인한 OnBoardingScreen 화면 상태
    // 클릭하면 상태를 false 로 변경하고 MyAppButton() 화면으로 변경
    if (shouldShowOnBoarding){
        OnBoardingScreen(onContinueClicked = {shouldShowOnBoarding = false})
    } else {
        MyAppButton()
    }
}

@Composable
fun OnBoardingScreen(onContinueClicked:() -> Unit){
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(24.dp),
                onClick = onContinueClicked) {
                    Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview(){
    Week1_BasicsCodelabTheme {
        OnBoardingScreen(onContinueClicked = {}) // 클릭하면 아무것도 작동 안함.
    }
}