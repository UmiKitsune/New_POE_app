package com.example.poe_app.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.poe_app.R
import kotlin.properties.Delegates

const val POE_APP_PREFERENCES = "POE_APP_PREFERENCES"
const val PREF_USERNAME_KEY = "PREF_USERNAME_KEY "
const val PREF_LANGUAGE_KEY = "PREF_LANGUAGE_KEY"

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_POE_app)
        setContentView(R.layout.activity_main)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupActionBarWithNavController(this, navController)

        preferences = getSharedPreferences(POE_APP_PREFERENCES, Context.MODE_PRIVATE)

        val userName = preferences.getString(PREF_USERNAME_KEY, "")
        val language = preferences.getString(PREF_LANGUAGE_KEY, "ru")
        if (userName!!.isNotEmpty()){
            navController.navigate(
                LogInFragmentDirections.actionLogInFragmentToMainFragment(
                    userName = userName,
                    language = language!!
                )
            )
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        preferences.edit().clear().apply()
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_poe, menu)
        return super.onCreateOptionsMenu(menu)
    }


    //TODO: сделать интерфейс, есть actionBar or not + поменять actionBar на ToolBar?
}