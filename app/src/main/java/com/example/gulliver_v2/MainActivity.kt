package com.example.gulliver_v2

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.Gravity
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.gulliver_v2.databinding.ActivityMainBinding
import com.example.tiptime.data.Datasource
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var toolbar: Toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.gulli_nav_frag) as NavHostFragment


        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).setOpenableLayout(drawerLayout).build()
        toolbar.setupWithNavController( navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val botNavView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        botNavView.setupWithNavController(navController)

        //val but = findViewById<>(R.id.chats)
        val popupViewNotif = layoutInflater.inflate(R.layout.join_trip_popup, null)
        val popupWindowNotif = PopupWindow(popupViewNotif, 300, 700)
        popupWindowNotif.isOutsideTouchable = true;

        popupWindowNotif.contentView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Dismiss the popup window
                popupWindowNotif.dismiss()
            }
            true
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.notifications -> {
                    // Handle favorite icon press
                    popupWindowNotif.showAtLocation(binding.root, Gravity.RIGHT, 0, 0)
                    true
                }
                R.id.chats -> {
                    // Handle search icon press
                    true
                }

                else -> false
            }
        }

        }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.gulli_nav_frag)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}


