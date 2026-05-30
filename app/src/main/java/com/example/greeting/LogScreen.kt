package com.example.greeting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // <-- تعديل الأيقونة
import androidx.compose.material.icons.automirrored.filled.ArrowForward // <-- تعديل الأيقونة
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greeting.ui.theme.*

@Composable
fun LogScreen(navController: NavController) {
    var selectedDay by remember { mutableIntStateOf(5) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        // 1. اسم التطبيق
        item {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryOrange,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 2. بطاقة التقويم
        item {
            CalendarCard(selectedDay = selectedDay, onDaySelected = { selectedDay = it })
            Spacer(modifier = Modifier.height(24.dp))
        }

        // 3. عبارة سجل التمارين
        item {
            Text(
                text = stringResource(id = R.string.exercise_log_this_month),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // 4. بطاقات التمارين (تم إضافة navController لكل واحد)
        item {
            LogWorkoutCard(
                imageRes = R.drawable.jump,
                title = stringResource(id = R.string.workout_jumprope),
                date = stringResource(id = R.string.log_date_1),
                calories = "450",
                duration = "30",
                navController = navController
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            LogWorkoutCard(
                imageRes = R.drawable.run,
                title = stringResource(id = R.string.workout_running),
                date = stringResource(id = R.string.log_date_2),
                calories = "330",
                duration = "45",
                navController = navController
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            LogWorkoutCard(
                imageRes = R.drawable.hund,
                title = stringResource(id = R.string.workout_weightlifting),
                date = stringResource(id = R.string.log_date_3),
                calories = "550",
                duration = "20",
                navController = navController
            )
        }
    }
}

@Composable
fun CalendarCard(selectedDay: Int, onDaySelected: (Int) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous", tint = TextWhite) // <-- تعديل الأيقونة
                }
                Text(
                    text = stringResource(id = R.string.october_2026),
                    color = TextWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next", tint = TextWhite) // <-- تعديل الأيقونة
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val daysOfWeek = listOf(
                R.string.sunday, R.string.monday, R.string.tuesday, R.string.wednesday,
                R.string.thursday, R.string.friday, R.string.saturday
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                daysOfWeek.forEach { day ->
                    Text(
                        text = stringResource(id = day),
                        color = TextGray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            CalendarRow(days = listOf(0, 0, 0, 0, 1, 2, 3), selectedDay = selectedDay, onDaySelected = onDaySelected)
            CalendarRow(days = listOf(4, 5, 6, 7, 8, 9, 10), selectedDay = selectedDay, onDaySelected = onDaySelected)
            CalendarRow(days = listOf(11, 12, 13, 14, 15, 16, 17), selectedDay = selectedDay, onDaySelected = onDaySelected)
            CalendarRow(days = listOf(18, 19, 20, 21, 22, 23, 24), selectedDay = selectedDay, onDaySelected = onDaySelected)
            CalendarRow(days = listOf(25, 26, 27, 28, 29, 30, 31), selectedDay = selectedDay, onDaySelected = onDaySelected)
        }
    }
}

@Composable
fun CalendarRow(days: List<Int>, selectedDay: Int, onDaySelected: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        days.forEach { day ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .size(36.dp)
                    .clip(CircleShape)
                    // هنا غيرنا اللون ليصير برتقالي فاتح شفاف
                    .background(if (day == selectedDay) PrimaryOrange.copy(alpha = 0.3f) else Color.Transparent)
                    .clickable(enabled = day != 0) { onDaySelected(day) },
                contentAlignment = Alignment.Center
            ) {
                if (day != 0) {
                    Text(
                        text = day.toString(),
                        // غيرنا لون الخط ليصير برتقالي غامق عشان يبان على الخلفية الفاتحة
                        color = if (day == selectedDay) PrimaryOrange else TextWhite,
                        fontSize = 14.sp,
                        fontWeight = if (day == selectedDay) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun LogWorkoutCard(imageRes: Int, title: String, date: String, calories: String, duration: String, navController: NavController) { // <-- إضافة navController هنا
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // صورة التمرين
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // تفاصيل التمرين
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = title, color = TextWhite, fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text(text = stringResource(id = R.string.kcal, calories), color = PrimaryOrange, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = date, color = TextGray, fontSize = 10.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(TextGray.copy(alpha = 0.3f))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // زر عرض التفاصيل مربوط بالتنقل
                    Button(
                        onClick = {
                            navController.navigate("details") // <-- الربط هنا
                        },
                        modifier = Modifier.height(24.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryOrange,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = stringResource(id = R.string.view_details), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }

                    Text(text = stringResource(id = R.string.minutes, duration), color = TextGray, fontSize = 10.sp)
                }
            }
        }
    }
}