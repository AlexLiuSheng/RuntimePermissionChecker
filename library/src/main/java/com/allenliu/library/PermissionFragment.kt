package com.allenliu.library

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *@author Allen Liu
 *@Date 2020/5/21
 *@Desc
 **/
private const val PERMISSION_REQUEST_CODE = 0X01

class PermissionFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    private var callback: ((granted: Boolean) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("TAG", "add fragment")
        arguments?.run {
            getStringArray("permission")?.apply {

                requestPermissions(this, PERMISSION_REQUEST_CODE)
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

    }

    fun setPermissionCallback(callback: ((granted: Boolean) -> Unit)) {
        this.callback = callback
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) { //同意权限申请
                Log.e("TAG", "permission granted")
                callback?.invoke(true)
            } else { //拒绝权限申请
                callback?.invoke(false)
            }
            fragmentManager?.beginTransaction()?.remove(this)
        }
    }
}