package com.link184.livewallpaper

import android.service.wallpaper.WallpaperService
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService.Engine
import android.view.SurfaceHolder

class MyWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine {
        return MyEngine()
    }

    inner class MyEngine : Engine() {
        private val handler = Handler(Looper.getMainLooper())
        private val paint = Paint().apply {
            color = 0xFF00FF00.toInt()
            style = Paint.Style.FILL
        }
        private var running = false

        private val drawRunnable = object : Runnable {
            override fun run() {
                drawFrame()
                if (running) {
                    handler.postDelayed(this, 16) // 60 FPS
                }
            }
        }

        override fun onCreate(surfaceHolder: SurfaceHolder?) {
            super.onCreate(surfaceHolder)
            running = true
            handler.post(drawRunnable)
        }

        override fun onDestroy() {
            super.onDestroy()
            running = false
            handler.removeCallbacks(drawRunnable)
        }

        private fun drawFrame() {
            val surfaceHolder = surfaceHolder ?: return
            val canvas: Canvas? = surfaceHolder.lockCanvas()
            if (canvas != null) {
                canvas.drawColor(0xFF000000.toInt()) // Clear with black
                canvas.drawCircle(100f, 100f, 50f, paint)
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }
}