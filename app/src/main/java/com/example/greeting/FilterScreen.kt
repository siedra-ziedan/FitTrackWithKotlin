package com.example.greeting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greeting.ui.theme.DarkBackground
import com.example.greeting.ui.theme.DarkSurface
import com.example.greeting.ui.theme.PrimaryOrange
import com.example.greeting.ui.theme.TextGray
import com.example.greeting.ui.theme.TextWhite

// دالة مساعدة لتمرير بيانات التمرين بسهولة
data class WorkoutItem(
    val imageRes: Int,
    val titleRes: Int,
    val durationRes: Int,
    val caloriesRes: Int
)

@Composable
fun FilterScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }

    // قائمة التمارين مع الصور والنصوص التي أضفناها
    val workoutList = listOf(
        WorkoutItem(R.drawable.workout_running, R.string.workout_running, R.string.duration_min, R.string.calories_kcal),
        WorkoutItem(R.drawable.workout_squat, R.string.workout_squat, R.string.duration_min_15, R.string.calories_kcal_450),
        WorkoutItem(R.drawable.workout_jumprope, R.string.workout_jumprope, R.string.duration_min_12, R.string.calories_kcal_280)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = DarkSurface,
                contentColor = PrimaryOrange
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    label = { Text(stringResource(id = R.string.menu_home)) },
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryOrange, selectedTextColor = PrimaryOrange, unselectedIconColor = TextGray, unselectedTextColor = TextGray)
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    label = { Text(stringResource(id = R.string.menu_search)) },
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryOrange, selectedTextColor = PrimaryOrange, unselectedIconColor = TextGray, unselectedTextColor = TextGray)
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.FavoriteBorder, contentDescription = null) },
                    label = { Text(stringResource(id = R.string.menu_favorites)) },
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryOrange, selectedTextColor = PrimaryOrange, unselectedIconColor = TextGray, unselectedTextColor = TextGray)
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                    label = { Text(stringResource(id = R.string.menu_profile)) },
                    selected = selectedItem == 3,
                    onClick = { selectedItem = 3 },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryOrange, selectedTextColor = PrimaryOrange, unselectedIconColor = TextGray, unselectedTextColor = TextGray)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryOrange
            )
            Text(
                text = stringResource(id = R.string.filter_subtitle),
                fontSize = 14.sp,
                color = TextGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // قائمة التمارين
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(workoutList) { workout ->
                    WorkoutCard(workout)
                }
            }
        }
    }
}

@Composable
fun WorkoutCard(workout: WorkoutItem) {
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
                painter = painterResource(id = workout.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // نصوص التمرين (الاسم، الوقت، السعرات)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = workout.titleRes),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${stringResource(id = workout.durationRes)} | ${stringResource(id = workout.caloriesRes)}",
                    fontSize = 14.sp,
                    color = TextGray
                )
            }

            // أيقونات التشغيل والمفضلة
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = TextGray,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = PrimaryOrange,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}