package com.talencote.ficat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.talencote.ficat.api.ApiClient
import com.talencote.ficat.api.SessionManager
import com.talencote.ficat.data.dto.FanficDto
import com.talencote.ficat.data.dto.LoginResponse
import com.talencote.ficat.fragments.FragmentFanficDetails
import com.talencote.ficat.recyclerview.RouteToFragments
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class MainActivity : AppCompatActivity(), RouteToFragments {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)

        apiClient = ApiClient()
        var flag: Boolean = false
        flag = (this.getSharedPreferences(this.getString(R.string.app_name), Context.MODE_PRIVATE).contains("user_token"))
//        try {
//            apiClient.getApiService(this).testToken().enqueue(object : Callback<Boolean> {
//                override fun onFailure(call: Call<Boolean>, t: Throwable) {
//                }
//
//                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
//                    flag = true
//                }
//            })
//        } catch (e : Exception) {
//            e.printStackTrace()
//        }

        if (!flag) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            setContentView(R.layout.activity_main)

            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)

            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            val navView: NavigationView = findViewById(R.id.nav_view)
            val navController = findNavController(R.id.nav_host_fragment)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            appBarConfiguration = AppBarConfiguration(setOf(
                    R.id.nav_popular, R.id.nav_foryou, R.id.nav_favorites), drawerLayout)
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
            replace(R.id.nav_host_fragment, FragmentFanficDetails.newInstance(fanfic), FragmentFanficDetails::class.java.simpleName)
            addToBackStack("trans: to fanfic details")
            commit()
        }
    }
}