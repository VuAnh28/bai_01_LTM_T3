package uth.edu.bai01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uth.edu.bai01.ui.theme.Bai01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bai01Theme {
                AppNavigation()
            }
        }
    }
}
@Composable
fun AppNavigation(){
    val navController = rememberNavController()//Khoi tao navController
    NavHost(navController = navController,
        startDestination = "welcome",
    ) {
        composable("welcome"){ WelcomeScreen(navController)}
        composable("components_list"){ComponentsListScreen(navController) }
        composable("text_detail"){TextDetailScreen(navController)}
    }
}

@Composable
fun WelcomeScreen(navController: NavController){
   Column(modifier = Modifier.fillMaxSize()
       .padding(24.dp),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally)
   {
       Image(painter = painterResource(id =R.drawable.image_1)
           , contentDescription = "Image", contentScale = ContentScale.Fit,
           modifier = Modifier.size(216.dp,233.dp))
       Spacer(modifier = Modifier.padding(24.dp))
       Column(horizontalAlignment = Alignment.CenterHorizontally) {
           Text("Jetpack Compose", fontSize = 30.sp,
               fontWeight = FontWeight.ExtraBold, color = Color.Black, textAlign = TextAlign.Center)


           Spacer(modifier = Modifier.padding(16.dp))
           Text("Jetpack Compose is a modern UI toolkit for building " +
                   "native Android applications using a declarative " +
                   "programming approach.", textAlign = TextAlign.Center, fontSize = 16.sp)
       }
       Spacer(modifier = Modifier.padding(24.dp))
       Button(onClick = {navController.navigate("components_list")},
           colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Magenta),
           modifier = Modifier.fillMaxWidth()) {
           Text("I'm ready" , fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
       }

   }
}
@Composable
fun ComponentsListScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround){
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "UI Components List", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))

            Column(horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth())
            {
                Text("Menu", fontSize = 20.sp, fontWeight = FontWeight.Bold,)
                Spacer(modifier = Modifier.height(24.dp))
              Column(horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.Center) {
                  Image(painterResource(id = R.drawable.hutieu),
                      contentDescription = null, modifier = Modifier.size(100.dp,100.dp))
                  Spacer(modifier = Modifier.height(30.dp))
                  Text(text = stringResource(id =R.string.food), fontSize = 20.sp)
              }
            }
        Spacer(modifier = Modifier.padding(24.dp))
        Text("Input", fontSize = 20.sp, modifier = Modifier.align(Alignment.Start),
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        var name by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isShowPassword by remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Email") },
                placeholder = { Text("Enter email") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null)},
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = password,
                onValueChange = {password = it},
                label = { Text("Password")},
                placeholder = { Text("Enter Password")},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null)},

                trailingIcon = {
                    IconButton(onClick = {isShowPassword =!isShowPassword}
                    ) {
                        Icon(
                            if(isShowPassword) Icons.Filled.AccountCircle else Icons.Filled.Person
                            , contentDescription = null
                        )

                    }
                },
                visualTransformation = if(isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
                )
        }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Layout", fontSize = 20.sp,
                modifier = Modifier.align(alignment = Alignment.Start),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            val scrollState = rememberScrollState()
            val scrollState_h = rememberScrollState()
            Text("column", fontSize = 16.sp,
                modifier = Modifier.align(alignment = Alignment.Start))
            Column(horizontalAlignment = Alignment.CenterHorizontally
            , verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.size(150.dp,75.dp).verticalScroll(scrollState)
                ){
                Button (onClick = {}){Text("Viết gì vô nhỉ?")}
                Button(onClick = {}){Text("Viết gì vô nhỉ?")}
                Button(onClick = {}){Text("Viết gì vô nhỉ?")}
                Button(onClick = {}){Text("Viết gì vô nhỉ?")}
                Button(onClick = {}){Text("Viết gì vô nhỉ?")}
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.size(150.dp).horizontalScroll(scrollState_h)) {
                Button(onClick = {navController.navigate ("welcome")},
                    colors = ButtonDefaults.buttonColors(
                        Color.Magenta)) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null,)
                }
                Button (onClick = {}){Text("Viết gì vô nhỉ?")}
                Button(onClick = {}){Text("Viết gì vô nhỉ?")}
                Button(onClick = {}){Text("Viết gì vô nhỉ?")}
                Button(onClick = {}){Text("Viết gì vô nhỉ?")}
                Button(onClick = {}){Text("Viết gì vô nhỉ?")}
                Button(onClick = {navController.navigate ("text_detail")},
                    colors = ButtonDefaults.buttonColors(
                        Color.Magenta)) {
                    Icon(Icons.Default.ArrowForward, contentDescription = null,)
                }
            }

        }




}
@Composable
fun TextDetailScreen(navController: NavController = rememberNavController()) {
    Surface(modifier = Modifier.padding(24.dp)) {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
            Button(onClick = {navController.navigateUp()},
                modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
            Text("Text Detail", modifier = Modifier.weight(4f),
                textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    append("The ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)){
                        append("quick ")
                    }
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append("Brown")
                    }
                    append(" fox jumps ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over")
                    }
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)){
                        append(" the ")
                    }
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("lazy")
                    }
                    append(" dog.")
                }, fontSize = 36.sp, modifier = Modifier.fillMaxWidth().padding(24.dp)
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Bai01Theme {
        AppNavigation()
    }
}