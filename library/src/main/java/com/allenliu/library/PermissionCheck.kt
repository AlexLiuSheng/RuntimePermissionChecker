/**
 * @author Allen Liu
 * @Date 2020/5/21
 * @Desc
 */
package com.allenliu.library

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.allenliu.library.FragmentHelper
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * @author Allen Liu
 * @Date 2020/5/21
 * @Desc
 */
@Aspect
class PermissionAspect {
    @Pointcut("call(@com.allenliu.library.NeedPermission * *(..))")
    fun request() {
    }

    @Around("request()&&@annotation(needPermission)")
    fun requestPermission(point: ProceedingJoinPoint, needPermission: NeedPermission) {
        val permissions = needPermission.permission
        val holder = point.getThis()

        fun checkPermission(context: Context, fragmentManager: FragmentManager) {
            val result = ContextCompat.checkSelfPermission(
                context,permissions[0]
            )
            if (result == PackageManager.PERMISSION_GRANTED) {
                Log.e("TAG", "has permission")
                  point.proceed()
            } else {
                Log.e("TAG", "has not permission")
                FragmentHelper.requestPermission(permissions,fragmentManager){
                    if(it){
                        point.proceed()
                    }else{

                    }
                }
            }
        }
        if (holder is Fragment) {
            checkPermission(holder.context!!, holder.fragmentManager!!)
        } else if (holder is FragmentActivity) {
            checkPermission(holder, holder.supportFragmentManager)
        } else {

        }
    }
}