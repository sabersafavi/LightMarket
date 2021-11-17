package com.saber.flashlightappsmarket

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.saber.flashlightappsmarket.databinding.ActivityMainBinding
import com.saber.flashlightappsmarket.databinding.LayoutNavBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout

        ///
        val bottomNavView: BottomNavigationView = binding.appBarMain.navBottomView
        val bottomNavController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_flash_lights, R.id.nav_colored_lights, R.id.nav_sos_alerts
            )
        )
        setupActionBarWithNavController(bottomNavController, appBarConfiguration)
        bottomNavView.setupWithNavController(bottomNavController)
        ///

//        val navView: NavigationView = binding.navView
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_flash_lights, R.id.nav_colored_lights, R.id.nav_sos_alerts
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        ///
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity2, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//
//    binding = LayoutNavBinding.inflate(layoutInflater)
//    setContentView(binding.root)
//
//    val navView: BottomNavigationView = binding.navBottomView
//
//    val navController = findNavController(R.id.nav_host_fragment_activity_main)
//    // Passing each menu ID as a set of Ids because each
//    // menu should be considered as top level destinations.
//    val appBarConfiguration = AppBarConfiguration(
//        setOf(
//            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//        )
//    )
//    setupActionBarWithNavController(navController, appBarConfiguration)
//    navView.setupWithNavController(navController)
//}