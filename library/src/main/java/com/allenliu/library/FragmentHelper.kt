package com.allenliu.library

import android.os.Bundle
import androidx.fragment.app.FragmentManager

/**
 *@author Allen Liu
 *@Date 2020/5/21
 *@Desc
 **/
object FragmentHelper {
    private const val FRAGMENT_TAG="permission_tag"
    private val permissionFragment= PermissionFragment()
    fun requestPermission(permissions:Array<String>,fragmentManager: FragmentManager,result:(granted:Boolean)->Unit){
        permissionFragment.setPermissionCallback(result)
        permissionFragment.arguments= Bundle().apply {
            putStringArray("permission",permissions)
        }
        fragmentManager.beginTransaction().add(permissionFragment, FRAGMENT_TAG)
            .commitNow()

    }

}