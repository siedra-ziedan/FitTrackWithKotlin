package com.example.greeting

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
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greeting.ui.theme.DarkBackground
import com.example.greeting.ui.theme.DarkSurface
import com.example.greeting.ui.theme.PrimaryOrange
import com.example.greeting.ui.theme.TextGray
import com.example.greeting.ui.theme.TextWhite
import android.widget.Toast
@Composable
fun CompleteProfileScreen(navController: NavController) {
    val context = LocalContext.current
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
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(id = R.string.start_workout_plan),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.help_us_customize),
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(PrimaryOrange)
        )

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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
            onClick = {

                // تحقق إن المستخدم حط وزن
                if (currentWeight.isBlank()) {
                    Toast.makeText(context, "Please enter your current weight", Toast.LENGTH_SHORT).show()
                } else {
                    val calculatedGoal = when {

                        currentWeight.toInt() >= 100 -> 900

                        currentWeight.toInt() >= 80 -> 750

                        currentWeight.toInt() >= 60 -> 600

                        else -> 450
                    }
                    // حفظ البيانات
                    LocaleHelper.saveUserMetrics(
                        context,
                        heightCm = height.toInt().toString(),
                        weightKg = currentWeight,
                        age = age.toInt().toString()
                    )
                    LocaleHelper.saveDailyGoal(
                        context,
                        calculatedGoal.toString()
                    )

                    LocaleHelper.saveCurrentProgress(
                        context,
                        "420"
                    )

                    // الانتقال للشاشة الرئيسية (المسار الصح هو main_app)
                    navController.navigate("main_app") {
                        popUpTo("complete_profile") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = stringResource(id = R.string.start_calculation), fontSize = 18.sp, color = TextWhite)
        }
    }
}

