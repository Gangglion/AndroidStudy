package com.example.navigationfragmentsample

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.navigationfragmentsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0) // note : BottomNavigation 에 패딩이 적용되는 것을 막기 위해 systemBars.bottom 대신 0dp 로 지정
            insets
        }
        setMainNav()
        setBottomNavigation()

        intent?.let {
            navController.handleDeepLink(it)
        }
    }

    private fun setMainNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(binding.navMain.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setBottomNavigation() {
        with(binding.bottomNav) {
            // BottomNavigationView 와 navController 연결. navController 는 BottomNavigationView 에 대한 nav_graph
            setupWithNavController(navController)
            setOnItemSelectedListener { item -> // 선택한 bottom_nav_menu 의 itemId 로 이동. 이때 itemId 와 nav_main 에 선언된 fragment 들의 id 를 동일하게 맞춰주어야 한다.
                navController.navigate(item.itemId)
                return@setOnItemSelectedListener true
            }
            // note : 중복클릭 방지
            setOnItemReselectedListener {  }
        }
    }
}