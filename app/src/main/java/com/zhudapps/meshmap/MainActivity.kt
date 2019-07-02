package com.zhudapps.meshmap

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_coordinator.*
import android.content.Intent
import android.net.Uri


class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainNavigationContract {

    private var clickToList = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //in our dagger dependency graph we are using AndroidInjector to build our graph and injects those dependencies
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        fab.setOnClickListener {
            if (clickToList) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_mapFragment_to_mapListFragment)
                clickToList = false
                (it as FloatingActionButton).setImageResource(R.drawable.ic_pin_drop_white_24dp)
            } else {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_mapListFragment_to_mapFragment)
                clickToList = true
                (it as FloatingActionButton).setImageResource(R.drawable.ic_format_list_bulleted_white_24dp)
            }
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                sendToWeb("https://play.google.com/store/apps/details?id=com.zhudapps.materialcultured")
            }
            R.id.nav_gallery -> {
                sendToWeb("https://play.google.com/store/apps/details?id=com.zhudapps.materialcuteapp")
            }
            R.id.nav_share -> {
                sendToWeb("https://www.github.com/amohnacs15")
            }
            R.id.nav_send -> {

                sendToWeb("https://seedtowealth.com/")
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun sendToWeb(url: String) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}
