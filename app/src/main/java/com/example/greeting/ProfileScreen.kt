package com.example.greeting

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // <--- ضفتي هاد الاستيراد المهم
import com.example.greeting.ui.theme.*

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    var showLanguageDialog by remember { mutableStateOf(false) }

    // جلب البيانات المحفوظة
    val savedHeight = LocaleHelper.getHeight(context)
    val savedWeight = LocaleHelper.getWeight(context)
    val savedAge = LocaleHelper.getAge(context)
    val savedUsername = LocaleHelper.getUsername(context).ifEmpty { "User Name" }

    // بيانات مؤقتة للبطاقات العلوية
    val hoursValue = 11
    val caloriesValue = 450
    val exercisesValue = 8

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // 1. اسم التطبيق بأعلى اليمين
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryOrange,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 2. صورة البروفايل والاسم
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = savedUsername, color = TextWhite, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 3. بطاقات النشاط
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(title = stringResource(id = R.string.hours), value = hoursValue.toString(), progress = 0.55f, modifier = Modifier.weight(1f))
            StatCard(title = stringResource(id = R.string.calories_profile), value = caloriesValue.toString(), progress = 0.45f, modifier = Modifier.weight(1f))
            StatCard(title = stringResource(id = R.string.exercises), value = exercisesValue.toString(), progress = 0.8f, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 4. المقاييس الحيوية
        Text(text = stringResource(id = R.string.vital_metrics), color = TextWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricCard(title = stringResource(id = R.string.age), value = "${savedAge.ifEmpty { "0" }} years", modifier = Modifier.weight(1f))
            MetricCard(title = "Weight", value = "${savedWeight.ifEmpty { "0" }}\n kg", modifier = Modifier.weight(1f))
            MetricCard(title = "Height", value = "${savedHeight.ifEmpty { "0" }}\n cm", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 5. قائمة الحساب والنشاط
        ProfileOptionItem(icon = Icons.Default.Edit, title = stringResource(id = R.string.edit_profile))

        // رجّعت الإحصائيات الأسبوعية هنا
        ProfileOptionItem(icon = Icons.Default.BarChart, title = stringResource(id = R.string.weekly_stats))

        ProfileOptionItem(
            icon = Icons.Default.Settings,
            title = stringResource(id = R.string.settings_profile),
            onClick = { showLanguageDialog = true }
        )

        // زر تسجيل الخروج المعدّل
        ProfileOptionItem(
            icon = Icons.AutoMirrored.Filled.Logout,
            title = stringResource(id = R.string.logout),
            tint = Color.Red,
            onClick = {
                // الرجوع لشاشة تسجيل الدخول ومسح كل الشاشات اللي قبلها
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        )
    }

    // نافذة تغيير اللغة
    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = { Text(text = stringResource(id = R.string.select_language), color = TextWhite) },
            text = {
                Column {
                    TextButton(onClick = {
                        LocaleHelper.saveLanguage(context, "en")
                        LocaleHelper.setLocale(context, "en")
                        (context as? Activity)?.recreate()
                        showLanguageDialog = false
                    }) {
                        Text(text = stringResource(id = R.string.english), color = TextWhite, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = {
                        LocaleHelper.saveLanguage(context, "ar")
                        LocaleHelper.setLocale(context, "ar")
                        (context as? Activity)?.recreate()
                        showLanguageDialog = false
                    }) {
                        Text(text = stringResource(id = R.string.arabic), color = PrimaryOrange, fontSize = 18.sp)
                    }
                }
            },
            confirmButton = {},
            containerColor = DarkSurface
        )
    }
}

// بطاقات النشاط
@Composable
fun StatCard(title: String, value: String, progress: Float, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, color = TextGray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, color = TextWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(TextGray.copy(alpha = 0.3f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color(0xFFFC9B7F))
                )
            }
        }
    }
}

// بطاقات المقاييس
@Composable
fun MetricCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface)
    ) {
        Column(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, color = TextGray, fontSize = 14.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, color = TextWhite, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }
    }
}

// عناصر القائمة
@Composable
fun ProfileOptionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    tint: Color = TextWhite,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, tint = tint, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, color = tint, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}