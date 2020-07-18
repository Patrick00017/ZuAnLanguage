package com.example.zuanlanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var _navController: NavController
    private lateinit var _appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _navController = findNavController(R.id.fragment)
        val fragmentSet = setOf(R.id.GenarateFragment, R.id.DataFragment, R.id.MineFragment)
        bottomNav.setupWithNavController(_navController)
        _appBarConfiguration = AppBarConfiguration.Builder(fragmentSet).build()
        NavigationUI.setupActionBarWithNavController(this, _navController, _appBarConfiguration)
    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp() || _navController.navigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || _navController.navigateUp()
    }
}