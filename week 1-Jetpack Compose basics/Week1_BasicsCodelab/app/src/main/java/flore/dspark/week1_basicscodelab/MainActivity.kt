package flore.dspark.week1_basicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
    // 추가 : animateDpAsState 로 패딩 확장 시 에니메이션 효과 추가 (스프링 효과)
    val extraPadding by animateDpAsState(
        if (expanded.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    // 추가 : Text 필드에 style 지정 할 수 있으며, 문자의 볼드 처리시 copy 메소드 사용.
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Text(text = "Hello, ")
                Text(text = name, style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
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
    // 추가 : rememberSaveable을 사용헤 액티비티 라이플사이클에 영향을 받아도 객체 값은 유지
    // ex. 다크모드 변경, 기기회전 등.
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true)}

    // 처음 값이 true 로 인한 OnBoardingScreen 화면 상태
    // 클릭하면 상태를 false 로 변경하고 MyAppButton() 화면으로 변경
    if (shouldShowOnBoarding){
        OnBoardingScreen(onContinueClicked = {shouldShowOnBoarding = false})
    } else {
        GreetingLazyColumn()
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

@Composable
private fun GreetingLazyColumn(names: List<String> = List(1000) {"$it"}){
    // 리사이클러 뷰와 비슷한 역할의 리스트 형식의 뷰를 만듬.
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
        items(items = names){
//            name -> GreetingButton(name = name)
            name -> GreetingCard(name = name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LazyColumnPreview(){
    Week1_BasicsCodelabTheme {
        GreetingLazyColumn()
    }
}


// 프리뷰시 다크모드와 일반모드의 UI 상태를 같이 보기 위해 설정
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreviewNormal(){
    Week1_BasicsCodelabTheme {
        GreetingLazyColumn()
    }
}

/** 최종 UI 화면 */
@Composable
private fun GreetingCard(name : String){
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name : String){
    var expanded by remember { mutableStateOf(false)}
    
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(12.dp)) {
                Text(text = "Hello, ")
                Text(text = name, style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold))
            
                if (expanded){
                    Text(text = ("Composem ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy. ").repeat(4),)
                }
            }
        IconButton(onClick = {expanded = !expanded}) {
            Icon(imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded){
                    stringResource(id = R.string.show_less)
                } else{
                    stringResource(id = R.string.show_more)
                }
            )
        }
    }
}