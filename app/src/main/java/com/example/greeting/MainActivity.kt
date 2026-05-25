package com.example.greeting

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Male
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
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greeting.ui.theme.DarkBackground
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
                        composable("filter") { FilterScreen(navController) }
                    }
                }
            }
        }
    }
}

// 1. الشاشة الترحيبية
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
            shape = androidx.compose.foundation.shape.CircleShape,
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

// 2. شاشة تسجيل الدخول
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
            shape = androidx.compose.foundation.shape.CircleShape,
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

// 3. شاشة إنشاء حساب
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
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = androidx.compose.foundation.shape.CircleShape,
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

// 4. شاشة إكمال البيانات (ممتلئة بالكامل - Sliders داخل صناديق - الوزن حقول صغيرة)
@Composable
fun CompleteProfileScreen(navController: NavController) {
    var selectedGender by remember { mutableStateOf("Male") }
    var age by remember { mutableStateOf(24f) }
    var height by remember { mutableStateOf(170f) }
    var currentWeight by remember { mutableStateOf("") }
    var targetWeight by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(24.dp),
        // توزيع المساحات بالتساوي لملء الشاشة بالكامل بدون فراغات
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // العنوان الرئيسي
        Text(
            text = stringResource(id = R.string.start_workout_plan),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // الجملة التحفيزية
        Text(
            text = stringResource(id = R.string.help_us_customize),
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        // الخط البرتقالي من فوق
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(PrimaryOrange)
        )

        // اختيار الجنس (بإطار)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = { selectedGender = "Female" },
                modifier = Modifier.weight(1f).height(60.dp),
                border = BorderStroke(2.dp, if (selectedGender == "Female") PrimaryOrange else TextGray),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent, contentColor = if (selectedGender == "Female") PrimaryOrange else TextGray)
            ) {
                Icon(Icons.Default.Female, contentDescription = "Female", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.female), fontSize = 16.sp)
            }

            OutlinedButton(
                onClick = { selectedGender = "Male" },
                modifier = Modifier.weight(1f).height(60.dp),
                border = BorderStroke(2.dp, if (selectedGender == "Male") PrimaryOrange else TextGray),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent, contentColor = if (selectedGender == "Male") PrimaryOrange else TextGray)
            ) {
                Icon(Icons.Default.Male, contentDescription = "Male", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.male), fontSize = 16.sp)
            }
        }

        // شريط العمر (داخل مستطيل محاط بإطار)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, TextGray), RoundedCornerShape(12.dp))
                .background(DarkSurface, RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = stringResource(id = R.string.age), color = TextGray)
                    Text(text = age.toInt().toString(), color = TextWhite, fontWeight = FontWeight.Bold)
                }
                Slider(
                    value = age,
                    onValueChange = { age = it },
                    valueRange = 10f..90f,
                    colors = SliderDefaults.colors(thumbColor = PrimaryOrange, activeTrackColor = PrimaryOrange, inactiveTrackColor = TextGray.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // شريط الطول (داخل مستطيل محاط بإطار - الحد 220)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, TextGray), RoundedCornerShape(12.dp))
                .background(DarkSurface, RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = stringResource(id = R.string.height_cm), color = TextGray)
                    Text(text = "${height.toInt()} cm", color = TextWhite, fontWeight = FontWeight.Bold)
                }
                Slider(
                    value = height,
                    onValueChange = { height = it },
                    valueRange = 100f..220f,
                    colors = SliderDefaults.colors(thumbColor = PrimaryOrange, activeTrackColor = PrimaryOrange, inactiveTrackColor = TextGray.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // حقول الوزن (مربعات صغيرة - الحد الأقصى 200)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // الوزن الحالي
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.current_weight_kg),
                    color = TextGray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = currentWeight,
                        onValueChange = { newText ->
                            val filtered = newText.filter { it.isDigit() }
                            if (filtered.isEmpty()) currentWeight = ""
                            else if (filtered.toInt() <= 200) currentWeight = filtered
                        },
                        modifier = Modifier.width(90.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange, unfocusedBorderColor = TextGray, cursorColor = PrimaryOrange, focusedContainerColor = DarkSurface, unfocusedContainerColor = DarkSurface)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "kg", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.width(24.dp))

            // الوزن المستهدف
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.target_weight_kg),
                    color = TextGray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = targetWeight,
                        onValueChange = { newText ->
                            val filtered = newText.filter { it.isDigit() }
                            if (filtered.isEmpty()) targetWeight = ""
                            else if (filtered.toInt() <= 200) targetWeight = filtered
                        },
                        modifier = Modifier.width(90.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryOrange, unfocusedBorderColor = TextGray, cursorColor = PrimaryOrange, focusedContainerColor = DarkSurface, unfocusedContainerColor = DarkSurface)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "kg", color = TextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // زر بدء الحساب
        Button(
            onClick = { navController.navigate("filter") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(id = R.string.start_calculation), fontSize = 18.sp, color = TextWhite)
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