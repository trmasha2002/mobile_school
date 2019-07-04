package com.example.mariatrapicyna.smart_fridge

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)


        val navigationView = findViewById<NavigationView>(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this)

        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addNewProduct -> supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ProductFragment()
                ).commit()
            R.id.listOfProduct -> supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ListFragment()
                ).commit()
            R.id.setting -> supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    SettingsFragment()
                ).commit()

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}