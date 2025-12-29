package com.we.instagram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.we.instagram.data.prefs.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NavHost setup
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Decide start destination based on login state
        val sessionManager = SessionManager(this)

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        navGraph.setStartDestination(
            if (sessionManager.isLoggedIn()) {
                R.id.homeFragment
            } else {
                R.id.loginFragment
            }
        )

        navController.graph = navGraph
    }
}