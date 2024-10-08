# 목차
1. [단원 1 정리](#단원-1-정리)
2. [단원 2 정리](#단원-2-정리)
3. [단원 3 정리](#단원-3-정리)
4. [단원 4 정리](#단원-4-정리)

## 단원 1 정리

## Jetpack Compose 란?
네이티브 Android UI를 빌드하기 위한 최신 도구 키트이다.

기존 안드로이드 앱 개발시, UI부분은 xml로 View를 그리고, 코드 상에서 setContentView 나 inflate 메소드를 이용하여 필요한 View 를 로드해야 했다.

Jetpack Compose 를 이용하면 이전과 달리 코드상에서 UI에 대한 모든 관리를 하게 된다.

초기 xml을 사용하는 Android 개발 방식을 접했다가 Flutter 를 잠시 공부해본 경험으로는, 이 Compose 방식이 Flutter 와 유사하다고 느꼈고, 굉장히 편리하고 강력한 기능이라는 느낌을 받았다.

Compose를 이용하여 프로젝트를 생성하는 방법은 New Project에서 Empty Compose Activity를 선택하여 프로젝트를 생성한다.

## 구성 요소

기본 제공하는 코드를 확인해보자.
~~~
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface(color = Color.Yellow) {
        Text(text = "Hi, My name is $name!", modifier = Modifier.padding(24.dp))
    }
}
~~~

- `onCreate()` 함수는 동일하다. 이 프로그램의 진입점이다.

- `setContent()` 함수는 Composable 함수를 통해 레이아웃을 정의하는데 사용된다. `@Composable` 주석으로 표시된 모든 함수는 `setContent()` 함수 또는 다른 `@Composable` 함수에서 호출이 가능하다.

- `@Composable` 어노테이션이 붙은 함수는 구성가능한 함수이다. 화면에 표시되는 내용을 생성한다. 이름은 대문자로 표기하는 파스칼 표기법을 사용하며, 아무것도 반환 할 수 없다. 또한 명사로 명명해야 한다.

- `Greeting` 함수에 Text를 Surface로 감쌈으로서, 배경색을 변경할 수 있다. 배경색이 인자로 들어간다.

- `Modifier`(수정자)는 Composable을 강화하거나 장식하는데 사용한다. 상하좌우 위치선정, 정렬, 패딩 등등 여러 역할이 존재한다. `modifier = Modifier.padding(24.dp)` 의 경우 Text 에 상하좌우 24dp 만큼의 패딩을 준다는 의미이다.

<br><br><br>
Compose에는 미리보기 기능이 존재한다. 다음 코드는 전체 앱을 빌드하지 않고 앱이 어떻게 표시되는지 확인 할 수 있는 기능이다.
```
@Preview(showBackground = true) // showBackground 매개변수가 true로 설정되면 앱 미리보기에 배경이 추가됨.
                                // 흰색 배경이 true로 설정됬을때임. false는 검은색 배경이 됨.
@Composable
fun DefaultPreview() {
    GreetingCardTheme {
        Greeting("Glion")
    }
}
```
- `@Preview` 어노테이션을 추가한 함수는 미리보기 함수가 된다.

- showBackground 매개변수가 true 로 설정되면 앱 미리보기에 배경이 추가된다.

- 미리보기 위해서는 Composable 함수를 호출하여 UI를 그려야 하므로 @Composable 어노테이션을 추가한 모습을 확인할 수 있으며, `onCreate()` 함수와 마찬가지로 `GreetingCardTheme` 내부에 `Greeting()`이라는 Composable함수를 호출하는 모습을 볼 수 있다.

## Jetpack Compose 에 대해 다시 짚고 넘어가자
Jetpack Compose 는 Android에서 UI를 빌드하기 위해 사용하는 최신 툴킷이다. 적은 양의 코드, 강력한 도구 및 직관적은 Kotlin의 기능으로 UI 개발을 간소화하고, 가속화 시킨다.
<br>
<br>
Compose를 사용하면 데이터를 받아서 UI요소를 내보내는 Composable 함수 집합을 정의하여 UI를 빌드 할 수 있다.
<br>
다음과 같은 특징이 있다.
1. UI 의 일부를 설명한다.
2. 아무것도 반환하지 않는다
3. 몇개의 입력을 받아서 화면에 표시되는 내용을 생성한다.
4. 여러 UI요소를 내보낼 수 있다.

## UI 계층 구조
공식 튜토리얼에는 다음과 같이 나와 있다.
```
UI 계층구조는 포함에 기반합니다다. 즉, 한 구성 요소에 하나 이상의 구성 요소를 포함할 수 있으며, 상위 요소 및 하위 요소라는 용어가 사용되는 경우도 있습니다. 
여기에서 컨텍스트는 상위 UI 요소가 하위 UI 요소를 포함하는 것이며 이 하위 UI 요소는 하위 UI 요소를 차례로 포함할 수 있습니다. 이 섹션에서는 상위 UI 요소 역할을 할 수 있는 Column, Row, Box 컴포저블에 관해 알아봅니다.
```
간단히 얘기하면 최상위 구성요소가 있고, 그 내부에 하위 요소, 하위 요소를 층층히 쌓아 내려가며 UI를 구성한다는 의미이다. 그중에 상위 요소 역할을 할 수 있는 것이 `Column`, `Row`, `Box` 가 있다.
<br><br>
`Column` Composable은 UI를 세로로 쌓는다.<br>
`Row` Composable은 UI를 가로로 쌓는다.<br>
`Box` Composable은 UI가 위로 쌓이는 모양이다.<br>

## 안드로이드 해상도
공식 튜토리얼에서는 이미지를 drawable 폴더에 넣어 사용하고자 할때 `Resource Manager` 를 이용하여 'Density'를 'no density'로 하고 추가하는 과정을 거쳤다. 왜 그런 것일까?<br>

우선 안드로이드 해상도에 대해 알아야 한다.<br>
* DP : 안드로이드에서 사용하는 독립적 단위 수치이다. 어떤 해상도에서도 같은 크기를 보여주는 것이 목적이다.
* DPI : 1인치에 들어있는 픽셀의 수이다. 안드로이드에서는 160을 기본으로 한다.
* px : 스크린의 실제 픽셀 단위를 사용한다. 실제 크기나 밀도와 상관이 없다.

px = dp * (dpi / 160)<br>
160 dpi인 해상도에서 1dp 는 1px라는 뜻이다. 160dpi를 mdpi라고 한다.

구글은 해상도 및 화면 크기가 다른 디바이스들을 범용으로 지원하기 위해 밀도(Density)와 함꼐 화면 크기로 분류하여 기준을 제시하고 있다. 해상도별로 다른 APK를 배포할 수 없기 때문에 정해진 기준에 따라 리소스를 제공해 줄 필요가 있다. <br>이러한 부분을 무시하게 되면 시스템이 자동으로 이미지를 확대하여 흐릿한 이미지가 나오거나, 메모리를 과하게 사용하는 큰 이미지를 얻게 되고, 메모리 부족 오류가 발생할 수 있는 원인이 된다.
<br>
<br>
원래 주제로 넘어와서 No Density로 지정한 것은 예제 프로젝트 이기 때문에 밀도를 정하지 않고 원본 그대로를 사용하겠다는 의미이다.<br>
실제 프로젝트에 적용할 때는 대표적인 밀도는 지원하게끔 설정해 주는 것이 필요 할 것 같다.

## HappyBirthday 실습 프로젝트에서 주목해야 할 코드
1. 이미지 크기를 조절하는 방법 - 이미지 배율 유형 조절
    ```
    Image(
        painter = image, 
        contentDescription = null, 
        contentScale = ContentScale.Crop
    )
    ```
   이미지의 인자로 contentScale가 보인다. 가지는 속성은 다음과 같다.
    * Crop : 이미지의 너비 및 높이가 배치될 대상의 치수보다 크거나 같도록 이미지를 균일하게 조정한다.
    * FillBounds : 배치될 대상 범위를 채우기 위해 수평 및 수직으로 불균일하게 크기를 조정한다.
    * FillHeight : 배치될 대상의 너비와 맞도록 종횡비를 유지하며 크기를 조절한다.
    * Fit : 이미지의 두 치수가 배치될 대상의 치수와 같거나 작도록 균일하게 조정한다. 이때 종횡비는 유지한다
    * Inside : 이미지 소스가 배치될 대상보다 큰 경우 그 경계 내에 있게 종횡비를 유지하며 크기를 조절한다.
    * None : 이미지 소스에 배율을 적용하지 않는다.
      <br>
      <br>
      추가로 `contentDescription` 은 시각장애인들을 위한 `Talkback` 옵션으로써, 사용하지 않을 경우 `null` 을 주면 된다.

2. 레이아웃에 `Modifier` 을 적용하여 `Arrangement`, `alignment` 속성을 통해 하위 요소 배치 하는 방법
    ```
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = message, fontSize = 36.sp, modifier = Modifier.padding(top = 16.dp))
        Text(text = from, fontSize = 24.sp, modifier = Modifier
        .padding(top = 16.dp, end = 16.dp)
        .align(alignment = Alignment.End))
    }

    ```
    * Row : horizontalArrangement / verticalAlignment 사용하여 하위 요소의 위치 지정
    * Column : verticalArrangement / horizontalAlignment 사용하여 하위 요소의 위치 지정
      <br><br>
      arrangement 속성은 레이아웃 크기가 그 하위 요소의 합보다 큰 경우 하위 요소를 정렬하는 데 사용한다(세로정렬)<br>
      alignment 속성은 레이아웃의 시작, 가운데, 또는 끝에 하위 요소를 정렬하는데 사용한다(가로정렬)
      <br><br>
      해당 코드에서는 `Column` 전체에 대해 수평으로 사용가능한 공간에서 수직기준 상단 정렬을 하였다.<br>
      또한, `Text` 에 top과 end에 16.dp만큼 패딩을 주었고, `Alignment.End`를 하여 text를 가로 끝에 붙이도록 정렬해준 모습이다.

## 연습 : Compose 기본 사항

(3번째 문제였던 Compose 사분면 문제를 다룬다)
<br><br>
우선, 해당 연습문제를 풀며 느낀건 Composable 함수에 인자를 전달하여 해당 인자를 출력하게 하는 방식을 권장하고 있으며, 레이아웃에 대해 잘 파악하여 어떤 Composable 함수를 작성하는지가 매우 중요하다는 사실을 깨달았다.
<br><br>

사분면 문제에서 제시된 조건은 화면을 4개의 사분면으로 나눠 각각의 부분 색상과 텍스트를 다르게 나타내야 하는 것이다.<br>
필요한 text들은 string.xml 에 지정하여 아이디값으로 불러오게끔 하였다.<br><br>

우선 한개의 사분면을 구성한다. 화면 전체가 특정 색으로 칠해지며, 텍스트는 양쪽 정렬로 되어있다.

```
@Composable
fun ComposeOneSection(
    title: String,
    body: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = body,
            textAlign = TextAlign.Justify
        )
    }
}
```
매개변수로 필요한 텍스트의 제목, 본문, 해당 사분면의 색상을 받는다.<br>
수정자 또한 매개변수로써 기본 매개변수로 `Modifier` 로 초기화 되어있는데, 모든 Composable 함수는 선택적 Modifier 매개변수를 허용해야 하기 때문이다.<br>
`Column` 내부를 보면 매개변수로 넘어온 색상으로 배경색을 지정해주고, 화면 전체를 차지하게끔 `fillMaxSize()`를 해준다.<br>
텍스트 제목과 본문은 화면 중앙에 위치해 있으므로 `horizontalAlignment = Alignment.CenterHorizontally` , `verticalArrangement = Arrangement.Center` 를 지정해주어 화면 중앙에 하위 요소들이 배치되게끔 지정해준다.<br>
본문 텍스트가 양쪽 정렬로 되어있었으므로 `TextAlign.Justifty` 해주었다.<br>
<br>
이제 한개의 사분면을 만들 수 있는 Composable 함수를 4번 호출하여 사분면 형태를 완성해주어야 한다.<br>
다음은 4사분면을 그리는 Composable 코드이다.
```
@Composable
fun ComposeFourSection(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            ComposeOneSection(
                stringResource(id = R.string.compose_text1),
                stringResource(id = R.string.compose_text2),
                Color.Green,
                modifier = Modifier.weight(1f)
            )
            ComposeOneSection(
                stringResource(id = R.string.compose_image1),
                stringResource(id = R.string.compose_image2),
                Color.Yellow,
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposeOneSection(
                stringResource(id = R.string.compose_row1),
                stringResource(id = R.string.compose_row2),
                Color.Cyan,
                modifier = Modifier.weight(1f)
            )
            ComposeOneSection(
                stringResource(id = R.string.compose_column1),
                stringResource(id = R.string.compose_column2),
                Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
```
Column 컴포저블은 화면 가로 전체를 차지하게끔 Modifier.fillMaxWidth() 지정해주고, 사분면의 모양을 하기 위해 한줄에 2개씩(가로로 2개 배치) 배치하기 위해 Row를 2번 써주었다.<br>
이때, Row의 인자로 Modifier.weight(1f) 가 들어간다.<br><br>
고정 높이를 지정하지 않고도 가중치로 각 자식요소가 차지하는 공간을 제어하는데 Weight를 사용한다.<br>
예를들어 컨테이너 안에 3개의 Composable이 있다고 하자. 2개의 weight는 3이고, 1개의 weight가 2일때 weight의 총합은 8이 된다.<br>
사용 가능한 컨테이너에서 height를 8로 나누고 해당 가중치에 따라 Composable이 차지하게 된다.
<br><br>
여기서 컨테이너란, jetpack compose에서 다른 구성요소를 보관하고 배열하는데 사용되는 레이아웃 구성요소이다. 컨테이너는 레이아웃 내에서 하위 구성요소의 위치 및 크기를 제어하는 역할을 한다.<br>
우리가 알고있는 Column, Row, Box 등이 컨테이너이다. Composable은 @Composable 함수를 말하는 것이 아닌 Text, Image와 같은 UI요소를 의미한다.

## 단원 2 정리
### 이 파트는 단원 2의 내용을 학습하고 정리하는 부분임.
* #### Android Compose 의 기본 Layout - Column, Row, Box, BoxWithConstraints
* #### Modifier
    - 역할 : Composable의 크기, 레이아웃, 동작, 모양 변경, 접근성 라벨과 같은 정보추가, 사용자 입력 처리, 클릭, 스크롤, 드래그, 확대, 축소 등 높은 수준의 상호작용을 추가
    - 호출 순서에 따라 결과가 달라 질 수 있다.
    - 기본적으로 Composable 컴포넌트는 하위 요소의 크기에 맞춰진다(view의 wrap_content)
    - Modifier.size : 크기를 임의로 정할 수 있음
    - Modifier.requiredSize : 제약 조건을 무시하고 크기를 정할 때 사용한다(상위 요소보다 크게 만들 수 있다)
    - offset : 원래 위치를 기준으로 레이아웃에서 x,y축을 설정할 수 있다. 양수, 음수 둘다 가능하다.
    - matchParentSize : Box 안에서만 사용 가능하다. 상위 요소의 크기에 영향을 안주는 선에서 가장 큰 사이즈로 만들어준다.
    - weight : 가중치를 정해 크기를 정한다(인자는 Float 값이 들어간다)
    - 수정자는 객체이므로, 미리 선언해서 저장해둔 뒤 Composable에 인자로 전달하는 방식으로 사용할 수 있고, 재사용성을 위해 이와 같은 방법이 권장된다.
    - wrapContentWidth/Height/Size : View 기반 레이아웃의 wrap_content 에 해당함
      <br><b>wrapContentWidth</b> == android:layout_width="wrap_content"
      <br><b>wrapContentHeight</b> == android:layout_height="wrap_content"
      <br><b>wrapContentSize</b> == android:layout_width="wrap_content"; android:layout_height="wrap_content"
* #### Alignment / Arrangement : Column, Row, Box 등의 인자로 verticalAlignment, verticalArrangement, horizontalAlignment, horizontalArrangement 값에 들어가 하위 요소를 정렬한다.
    [참고링크](https://nosorae.tistory.com/entry/AndroidCompose-%ED%97%B7%EA%B0%88%EB%A0%A4%EC%84%9C-%EB%94%B1-%EC%A0%95%EB%A6%AC%ED%95%98%EB%8A%94-Compose-%EC%A0%95%EB%A0%ACAlignment%EA%B3%BC-%EB%B0%B0%EC%B9%98Arrangement#google_vignette)
    - Alignment : 레이아웃 내에서의 수직 방향 정렬 방식, Modifier.align 을 추가하여 하위 요소의 동작을 따로따로 재정의 할 수 있음
        - Column 에서 Alignment : Start, CenterHorizontally, End
        - Row 에서 Alignment : Top, CenterVertically, Bottom
        - Box 에서 Alignment : TopStart, TopCenter, TopEnd, CenterStart, Center, CenterEnd, BottomStart, BottomCenter, BottomEnd
    - Arrangement : 레이아웃 내에서의 수평 방향 배치 방식
        - Row 에서 Arrangement : Equal Weight, Space Between, Space Around, Space Evenly, End(LTR), Center, Start(LTR)
        - Column 에서 Arrangement : Equal Weight, Space Between, Space Around, Space Evenly, Top, Center, Botton
        - spaceBy() : 하위 Composable 간의 간격 설정 가능.
* #### Composable 은 UI 구성요소를 위에서부터 작성된대로 순차적으로 배치한다. 만약 Text(...); Button(...); TextField(...) 라면 작성된 순서대로 배치된다.
* #### @Composable 함수의 표기법은 파스칼케이스(대문자 시작, 단어 시작마다 대문자), 명사구로 한다.
* #### 선언형 UI - UI 의 모양을 코드로 나타낸 것으로서 개발자가 UI 동작에 대해 선언함으로서 정확하게 통제할 수 있다.
    - Compose는 initial composition 을 통해 생성되고 recomposition 을 통해 업데이트 된다.
    - 업데이트를 위해 추적할 상태를 알아야 하고, 이는 State 또는 MutableState를 사용하여 관찰/추적 가능한 상태로 설정이 가능하다.\
        따로 저장되지 않은 값은 리컴포지션시 초기화될수 있기 때문에, 상태를 나타내는 변수라던지, 리컴포지션시 값이 유지되야 하는 변수는 remember 에 위임해주어야 한다.\
    - 상태를 나타내는 변수란, UI가 변경될 수 있는 조건을 가진 변수를 의미한다. \
      각각 다른 조건에 따라 UI가 변하기 때문에 구현하고자 하는 프로젝트에 맞는 조건을 파악하여 remember 와 State/MutableState를 적절하게 사용해주어야 한다.
* #### Android Compose 로 앱을 작성할때, 일반적으로 함께 사용되는 라이브러리(반드시 알아가야 할 라이브러리들)
  * Navigation, ViewModel, LiveData/RX/Flow, Paging, Hilt, Image Loading, Theme Adapter
* #### 모든 Composable 함수에 기본 Modifier 매개변수를 제공하여 재사용성을 높여주는것이 좋다.
    ```
    @Composable
    fun ImageLayout(arg1: Type, modifier: Modifier = Modifier){ 
    // ImageLayout을 호출할때 사전에 정의해둔 modifier를 전달할수도 있지만, 아예 전달하지 않으면 기본 Modifier가 사용된다.
        Column(modifier = Modifier.padding) // 기본 매개변수를 이용함
        Row(modifier = modifier) // ImageLayout을 호출했을때 전달된 Modifier를 이용한다.
    }
    ```
* #### 상태 호이스팅
  * 컴포저블을 stateless 하게 만드는 것(stateless 컴포저블은 자체 상태를 저장하지 않는 컴포저블임)
  * 상태가 없는 컴포저블(새 상태를 보유하거나 정의, 수정하지 않음)
  * 호이스팅 - 컴포저블의 상태를 끌어올려 stateless로 만드는것.
  * 구성요소를 stateless로 만들기 위해 상태를 호출자로 이동시키는 것을 말함
    ```
    // 상태 호이스팅 전 - TextField 예시
    @Composable
    fun TextFieldLayout(){
        Column{
            EditTextField(value = "Test")
            Text(
                text = inputValue, // 텍스트에 텍스트필드에서 입력한 값을 띄워주고 싶지만, EditTextField 내에 선언한 변수이므로 접근 불가능. 
                // EditTextField 의 상태 호이스팅 필요
                modifier = Modifier
            )
        }
    }
    
    @Composable
    fun EditTextField(
        modifier: Modifier = Modifier
    ){
        var inputValue by remember { mutableStateOf("") }
        TextField(
            value = inputValue,
            onValueChange = { inputValue = it } // 텍스트 상자에 텍스트를 입력할 때 트리거되는 람다 콜백 함수, 여기서는 입력된 값을 inputValue 에 저장한다.
        )
    }
    
    // 상태 호이스팅 했을떄(상태를 끌어올렸을때)
    @Composable
    fun TextFieldLayout(){
        var inputValue by remember { mutableStateOf("") } // EditTextField에서 선언되 있던 상태를 호출자영역으로 올림
        Column{
            EditTextField(
                value = inputValue,
                onValueChange = { inputValue = it } // onValueChange 동작인 람다함수를 EditTextField 인자로 전달해줌
            )
            Text(
                text = inputValue, // 기존에 접근할 수 없었던 inputValue가 TextFieldLayout 영역으로 올라왔기 때문에 접근 가능.
                modifier = Modifier
            )
        }
    }
    
    @Composable
    fun EditTextField(
        value: String, // TextField 의 value를 나타낼 값을 매개변수로 받음
        onValueChange: (String) -> Unit, // onValueChange 에서 실행할 람다함수를 매개변수로 받음. String을 입력받고 아무것도 리턴하지 않는 람다함수를 받을 것임.
        modifier: Modifier = Modifier
    ){
        TextField(
            value = value,
            onValueChange = onValueChange // EditTextField 는 매개변수로 받은 onValueChange를 실행하는, 상태에 따라 바뀌지않는 stateless가 됨
            // onValueChange 에서 수행할 동작은 호출자에서 전달해주기 때문에, 여기서는 전달받은 동작만 수행하게 된다.
        )
    }
    ```
  * TextField 에서 KeyBoardOptions 클래스를 사용하여 키보드 유형을 설정할 수 있다. 또한 작업버튼(검색, 보내기, 다음으로 이동 등)을 설정할 수 있다.
* #### 요약
  * 앱의 State는 시간이 지남에 따라 변할 수 있는 값이다.
  * 컴포지션은 Compose가 @Composable을 실행할 때 빌드한 UI에 관한 설명이다. Compose 앱은 구성가능한 함수(@Composable로 선언한 함수)를 호출하여 데이터를 UI 로 변환한다.
  * 초기 컴포지션은 Compose 가 @Composable 함수를 처음 실행할 때 UI 가 생성된 것이다.
  * 리컴포지션은 동일한 컴포저블을 다시 실행하여 데이터가 변경될 때 트리를 업데이트하는 프로세스이다.
  * 상태 호이스팅이란 구성요소를 stateless 하게 만들기 위해 상태를 호출자로 이동하는 패턴을 말한다.
* #### 자동테스트
  * ##### [실습한 프로젝트의 테스트코드 바로가기](https://github.com/Gangglion/AndroidStudy/tree/main/ComposeBasic_beginner/Calculator/app/src)
  * 수동테스트 - 사람이 직접 테스트함
  * 자동테스트 - 소프트웨어에서 실행됨
    * 로컬테스트 : 함수, 클래스, 속성을 테스트 할 수 있다. 코드를 테스트하여 제대로 작동하는지 확인한다.
    * 계측테스트(UI 테스트) : 안드로이드 개발에서 UI 테스트에 속하며, 앱이나 앱의 일부를 실행하고, 사용자 상호작용을 시뮬레이션 한다.
      실제 기기나 애뮬레이터에서 동작하며 테스트 APK 가 설치된다. API 버전별로 테스트도 가능하다.
    * 테스트 디렉토리 구조는 Main 의 디렉토리 구조와 동일해야 한다.
  * 로컬테스트 에 대해 좀더 자세히
    * 로컬테스트에서는 테스트하려는 함수를 직접 호출하고, 인자와 리턴값을 비교하여 예상값과 일치하는지 확인한다.
      이를 위해 실제 Main의 함수의 접근지정자를 변경해야 할 수도 있다. @VisibleForTesting 어노테이션을 붙여 테스트목적으로 공개됨을 알릴 수 있다.
    * 로컬 테스트 작성은 메소드 형태로 작성하며 @Test 를 달아준다. 컴파일러가 테스트 메소드임을 인지할 수 있다.
    * 일반 앱 메소드와 동일한 로직을 사용하지 않는다.
    * 테스트는 특정 조건이 충족되었는지 확인하는데 사용되는 assertion 으로 끝난다. 이는 테스트메소드 앞에 assert가 있는 메소드 호출의 형태이다.\
      ex) assertTrue()
  * 계측테스트(UI 테스트) 에 대해 좀더 자세히
    * 앱과 UI 의 실제 인스턴스를 테스트 하기 때문에 MainActivity의 onCreate메소드에서 콘텐츠를 설정하는 방식과 유사해야 한다.
    * UI와 상호작용 하는 명령을 작성하여 UI를 통해 테스트 되도록 한다.
      ```asgl
      @get:Rule
      val composeTestRule = createComposeRule() // Compose로 빌드된 앱의 모든 계측테스트를 작성하기 전에 무조건 해야함. 이걸 사용해서 UI구성요소에 액세스 할 수 있다
      
      @Test // 컴파일러는 로컬테스트, 계측테스트에 동일한 @Test를 달아주어도, 어떤 테스트인지 인식한다.
      fun exampleTest(){
        cmposeTestRule.setContent {
            ExampleTheme{ // 이 부분은 Main과 동일하게 작성해준다.
                Surface(modifier = Modifier.fillMaxSize()){
                    ExampleLayout()
                }
            }
        }
      }
      ```
      
## 단원 3 정리
### 단원 3을 학습한 뒤 내용 정리하는 부분
* #### ContentScale 
  * [참고링크](https://sonseungha.tistory.com/654)
  * 경계가 Painter 의 고유 크기와 다른 경우 사용할 가로, 세로 스케일링을 결정하는데 사용되는 선택적 스케일 매개변수(추가하려는 이미지가 Layout 과 맞지 않는 경우 Scale을 맞출 수 있도록 사용하는 매개변수)
  * ##### 종류 
    * Crop - 이미지의 너비나 높이가 대상의 해당 치수 이하가 되도록 소스를 균일하게 조정(소스의 종횡비 유지)
    * Fit - 이미지 소스의 두 치수(너비 및 높이) 가 대상의 해당 치수 이하가 되도록 소스를 균일하게 조정(소스의 종횡비 유지)
    * FillBounds - 대상 범위를 채우기 위해 수평 및 수직으로 뷸균형하게 크기를 조절
    * FillHeight - 범위가 대상 높이와 일치하도록 너비, 높이 비율을 유지하면서 소스의 크기 조정
    * FillWidth - 범위가 대상 너비와 이치하도록 너비, 높이 비율을 유지하면서 소스의 크기 조정
    * Inside - 소스가 대상보다 큰 경우 종횡비가 대상 경계 내에 있도록 소스의 크기를 조절
    * None - 소스에 배율을 적용하지 않는다.
* #### Lazy Composable
  * [참고링크]("https://velog.io/@wonseok/Jetpack-Compose-Lazy-Layouts")
  * xml을 사용하는 View 방식의 ListView, RecyclerView 에 상응하는 개념. 보일러플레이트 코드가 줄어든다.
  * 한 항목에 대한 아이템을 @Composable 로 만들어두고, 데이터 리스트 항목만큼의 아이템을 생성하여 리스트형태로 나타난다.
  * LazyColumn, LazyRow, LazyVerticalGrid 가 존재한다.
  * 만일, 네트워크 통신을 이용하여 받은 데이터에 대해 리스트 형태로 뿌려준다고 하면
    1. Retrofit 통신 성공, 미리 정의한 Model타입의 List를 만든다.
    2. 하나의 항목에 대한 UI 를 작성한다(TempItem 이라 하자)
    3. LazyColumn 을 사용한다는 가정하에 LazyColumn 람다에 items(List){ item -> TempItem(item) } 를 넣어, 리스트의 요소 하나당 UI를 반복해서 그린다.
  * 코드는 아래와 같다.
  * ```asgl
    LazyColumn(){
        items(List<T>) { item ->
            TempItem(item)
        }
    }
    ```
* #### Scaffold : 다양한 구성요소와 화면요소의 슬롯을 제공하는 레이아웃이다.
  * TopAppBar : center, small, medium, large 4가지의 유형이 존재한다.
  * contentWidowInsets 매개변수 : Scaffold 콘텐츠의 인셋을 지정하는데 도움이 된다. WindowInsets는 화면에서 앱이 시스템 UI와 교차할 수 있는 부분으로 PaddingValues 매개변수를 통해 전달된다.(Scaffold 람다의 매개변수이다.)
    ```asgl
    fun WoofApp(dogList: List<Dog>){
    Scaffold(
        topBar = {
            WoofTopAppBar()
        }
    ) {paddingValues ->
          LazyColumn(contentPadding = paddingValues){
              items(dogList){dog ->
                  DogItem(
                      dog = dog,
                      modifier = Modifier.padding(8.dp)
                  )
              }
          }
    }
    ```
  * @OptIn(ExperimentalMaterial3Api::class)
      * CenterAlignedtopAppBar나 Scaffold 를 추가할 때 위의 어노테이션을 붙인다. 이유는 일부 Material3 API가 실험용으로 간주되기 때문이다.
      * import androidx.compose.material3.ExperimentalMaterial3Api 해주고 @OptIn(ExperimentalMaterial3Api::class) 를 붙여준다.
* #### Style 관련
  * Material Design Builder : 주요색상을 정해주면, 그에 맞춰서 컬러 팔레트를 Material Theme 에 따라 색상을 자동으로 만들어주고, 파일도 뽑아준다.
  * theme 의 colorScheme
    * primary : UI 주요 구성요소에 사용됨
    * secondary : UI 에서 눈에 덜 띄는 구성요소에 사용됨
    * tetiary : 기본 색상과 보조 색상의 균형을 맞추고, 입력란과 같은 특정 요소로 관심을 유됴하는데 사용할 수 있음.
    * on : 팔레트의 색상 위에 나타나며, 텍스트, 아이콘, 획 에 적용된다. Surface 색상 위에 나타나는 onSurface 색상과 primary 색상 위에 올라오는 onPrimary가 이 예시이다.
  * Material 구성요소는 색상 슬롯에 자동 매핑된다. 상태 표시줄이 코드 변경 없이도 Primary 색상으로 적용되는데, 이는 자동으로 매핑되었기 때문이다. 명시적으로 색상을 할당할 필요가 없으며, 또한 명시적으로 할당하여 자동매핑에 대한 재정의도 가능하다.
  * 동적색상(theme의 dynamicColor 속성) : 사용자의 배경화면을 기반으로 앱 내의 테마를 생성하는 Material3 의 새로운 기능. Android12 이후 버전 기기만 적용되며, false로 세팅하면 사용자가 정의한 색상으로 적용된다.
  * Text Style(typography)
    * Display : 화면에서 가장 큰 텍스트에 적용됨. 짧고 중요한 텍스트 또는 숫자에 사용되며, 대형화면에서 가장 잘 작동함.
    * Headline : 작은 화면에 표시되는 강조 문구에 적합함. 텍스트의 주요 구저이나 콘텐츠의 중요한 부분을 표시함.
    * title : 헤드라인 스타일보다 작다. 상대적으로 짧게 유지되는 중간 강조 텍스트를 사용한다.
    * body : 앱의 긴 텍스트 문구에 사용된다.
    * label : 구성요소 내부의 텍스트 또는 콘텐츠 본문의 매우 작은 텍스트(캡션...)에 사용됨.
    * 각각 Large, Medium, small 가 존재한다.
* #### 요약
  * Material Themeing 을 사용하면 색상, 서체, 도형, 사용자 지정에 관한 안내에 따라 Material Design 을 사용할 수 있다.
  * Theme.kt 파일의 경우 [앱이름 + Theme()] 라는 Theme 컴포저블이 자동으로 생성되고, 이를 통해 테마가 정의된다. 여기서 MaterialTheme 객체가 앱의 color, typography, shapes, content를 설정한다.
  * Color.kt 는 앱에서 사용할 색상을 나열한다. 그 뒤 Theme.kt 에서 LightColorPalette 및 DarkColorPalette 의 색상을 특정 슬롯에 할당한다. 모든 슬롯을 할당할 필요는 없다.
  * 강제로 어두운 테마를 적용할 수 있다. 시스템이 자동으로 구현하지만 개발자가 직접 구현하는 것이 사용자 환경성에 더 좋다.
  * Shape.kt 에는 앱의 도형 상태를 정의한다. 도형에는 소, 중, 대 3가지 크기가 있고, 도형의 곡률을 지정할 수 있다. 기존 view 방식에서는 곡률이 있는 버튼을 만든다거나 할때 drawable을 따로 만들어주어야 했지만, 그럴 필요성이 사라졌다.
  * Type.kt 에는 글꼴을 초기화하고 Material Design 서체 스케일의 fontFamily, fontWeight, fontSize 를 할당한다.
  * Material Design 의 서체 스케일에는 앱과 앱 콘텐츠의 요구사항을 지원하는 다양한 스타일이 있으며 15가지 스타일로 구성된다.
* #### Animation [참고링크]("https://developer.android.com/jetpack/compose/animation?hl=ko")
  * 콘텐츠의 높낮이가 특정 조건에 따라 달라질 경우 애니메이션 사용이 가능하다 - animateContentSize
  * animateContentSize 의 인자로 animationSpec 을 주어 애니메이션 맞춤 설정이 가능하다.
    ```agsl
        Column(
            modifier = Modifier
                .animateContentSize( // MEMO : animationSpec을 사용해서 애니메이션 맞춤설정 가능. 그냥 사용하면 반동없이 애니메이션 효과로 생겼다 사라지기만 한다.
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy, // MEMO : 스프링의 반동 정도
                        stiffness = Spring.StiffnessMedium // MEMO : 스프링의 강성 정도
                    )
                )
        )
    ```
  * animate*AsState : Compose에서 단일 값에 대해 애니메이션 처리하는 가장 간단한 애니메이션 API 이다.
    * Float, Color, DP, Size, Offset, Int의 함수를 제공한다.
    * animateValueAsState를 사용하여 다른 데이터 유형도 추가 가능하다.
  * AnimatedVisibility 는 내부 콘텐츠에 대해 나타남과 사라짐을 애니메이션으로 처리할 수 있게 된다. 즉 항목이 나타나는것에 대한 애니메이션 처리가 가능해진다.
* #### 추가
  * Modifier.weight 를 잘 사용하면 영역 크기를 지정해 줄 수 있어 유용하다.
  * 예를들어, Row에 3개의 컴포저블 Text, Text, Image가 존재할때 첫번째 Text에 modifier.weight(1f) 를 지정해주게 되면, 나머지 2개 Text과 Image가 차지하는 영역 외 나머지 영역을 첫번째 Text가 차지하게 된다.

## 단원 4 정리
### 단원 4를 학습한 뒤 내용 정리하는 부분