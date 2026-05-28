package com.example.greeting

import android.app.Activity
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.navigation.NavController
import com.example.greeting.ui.theme.DarkBackground
import com.example.greeting.ui.theme.DarkSurface
import com.example.greeting.ui.theme.PrimaryOrange
import com.example.greeting.ui.theme.TextGray
import com.example.greeting.ui.theme.TextWhite

data class WorkoutItem(
    val imageRes: Int,
    val titleRes: Int,
    val durationRes: Int,
    val caloriesRes: Int,
    val category: String
)

@Composable
fun FilterScreen(navController: NavController) {
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf(1) } // 1 = My Exercises كتبويب افتراضي
    var selectedFilter by remember { mutableStateOf("All") }
    var showLanguageDialog by remember { mutableStateOf(false) }

    val favoriteWorkouts = remember { mutableStateListOf<WorkoutItem>() }

    val filters = listOf("All", "Cardio", "Strength", "HIIT")

    val workoutList = listOf(
        WorkoutItem(R.drawable.workout_running, R.string.workout_running, R.string.duration_min, R.string.calories_kcal, "Cardio"),
        WorkoutItem(R.drawable.workout_jumprope, R.string.workout_jumprope, R.string.duration_min_12, R.string.calories_kcal_280, "Cardio"),
        WorkoutItem(R.drawable.workout_cycling, R.string.workout_cycling, R.string.duration_min_30, R.string.calories_kcal_500, "Cardio"),
        WorkoutItem(R.drawable.workout_swimming, R.string.workout_swimming, R.string.duration_min_60, R.string.calories_kcal_700, "Cardio"),
        WorkoutItem(R.drawable.workout_squat, R.string.workout_squat, R.string.duration_min_15, R.string.calories_kcal_450, "Strength"),
        WorkoutItem(R.drawable.workout_weightlifting, R.string.workout_weightlifting, R.string.duration_min_45, R.string.calories_kcal_600, "Strength"),
        WorkoutItem(R.drawable.workout_pushups, R.string.workout_pushups, R.string.duration_min_10, R.string.calories_kcal_150, "Strength"),
        WorkoutItem(R.drawable.workout_burpees, R.string.workout_burpees, R.string.duration_min_10, R.string.calories_kcal_400, "HIIT")
    )

    val filteredWorkouts = if (selectedFilter == "All") {
        workoutList
    } else {
        workoutList.filter { it.category == selectedFilter }
    }

    val toggleFavorite: (WorkoutItem) -> Unit = { workout ->
        if (favoriteWorkouts.contains(workout)) {
            favoriteWorkouts.remove(workout)
        } else {
            favoriteWorkouts.add(workout)
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = DarkSurface,
                contentColor = PrimaryOrange
            ) {
                // 1. Dashboard (مؤقتاً فارغ)
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Dashboard, contentDescription = null) },
                    label = { Text(stringResource(id = R.string.menu_dashboard)) },
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryOrange, selectedTextColor = PrimaryOrange, unselectedIconColor = TextGray, unselectedTextColor = TextGray)
                )
                // 2. My Exercises (يحتوي على الفلترة والتمارين)
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.FitnessCenter, contentDescription = null) },
                    label = { Text(stringResource(id = R.string.menu_my_exercises)) },
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryOrange, selectedTextColor = PrimaryOrange, unselectedIconColor = TextGray, unselectedTextColor = TextGray)
                )
                // 3. Favorites
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                    label = { Text(stringResource(id = R.string.menu_favorites)) },
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryOrange, selectedTextColor = PrimaryOrange, unselectedIconColor = TextGray, unselectedTextColor = TextGray)
                )
                // 4. Log
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.DateRange, contentDescription = null) },
                    label = { Text(stringResource(id = R.string.menu_log)) },
                    selected = selectedItem == 3,
                    onClick = { selectedItem = 3 },
                    colors = NavigationBarItemDefaults.colors(selectedIconColor = PrimaryOrange, selectedTextColor = PrimaryOrange, unselectedIconColor = TextGray, unselectedTextColor = TextGray)
                )
                // 5. Profile
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                    label = { Text(stringResource(id = R.string.menu_profile)) },
                    selected = selectedItem == 4,
                    onClick = { selectedItem = 4 },
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
            when (selectedItem) {
                0 -> { // تبويب Dashboard (فارغ مؤقتاً)
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(
                        text = stringResource(id = R.string.coming_soon),
                        color = TextGray,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                1 -> { // تبويب My Exercises (نقلنا له كل الفلترة والتمارين)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = stringResource(id = R.string.app_name), fontSize = 28.sp, fontWeight = FontWeight.Bold, color = PrimaryOrange)
                            Text(text = stringResource(id = R.string.filter_subtitle), fontSize = 14.sp, color = TextGray)
                        }
                        IconButton(onClick = { showLanguageDialog = true }) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = stringResource(id = R.string.settings), tint = TextWhite, modifier = Modifier.size(28.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(filters) { filter ->
                            FilterChip(
                                selected = selectedFilter == filter,
                                onClick = { selectedFilter = filter },
                                label = { Text(filter) },
                                colors = FilterChipDefaults.filterChipColors(selectedContainerColor = PrimaryOrange, selectedLabelColor = Color.White, containerColor = DarkSurface, labelColor = TextGray),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(filteredWorkouts) { workout ->
                            WorkoutCard(
                                workout = workout,
                                isFavorite = favoriteWorkouts.contains(workout),
                                onToggleFavorite = { toggleFavorite(workout)},
                                navController=navController
                            )
                        }
                    }
                }
                2 -> { // تبويب Favorites
                    Text(
                        text = stringResource(id = R.string.my_favorites),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryOrange,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    if (favoriteWorkouts.isEmpty()) {
                        Spacer(modifier = Modifier.height(100.dp))
                        Text(
                            text = stringResource(id = R.string.no_favorites),
                            color = TextGray,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(favoriteWorkouts) { workout ->
                                WorkoutCard(
                                    workout = workout,
                                    isFavorite = true,
                                    onToggleFavorite = { toggleFavorite(workout) },
                                    navController=navController
                                )
                            }
                        }
                    }
                }
                else -> { // تبويبات Log و Profile (مؤقتاً)
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(
                        text = stringResource(id = R.string.coming_soon),
                        color = TextGray,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
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
}

@Composable
fun WorkoutCard(workout: WorkoutItem,isFavorite: Boolean,onToggleFavorite: () -> Unit,navController: NavController) {
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
            Image(
                painter = painterResource(id = workout.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = workout.titleRes), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${stringResource(id = workout.durationRes)} | ${stringResource(id = workout.caloriesRes)}", fontSize = 14.sp, color = TextGray)
            }

            Column(verticalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else TextGray,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                IconButton(
                    onClick = {
                        navController.navigate("details")
                    }
                ) {

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
}