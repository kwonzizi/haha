package com.acha.haha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.acha.haha.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private val frame: RelativeLayout by lazy {
        findViewById(R.id.body_container)
    }

    private val bottomNavigationView : BottomNavigationView by lazy{
        findViewById(R.id.bottom_navigation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(frame.id, FirstFragment()).commit()

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_calendar -> {

                    replaceFragment(FirstFragment())
                    true

                }

                R.id.nav_todo -> {

                    replaceFragment(TodoFragment())
                    true

                }

                R.id.nav_settings -> {

                    replaceFragment(SettingsFragment())
                    true

                }

                else -> false
            }
        }
    }

        fun replaceFragment(fragment: Fragment){

            supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()

        }
    }
