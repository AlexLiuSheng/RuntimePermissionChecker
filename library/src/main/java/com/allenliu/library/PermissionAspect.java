package com.allenliu.library;//
//
///**
// * @author Allen Liu
// * @Date 2020/5/21
// * @Desc
// **/
//package com.example.demo.permission;
//
//import android.app.Activity;
//import android.content.Context;
//import android.util.Log;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//
///**
// *@author Allen Liu
// *@Date 2020/5/21
// *@Desc
// **/
//@Aspect
//public class PermissionAspect {
//    @Pointcut("execution(@com.example.demo.permission.NeedPermission * *(..))")
//    public void r(){
//
//    }
//    @Around("r()&&@annotation(needPermission)")
//   public void a(JoinPoint point, NeedPermission needPermission){
//        Log.e("w","aspectj");
//        String permissions=needPermission.permission();
//        Object holder=point.getThis();
//
//        if (holder instanceof Fragment){
//            int result=ContextCompat.checkSelfPermission(((Fragment) holder).getContext(),permissions);
//            if(result==android.content.pm.PackageManager.PERMISSION_GRANTED){
//                try {
//                    ((ProceedingJoinPoint)point).proceed();
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//                Log.e("TAG","has permission");
//            }else{
//                Log.e("TAG","has not permission");
//
//            }
//        }else if (holder instanceof Activity){
//            int result=ContextCompat.checkSelfPermission((Context) holder,permissions);
//            if(result==android.content.pm.PackageManager.PERMISSION_GRANTED){
//                try {
//                    Log.e("TAG","has permission");
//                    ((ProceedingJoinPoint)point).proceed();
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//                Log.e("TAG","has permission");
//            }else{
//                Log.e("TAG","has not permission");
//
//            }
//
//        }else{
//
//        }
//
//
//    }
//
//
//}