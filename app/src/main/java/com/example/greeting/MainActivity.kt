package com.example.greeting

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greeting.ui.theme.DarkSurface
import com.example.greeting.ui.theme.GreetingTheme
import com.example.greeting.ui.theme.PrimaryOrange
import com.example.greeting.ui.theme.TextGray
import com.example.greeting.ui.theme.TextWhite
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {
                        composable("splash") { SplashScreen(navController) }
                        composable("login") { LoginScreen(navController) }
                        composable("signup") { SignUpScreen(navController) }
                        composable("complete_profile") { CompleteProfileScreen(navController) }
                        composable("home") { HomeScreen() }
                    }
                }
            }
        }
    }
}

// 1. الشاشة الترحيبية (أيقونة القلب)
@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(120.dp),
            shape = CircleShape,
            color = PrimaryOrange
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Heart Icon",
                tint = Color.White,
                modifier = Modifier.padding(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryOrange
        )
        Text(
            text = stringResource(id = R.string.splash_subtitle),
            fontSize = 14.sp,
            color = TextGray,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// 2. شاشة تسجيل الدخول (أيقونة الدمبل/الوزن)
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(true) }
    val emptyError = stringResource(id = R.string.error_empty_fields)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = PrimaryOrange
        ) {
            Icon(
                imageVector = Icons.Filled.FitnessCenter,
                contentDescription = "Weight Icon",
                tint = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.email_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryOrange,
                unfocusedBorderColor = TextGray,
                focusedLabelColor = PrimaryOrange,
                cursorColor = PrimaryOrange,
                focusedContainerColor = DarkSurface,
                unfocusedContainerColor = DarkSurface
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.password_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryOrange,
                unfocusedBorderColor = TextGray,
                focusedLabelColor = PrimaryOrange,
                cursorColor = PrimaryOrange,
                focusedContainerColor = DarkSurface,
                unfocusedContainerColor = DarkSurface
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    colors = CheckboxDefaults.colors(checkedColor = PrimaryOrange)
                )
                Text(text = stringResource(id = R.string.agree_terms), color = TextGray, fontSize = 12.sp)
            }
            TextButton(onClick = { }) {
                Text(text = stringResource(id = R.string.forgot_password), color = PrimaryOrange, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(context, emptyError, Toast.LENGTH_SHORT).show()
                } else {
                    navController.navigate("home")
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(id = R.string.login_title), fontSize = 18.sp, color = TextWhite)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = R.string.terms_text), color = TextGray, fontSize = 10.sp)

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = { navController.navigate("signup") }) {
            Text(text = stringResource(id = R.string.no_account), color = PrimaryOrange)
        }
    }
}

// 3. شاشة إنشاء حساب (أيقونة البرق)
@Composable
fun SignUpScreen(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val emptyError = stringResource(id = R.string.error_empty_fields)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = PrimaryOrange
        ) {
            Icon(
                imageVector = Icons.Filled.Bolt,
                contentDescription = "Lightning Icon",
                tint = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = stringResource(id = R.string.signup_title), fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextWhite)
        Text(text = stringResource(id = R.string.signup_desc), color = TextGray, fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp))

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(id = R.string.username_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange, unfocusedBorderColor = TextGray, focusedLabelColor = PrimaryOrange, cursorColor = PrimaryOrange, focusedContainerColor = DarkSurface, unfocusedContainerColor = DarkSurface)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.email_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange, unfocusedBorderColor = TextGray, focusedLabelColor = PrimaryOrange, cursorColor = PrimaryOrange, focusedContainerColor = DarkSurface, unfocusedContainerColor = DarkSurface)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.password_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange, unfocusedBorderColor = TextGray, focusedLabelColor = PrimaryOrange, cursorColor = PrimaryOrange, focusedContainerColor = DarkSurface, unfocusedContainerColor = DarkSurface)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (username.isBlank() || email.isBlank() || password.isBlank()) {
                    Toast.makeText(context, emptyError, Toast.LENGTH_SHORT).show()
                } else {
                    navController.navigate("complete_profile")
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(id = R.string.signup_button), fontSize = 18.sp, color = TextWhite)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = R.string.or_signup_with), color = TextGray)

        Spacer(modifier = Modifier.height(24.dp))
        TextButton(onClick = { navController.navigate("login") { popUpTo("signup") { inclusive = true } } }) {
            Text(text = stringResource(id = R.string.have_account), color = PrimaryOrange)
        }
    }
}

// 4. شاشة إكمال البيانات (صورة body كخلفية شفافة وحقول تقبل الكتابة)
@Composable
fun CompleteProfileScreen(navController: NavController) {
    var selectedGender by remember { mutableStateOf("Male") }

    // إضافة متغيرات لتقبل الكتابة في الحقول
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var currentWeight by remember { mutableStateOf("") }
    var targetWeight by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // الصورة كخلفية شفافة
        Image(
            painter = painterResource(id = R.drawable.body),
            contentDescription = "Body Background",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.15f), // شفافية الخلفية
            contentScale = ContentScale.Crop
        )

        // المحتوى فوق الخلفية
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(id = R.string.complete_profile_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )

            Spacer(modifier = Modifier.height(32.dp))

            // اختيار الجنس
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = { selectedGender = "Male" },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (selectedGender == "Male") PrimaryOrange else DarkSurface,
                        contentColor = TextWhite
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.male))
                }
                OutlinedButton(
                    onClick = { selectedGender = "Female" },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (selectedGender == "Female") PrimaryOrange else DarkSurface,
                        contentColor = TextWhite
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = stringResource(id = R.string.female))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // حقل العمر (لوحة أرقام)
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text(stringResource(id = R.string.age_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange, unfocusedBorderColor = TextGray, focusedLabelColor = PrimaryOrange, cursorColor = PrimaryOrange, focusedContainerColor = DarkSurface, unfocusedContainerColor = DarkSurface)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // حقل الطول (لوحة أرقام)
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text(stringResource(id = R.string.height_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange, unfocusedBorderColor = TextGray, focusedLabelColor = PrimaryOrange, cursorColor = PrimaryOrange, focusedContainerColor = DarkSurface, unfocusedContainerColor = DarkSurface)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // حقل الوزن الحالي (لوحة أرقام)
            OutlinedTextField(
                value = currentWeight,
                onValueChange = { currentWeight = it },
                label = { Text(stringResource(id = R.string.current_weight_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange, unfocusedBorderColor = TextGray, focusedLabelColor = PrimaryOrange, cursorColor = PrimaryOrange, focusedContainerColor = DarkSurface, unfocusedContainerColor = DarkSurface)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // حقل الوزن المستهدف (لوحة أرقام)
            OutlinedTextField(
                value = targetWeight,
                onValueChange = { targetWeight = it },
                label = { Text(stringResource(id = R.string.target_weight_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange, unfocusedBorderColor = TextGray, focusedLabelColor = PrimaryOrange, cursorColor = PrimaryOrange, focusedContainerColor = DarkSurface, unfocusedContainerColor = DarkSurface)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.complete_button), fontSize = 18.sp, color = TextWhite)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// شاشة رئيسية مؤقتة
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Kinetic Cardio!", color = TextWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}