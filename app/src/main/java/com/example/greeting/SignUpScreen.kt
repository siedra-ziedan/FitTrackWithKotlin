package com.example.greeting

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greeting.R // <--- مهم جداً إضافة هاد السطر
import com.example.greeting.ui.theme.DarkSurface
import com.example.greeting.ui.theme.PrimaryOrange
import com.example.greeting.ui.theme.TextGray
import com.example.greeting.ui.theme.TextWhite

@Composable
fun SignUpScreen(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
                } else if (!email.trim().endsWith("@gmail.com") || email.trim().length <= 10) {
                    Toast.makeText(context, emailError, Toast.LENGTH_SHORT).show()
                } else {
                    // حفظ اسم المستخدم قبل الانتقال
                    LocaleHelper.saveUsername(context, username)

                    navController.navigate("complete_profile")
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(id = R.string.signup_button), fontSize = 18.sp, color = TextWhite)
        }
    }
}