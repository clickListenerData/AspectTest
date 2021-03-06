package com.xz.aspecttest

import android.Manifest
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.xz.aspectlib.annotation.*
import com.xz.aspectlib.utils.ThreadEnum

@ActivityTime
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

//        aop()
//        sd()
//        intercept()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @IOThread(value = ThreadEnum.MAIN)
    fun aop() {
        Log.i("zzzzzzzzz",(Looper.getMainLooper() == Looper.myLooper()).toString())
    }

    @Intercept(type = [2])
    fun intercept() {
        Log.i("zzzzzzzzzzz","intercept")
    }

    @Permission(value = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE],requestCode = 1)
    fun sd() {  // 全部权限 同意后  才执行
        Log.i("zzzzzzzzz","sd")
    }

    @PermissionCancel  // 权限请求被拒绝
    fun cancel(code: Int) {
        Log.i("zzzzzzzzzz","cancel  $code")
    }

    @PermissionDenied  // 永久拒绝某权限
    fun denied(code: Int) {
        Log.i("zzzzzzzzzz","denied  $code")
    }


}
