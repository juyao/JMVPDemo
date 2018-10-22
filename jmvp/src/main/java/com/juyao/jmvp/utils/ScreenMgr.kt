package com.juyao.jmvp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Environment
import android.util.DisplayMetrics
import android.view.WindowManager
import android.webkit.WebView
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by juyao on 2017/11/24.
 * 有关Android屏幕的工具类
 */
class ScreenMgr {
    companion object {
        //获取屏幕宽度
        fun getScreenWith(context: Context): Int {
            val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            return metrics.widthPixels
        }

        //获取屏幕高度
        fun getScreenHeight(context: Context): Int {
            val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            return metrics.heightPixels
        }

        @SuppressLint("PrivateApi")
                //获取状态栏高度
        fun getStatusHeight(context: Context): Int {
            var statusHeight = -1
            try {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val `object` = clazz.newInstance()
                val id = clazz.getField("status_bar_height").get(`object`) as Int
                statusHeight = context.resources.getDimensionPixelSize(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return statusHeight
        }

        /**
         * 功能描述：获取整块屏幕的高度
         *
         * @param context
         * @return int
         */
        fun getRealScreenHeight(context: Context): Int {
            var dpi = 0
            val display = (context as Activity).windowManager
                    .defaultDisplay
            val dm = DisplayMetrics()
            val c: Class<*>
            try {
                c = Class.forName("android.view.Display")
                val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
                method.invoke(display, dm)
                dpi = dm.heightPixels
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return dpi
        }

        /**
         * 功能描述：获取虚拟按键区域的高度
         *
         * @param context
         * @return int 如果有虚拟按键则返回其高度否则返回0；
         */
        fun getNavigationAreaHeight(context: Context): Int {
            val realScreenHeight = getRealScreenHeight(context)
            val screenHeight = getScreenHeight(context)

            return realScreenHeight - screenHeight
        }

        /**
         * 获取导航栏高度
         * @param c
         * @return
         */
        fun getNavigationBarrH(c: Context): Int {
            val resources = c.resources
            val identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            return resources.getDimensionPixelOffset(identifier)
        }


        /**
         * 获取当前屏幕截图，包含状态栏
         */
        fun snapShotWithStatusBar(activity: Activity): Bitmap? {
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val bmp = view.drawingCache
            val width = getScreenWith(activity)
            val height = getScreenHeight(activity)
            var bp: Bitmap? = null
            bp = Bitmap.createBitmap(bmp, 0, 0, width, height)
            view.destroyDrawingCache()
            return bp

        }

        /**
         * 获取当前屏幕截图，不包含状态栏
         */
        fun snapShotWithoutStatusBar(activity: Activity): Bitmap? {
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val bmp = view.drawingCache
            val frame = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(frame)
            val statusBarHeight = frame.top

            val width = getScreenWith(activity)
            val height = getScreenHeight(activity)
            var bp: Bitmap? = null
            bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight)
            view.destroyDrawingCache()
            return bp
        }

        /**
         * 获得标题栏高度
         *
         * @param context 上下文，为Activity对象
         * @return 标题栏高度
         */
//        fun getTitleBarHeight(context: Activity): Int {
//            val contentTop = context.window.findViewById(Window.ID_ANDROID_CONTENT).top
//            return contentTop - getStatusBarHeight(context)
//        }

        /**
         * 获取通知栏高度
         *
         * @param context 上下文
         * @return 通知栏高度
         */
        fun getStatusBarHeight(context: Context): Int {
            var statusBarHeight = 0
            try {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val obj = clazz.newInstance()
                val field = clazz.getField("status_bar_height")
                val temp = Integer.parseInt(field.get(obj).toString())
                statusBarHeight = context.resources.getDimensionPixelSize(temp)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return statusBarHeight
        }

        /**
         * 获取指定Activity的截屏，保存到png文件
         *
         * @param activity activity
         * @return 截屏Bitmap
         */
        private fun takeScreenShot(activity: Activity): Bitmap {
            // View是你需要截图的View
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val b1 = view.drawingCache

            // 获取状态栏高度
            val frame = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(frame)
            val statusBarHeight = frame.top
            // 获取屏幕长和高
            val width = activity.windowManager.defaultDisplay.width
            val height = activity.windowManager.defaultDisplay
                    .height
            val b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight)
            view.destroyDrawingCache()
            return b
        }

        /**
         * 保存bitmap
         *
         * @param b           bitmap
         * @param strFileName 文件名
         * @return 是否保存成功
         */
        private fun savePic(b: Bitmap, strFileName: String): Boolean {
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(strFileName)
                if (null != fos) {
                    b.compress(Bitmap.CompressFormat.PNG, 90, fos)
                    fos.flush()
                    fos.close()
                    return true
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return false
        }

        /**
         * 截取webView快照(webView加载的整个内容的大小)
         *
         * @param webView webview
         * @return 截屏bitmap
         */
        private fun captureWebView(webView: WebView): Bitmap {
            val snapShot = webView.capturePicture()

            val bmp = Bitmap.createBitmap(snapShot.width, snapShot.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmp)
            snapShot.draw(canvas)
            return bmp
        }

        /**
         * 根据毫秒获得格式化日期
         *
         * @param time   毫秒数
         * @param format 格式化字符串
         * @return 格式化后的字符串
         */
        private fun getDate(time: Long, format: String): String {
            val date = Date(time)
            val formatter = SimpleDateFormat(format)
            return formatter.format(date)
        }

        /**
         * 获得文件名
         *
         * @param context 上下文
         * @return 文件名
         */
        private fun getFileName(context: Context): String {
            val fileName = getDate(System.currentTimeMillis(), "yyyyMMddHHmmss") + ".png"
            val localPath: String
            if (isExistsSD) {
                localPath = context.externalCacheDir.toString() + File.separator + fileName
            } else {
                localPath = context.filesDir.toString() + fileName
            }

            return localPath
        }

        /**
         * 截屏并保存
         *
         * @param a activity
         * @return 保存的路径
         */
        fun shoot(a: Activity): String {
            val localPath = getFileName(a)
            val ret = savePic(takeScreenShot(a), localPath)
            return if (ret) {
                localPath
            } else {
                ""
            }
        }

        /**
         * 截屏并保存
         *
         * @param context 上下文
         * @param webView webview
         * @return 保存的路径
         */
        fun shootWebView(context: Context, webView: WebView): String {
            val localPath = getFileName(context)
            val ret = savePic(captureWebView(webView), localPath)
            return if (ret) {
                localPath
            } else {
                ""
            }
        }

        /**
         * 是否存在sd卡
         *
         * @return 是否存在sd卡
         */
        private val isExistsSD: Boolean
            get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }


}

