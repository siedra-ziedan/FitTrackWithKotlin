package com.example.greeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.greeting.ui.theme.DarkBackground
import com.example.greeting.ui.theme.DarkSurface
import com.example.greeting.ui.theme.PrimaryOrange
import com.example.greeting.ui.theme.TextGray
import com.example.greeting.ui.theme.TextWhite
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
@Composable
fun DashboardScreen() {

    val context = LocalContext.current
    var showLanguageDialog by remember {
        mutableStateOf(false)
    }
    val username =
        LocaleHelper.getUsername(context).ifEmpty { "Mohammed" }

    val currentWeight =
        LocaleHelper.getWeight(context).ifEmpty { "78.5" }

    val caloriesBurned = 1240
    val caloriesGoal = 1580
    val remaining = caloriesGoal - caloriesBurned

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())

    ) {



        // HEADER

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal =10.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Kinetic Cardio ⚡",
                color = PrimaryOrange,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                lineHeight = 26.sp,
                modifier = Modifier.padding(start = 2.dp)
            )

            IconButton(
                onClick = {
                    showLanguageDialog = true
                },
                modifier = Modifier.offset(y = -4.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = TextWhite,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // WELCOME

        Text(
            text = "${stringResource(id = R.string.hello)} $username 👋",
            color = TextWhite,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(
                id = R.string.goal_remaining,
                remaining
            ),
            color = TextGray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(26.dp))

        // MAIN CALORIES CARD

        MainCaloriesCard(
            burned = caloriesBurned,
            goal = caloriesGoal
        )

        Spacer(modifier = Modifier.height(20.dp))

        // WEEK ACTIVITY

        WeeklyActivityCard()

        Spacer(modifier = Modifier.height(20.dp))

        // TOTAL WORKOUTS CARD

        TotalWorkoutCard()

        Spacer(modifier = Modifier.height(20.dp))

        // WEIGHT CARD

        WeightCard(currentWeight)
    }
    if (showLanguageDialog) {

        AlertDialog(
            onDismissRequest = {
                showLanguageDialog = false
            },

            title = {
                Text(
                    text = stringResource(id = R.string.select_language),
                    color = TextWhite
                )
            },

            text = {

                Column {

                    TextButton(
                        onClick = {

                            LocaleHelper.saveLanguage(context, "en")
                            LocaleHelper.setLocale(context, "en")

                            (context as? Activity)?.recreate()

                            showLanguageDialog = false
                        }
                    ) {

                        Text(
                            text = stringResource(id = R.string.english),
                            color = TextWhite
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(
                        onClick = {

                            LocaleHelper.saveLanguage(context, "ar")
                            LocaleHelper.setLocale(context, "ar")

                            (context as? Activity)?.recreate()

                            showLanguageDialog = false
                        }
                    ) {

                        Text(
                            text = stringResource(id = R.string.arabic),
                            color = PrimaryOrange
                        )
                    }
                }
            },

            confirmButton = {},

            containerColor = DarkSurface
        )
    }
}

@Composable
fun MainCaloriesCard(
    burned: Int,
    goal: Int
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface)

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all =  12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(170.dp)
            ) {

                Canvas(
                    modifier = Modifier.size(170.dp)
                ) {

                    // الجزء البرتقالي

                    drawArc(
                        color = PrimaryOrange,
                        startAngle = 140f,
                        sweepAngle = 190f,
                        useCenter = false,
                        style = Stroke(
                            width = 18f,
                            cap = StrokeCap.Round
                        )
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "$burned",
                        color = TextWhite,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = stringResource(id = R.string.calories),
                        color = TextGray,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = stringResource(id = R.string.active_calories),
                color = TextWhite,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${stringResource(id = R.string.goal)}: $goal",
                color = TextGray,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun WeeklyActivityCard() {

    val bars = listOf(
        70.dp,
        95.dp,
        155.dp,
        125.dp,
        185.dp,
        145.dp,
        110.dp
    )

    val days = listOf(
        stringResource(id = R.string.sat),
        stringResource(id = R.string.sun),
        stringResource(id = R.string.mon),
        stringResource(id = R.string.tue),
        stringResource(id = R.string.wed),
        stringResource(id = R.string.thu),
        stringResource(id = R.string.fri)
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface)
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = stringResource(id = R.string.week_activity),
                color = TextWhite,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(28.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {

                bars.forEachIndexed { index, height ->

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        Box(
                            modifier = Modifier
                                .width(34.dp)
                                .height(height)
                                .clip(RoundedCornerShape(18.dp))
                                .background(
                                    if (index == 4)
                                        PrimaryOrange
                                    else
                                        PrimaryOrange.copy(alpha = 0.45f)
                                )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = days[index],
                            color = TextGray,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TotalWorkoutCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface)
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(
                        PrimaryOrange.copy(alpha = 0.12f)
                    ),
                contentAlignment = Alignment.Center
            ) {Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = null,
                tint = PrimaryOrange,
                modifier = Modifier.size(30.dp)
            )
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column {

                Text(
                    text = stringResource(id = R.string.total_workouts),
                    color = TextWhite,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {

                    Text(
                        text = "24",
                        color = TextWhite,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "+3 ${stringResource(id = R.string.this_week)}",
                        color = Color(0XFF64B5F6),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun WeightCard(weight: String) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface)
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = stringResource(id = R.string.current_weight),
                color = TextWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "$weight ${stringResource(id = R.string.kg)}",
                color = PrimaryOrange,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(18.dp))

            LinearProgressIndicator(
                progress = { 0.72f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(50.dp)),
                color = Color(0xFFFC9B7F),
                trackColor = Color(0XFF4A4A4A)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "${stringResource(id = R.string.start)} 85 ${stringResource(id = R.string.kg)}",
                    color = TextGray,
                    fontSize = 14.sp
                )

                Text(
                    text = "${stringResource(id = R.string.goal)} 75 ${stringResource(id = R.string.kg)}",
                    color = TextGray,
                    fontSize = 14.sp
                )
            }
        }
    }
}