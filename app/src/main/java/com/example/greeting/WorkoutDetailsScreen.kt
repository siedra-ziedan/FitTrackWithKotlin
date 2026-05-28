package com.example.greeting


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greeting.ui.theme.DarkBackground
import com.example.greeting.ui.theme.DarkSurface
import com.example.greeting.ui.theme.PrimaryOrange
import com.example.greeting.ui.theme.TextGray
import com.example.greeting.ui.theme.TextWhite
import kotlinx.coroutines.delay

@Composable
fun WorkoutDetailsScreen() {

    // ================= TIMER =================

    var time by remember { mutableStateOf(0L) }

    var isRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isRunning) {

        while (isRunning) {

            delay(1000L)

            time++
        }
    }

    val hours = time / 3600

    val minutes = (time % 3600) / 60

    val seconds = time % 60

    val formattedTime = String.format(
        "%02d : %02d : %02d",
        hours,
        minutes,
        seconds
    )

    // =========================================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {

        // ================= HEADER IMAGE =================

        Box {

            Image(
                painter = painterResource(id = R.drawable.run),
                contentDescription = "Workout Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp),
                contentScale = ContentScale.Crop
            )

            // Gradient Overlay

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.25f),
                                Color.Black.copy(alpha = 0.95f)
                            )
                        )
                    )
            )
        }

        // =================================================

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            // ================= TITLE =================

            Text(
                text = stringResource(id = R.string.hiit_title),
                color = TextWhite,
                fontSize = 31.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(10.dp))
            Box(
                    modifier = Modifier
                        .width(75.dp)
                        .height(4.dp)
                        .background(
                            PrimaryOrange,
                            RoundedCornerShape(50.dp)
                        )
                    )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(id = R.string.hiit_subtitle),
                color = TextGray,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )

            // =========================================

            Spacer(modifier = Modifier.height(30.dp))

            // ================= TIMER CARD =================

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = DarkSurface
                ),
                shape = RoundedCornerShape(28.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(26.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                    text = stringResource(id = R.string.workout_timer)
                    )
                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = formattedTime,
                        color = TextWhite,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    // ================= BUTTONS =================

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // Resume Button

                        Surface(
                            shape = CircleShape,
                            color = Color(0xFF262626),
                            shadowElevation = 10.dp,
                            modifier = Modifier.size(62.dp)
                        ) {

                            IconButton(
                                onClick = {
                                    isRunning = true
                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Pause,
                                    contentDescription = null,
                                    tint = TextWhite,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(18.dp))

                        // Main Start Button

                        Surface(
                            shape = CircleShape,
                            color = PrimaryOrange,
                            shadowElevation = 14.dp,
                            modifier = Modifier.size(78.dp)
                        ) {

                            IconButton(
                                onClick = {
                                    isRunning = true
                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(42.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(18.dp))// Stop Button

                        Surface(
                            shape = CircleShape,
                            color = Color(0xFF262626),
                            shadowElevation = 10.dp,
                            modifier = Modifier.size(62.dp)
                        ) {

                            IconButton(
                                onClick = {

                                    isRunning = false


                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Stop,
                                    contentDescription = null,
                                    tint = TextWhite,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }
                    }

                    // =========================================
                }
            }

            // =================================================

            Spacer(modifier = Modifier.height(30.dp))

            // ================= INFO CARDS =================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                InfoCard(
                    icon = Icons.Default.LocalFireDepartment,
                    title = stringResource(id = R.string.calories),
                    value = "350"
                )

                InfoCard(
                    icon = Icons.Default.Bolt,
                    title = stringResource(id = R.string.intensity),
                    value = stringResource(id = R.string.high)
                )

                InfoCard(
                    icon = Icons.Default.Timer,
                    title = stringResource(id = R.string.duration),
                    value = "25 min"
                )
            }

            // ===============================================

            Spacer(modifier = Modifier.height(40.dp))

            // ================= INSTRUCTIONS =================

            Text(
                text = stringResource(id = R.string.instructions),
                color = TextWhite,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(18.dp))

            InstructionCard(
                icon = Icons.Default.WbSunny,
                title = stringResource(id = R.string.warmup_title),
                description = stringResource(id = R.string.warmup_desc)
            )

            InstructionCard(
                icon = Icons.Default.DirectionsRun,
                title = stringResource(id = R.string.sprint_title),
                description = stringResource(id = R.string.sprint_desc)
            )

            InstructionCard(
                icon = Icons.Default.SelfImprovement,
                title = stringResource(id = R.string.rest_title),
                description = stringResource(id = R.string.rest_desc)
            )

            // =================================================

            Spacer(modifier = Modifier.height(34.dp))

            // ================= PERFORMANCE TIP =================

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1D1812)
                ),
                border = BorderStroke(
                    1.dp,
                    PrimaryOrange.copy(alpha = 0.35f)
                )
            ) {

                Row(
                    modifier = Modifier.padding(22.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {Surface(
                    shape = CircleShape,
                    color = PrimaryOrange.copy(alpha = 0.15f),
                    modifier = Modifier.size(54.dp)
                ) {

                    Box(
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.WaterDrop,
                            contentDescription = null,
                            tint = PrimaryOrange,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {

                        Text(
                            text = "Performance Tip",
                            color = TextWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = stringResource(id = R.string.performance_tip),
                            color = TextGray,
                            fontSize = 13.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            // =================================================

            Spacer(modifier = Modifier.height(46.dp))

            // ================= START BUTTON =================

            Button(
                onClick = {

                    isRunning = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryOrange
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp
                )
            ) {

                Text(
                    text = stringResource(id = R.string.start_now),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // ===============================================

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun InfoCard(
    icon: ImageVector,
    title: String,
    value: String
) {

    Card(
        modifier = Modifier
            .width(105.dp)
            .height(112.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = PrimaryOrange,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = value,
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                color = TextGray,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun InstructionCard(
    icon: ImageVector,
    title: String,
    description: String
) {Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
    shape = RoundedCornerShape(24.dp),
    colors = CardDefaults.cardColors(
        containerColor = DarkSurface
    )
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            shape = CircleShape,
            color = PrimaryOrange.copy(alpha = 0.15f),
            modifier = Modifier.size(52.dp)
        ) {

            Box(
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = PrimaryOrange,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {

            Text(
                text = title,
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                color = TextGray,
                fontSize = 13.sp,
                lineHeight = 20.sp
            )
        }
    }
}
}