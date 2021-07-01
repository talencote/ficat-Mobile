package com.talencote.ficat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.talencote.ficat.api.ApiClient
import com.talencote.ficat.api.SessionManager
import com.talencote.ficat.data.dto.FanficDto
import com.talencote.ficat.fragments.FanficContentFragment
import com.talencote.ficat.fragments.FanficDetailsFragment
import com.talencote.ficat.recyclerview.RouteToFragments
import com.talencote.ficat.viewmodels.SettingsViewModel
import io.reactivex.Observer
import org.w3c.dom.Text


class MainActivity : AppCompatActivity(), RouteToFragments {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)

        apiClient = ApiClient()
        if (sessionManager.fetchAuthToken() == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            setContentView(R.layout.activity_main)

            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)

            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            val navView: NavigationView = findViewById(R.id.nav_view)
            val navController = findNavController(R.id.nav_host_fragment)

            val navHeader: View = navView.getHeaderView(0)
            val user = sessionManager.fetchUser()
            navHeader.findViewById<TextView>(R.id.username).text = user.username
            navHeader.findViewById<TextView>(R.id.email).text = user.email

            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            appBarConfiguration = AppBarConfiguration(setOf(
                    R.id.nav_popular, R.id.nav_foryou, R.id.nav_favorites, R.id.nav_settings), drawerLayout)
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onFanficSelected(fanfic: FanficDto) {
        this.supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, FanficDetailsFragment.newInstance(fanfic), FanficDetailsFragment::class.java.simpleName)
            addToBackStack("trans: to fanfic details")
            commit()
        }
    }

    override fun readFanfic(id: Int) {
        this.supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, FanficContentFragment.newInstance(id), FanficContentFragment::class.java.simpleName)
            addToBackStack("trans: to fanfic details")
            commit()
        }
    }
}