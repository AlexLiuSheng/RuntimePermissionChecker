package com.allenliu.runtimepermissionchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.allenliu.library.NeedPermission

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission()
    }
    @NeedPermission(permission = [android.Manifest.permission.RECORD_AUDIO])
    fun requestPermission(){

    }
}
