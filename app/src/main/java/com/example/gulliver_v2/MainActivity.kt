package com.example.gulliver_v2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration

import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tiptime.data.Datasource
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navController: NavController
    lateinit var toolbar: Toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    lateinit var bottomNav: BottomNavigationView
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.gulli_nav_frag) as NavHostFragment

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        navController = navHostFragment.navController

        //findViewById<Toolbar>(R.id.topBar)
        //    .setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).setOpenableLayout(drawerLayout).build()
        toolbar.setupWithNavController( navController, appBarConfiguration)

        navView.setNavigationItemSelectedListener(this)
        bottomNav = findViewById(R.id.bottom_navigation) as BottomNavigationView
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.page_2 -> {
                    loadFragment(GMapsFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.page_3 -> {
                    loadFragment(CartFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.page_4 -> {
                    loadFragment(ProfileFragment())
                    return@setOnNavigationItemReselectedListener
                }
            }
        }


    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.gulli_nav_frag,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        }

    private fun onListItemClick(position: Int) {
        val myDataset = Datasource().loadAffirmations()
        Toast.makeText(this, myDataset[position].stringResourceId, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.gulli_nav_frag)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {

            }
            R.id.nav_messages -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_GMapsFragment_to_itineraryFragment)
            }
            R.id.nav_friends -> {
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



}