package com.example.mvvm_tmdb

import android.os.Bundle

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm_tmdb.ui.Now_Play.NowPlayFragment
import com.example.mvvm_tmdb.ui.dashboard.DashboardFragment
import com.example.mvvm_tmdb.ui.notifications.NotificationsFragment
import com.example.mvvm_tmdb.util.replace


class MainActivity : AppCompatActivity() {

    private val nowplayFragment : NowPlayFragment by lazy {
        NowPlayFragment()
    }

    private val dashboardFragment: DashboardFragment by lazy{
        DashboardFragment()
    }

    private val notificationFragment : NotificationsFragment by lazy{
        NotificationsFragment()
    }

    companion object {
        private val util = com.example.mvvm_tmdb.util.util()
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.navigation_nowplay->{
                replace(R.id.container, nowplayFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard->{
                replace(R.id.container, dashboardFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications->{
                replace(R.id.container, notificationFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replace(R.id.container, nowplayFragment)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}
