package com.glion.notfixdpbug

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment

class MainActivity : BaseActivity(), View.OnClickListener {
    private lateinit var mContext: Context
    companion object{
        const val A = 0
        const val B = 1
        const val C = 2
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        findViewById<AppCompatButton>(R.id.btn_a).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.btn_b).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.btn_c).setOnClickListener(this)

        Log.w(TAG, "MainActivity - onCreate : ${resources.configuration.densityDpi}")

        changeFragment(B)
    }

    override fun onStart() {
        super.onStart()
        Log.w(TAG, "MainActivity - onStart : ${resources.configuration.densityDpi}")
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "MainActivity - onResume : ${resources.configuration.densityDpi}")
    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG, "MainActivity - onPause : ${resources.configuration.densityDpi}")
    }

    override fun onStop() {
        super.onStop()
        Log.w(TAG, "MainActivity - onStop : ${resources.configuration.densityDpi}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(TAG, "MainActivity - onDestroy : ${resources.configuration.densityDpi}")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_a -> {
                changeFragment(A)
            }
            R.id.btn_b ->{
                changeFragment(B)
            }
            R.id.btn_c ->{
                changeFragment(C)
            }
        }
    }

    private fun changeFragment(tab: Int){
        val fragment: Fragment = when(tab){
            A -> AFragment()
            B -> BFragment()
            C -> CFragment()
            else -> AFragment()
        }
        supportFragmentManager.beginTransaction().replace(R.id.fv_container, fragment).commit()
    }
}