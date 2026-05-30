package com.example.greeting

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greeting.ui.theme.GreetingTheme
import java.util.Locale

object LocaleHelper {
    private const val PREF_NAME = "AppPrefs"
    private const val LANGUAGE_KEY = "app_language"
    private const val HEIGHT_KEY = "user_height"
    private const val WEIGHT_KEY = "user_weight"
    private const val USERNAME_KEY = "user_username"
    private const val AGE_KEY = "user_age"
    private const val DAILY_GOAL_KEY = "daily_goal"
    private const val CURRENT_PROGRESS_KEY = "current_progress"
    fun saveLanguage(context: Context, language: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(LANGUAGE_KEY, language).apply()
    }

    fun getLanguage(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(LANGUAGE_KEY, "en") ?: "en"
    }

    fun setLocale(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }

    fun saveUserMetrics(context: Context, heightCm: String, weightKg: String, age: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(HEIGHT_KEY, heightCm).putString(WEIGHT_KEY, weightKg).putString(AGE_KEY, age).apply()
    }
    fun saveUsername(context: Context, username: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(USERNAME_KEY, username).apply()
    }

    fun getHeight(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(HEIGHT_KEY, "") ?: ""
    }

    fun getWeight(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(WEIGHT_KEY, "") ?: ""
    }

    fun getAge(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(AGE_KEY, "") ?: ""
    }

    fun getUsername(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(USERNAME_KEY, "") ?: ""
    }
    fun saveDailyGoal(context: Context, goal: String) {

        val prefs: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        prefs.edit().putString(DAILY_GOAL_KEY, goal).apply()
    }

    fun getDailyGoal(context: Context): String {

        val prefs: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        return prefs.getString(DAILY_GOAL_KEY, "600") ?: "600"
    }

    fun saveCurrentProgress(context: Context, progress: String) {

        val prefs: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        prefs.edit().putString(CURRENT_PROGRESS_KEY, progress).apply()
    }

    fun getCurrentProgress(context: Context): String {

        val prefs: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        return prefs.getString(CURRENT_PROGRESS_KEY, "420") ?: "420"
    }
}
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
                        composable("main_app") { FilterScreen(navController) } // شاشة التبويبات الخمسة
                        composable("details") { WorkoutDetailsScreen()  }
                    }
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getLanguage(newBase)))
    }
}