package com.example.greeting

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greeting.ui.theme.DarkSurface
import com.example.greeting.ui.theme.PrimaryOrange
import com.example.greeting.ui.theme.TextGray
import com.example.greeting.ui.theme.TextWhite

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(true) }

    // جلب نصوص الأخطاء من strings.xml
    val emptyError = stringResource(id = R.string.error_empty_fields)
    val emailError = stringResource(id = R.string.error_invalid_email)

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
                // 1. التحقق من أن الحقول ليست فارغة
                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(context, emptyError, Toast.LENGTH_SHORT).show()
                }
                // 2. التحقق من أن الإيميل ينتهي بـ @gmail.com وطوله أكبر من 10 أحرف
                else if (!email.trim().endsWith("@gmail.com") || email.trim().length <= 10) {
                    Toast.makeText(context, emailError, Toast.LENGTH_SHORT).show()
                }
                // 3. إذا نجح التحقق، انتقل للشاشة الرئيسية
                else {
                    navController.navigate("main_app") {
                        popUpTo("splash") { inclusive = true }
                    }
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