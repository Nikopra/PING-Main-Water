package com.example.mainwater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import com.example.mainwater.databinding.ActivityMainBinding
import com.example.mainwater.fragments.CommunityFragment
import com.example.mainwater.fragments.DevicesFragment
import com.example.mainwater.fragments.HomeFragment
import com.example.mainwater.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.View


class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val devicesFragment = DevicesFragment()
    private val settingsFragment = SettingsFragment()
    private val communityFragment = CommunityFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replaceFragment(homeFragment)
        Log.d("ITM","TEST1")



        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.devices -> replaceFragment(devicesFragment)
                R.id.community -> replaceFragment(communityFragment)
                R.id.settings -> replaceFragment(settingsFragment)
            }
            true
        }
        Log.d("ITM","TEST2")
    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment!=null){

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

}