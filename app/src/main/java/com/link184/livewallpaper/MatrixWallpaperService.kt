package com.link184.livewallpaper

import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import com.link184.livewallpaper.test.TestGlSurfaceView


class MatrixWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine {
        return MatrixWallpaperEngine()
    }

    private inner class MatrixWallpaperEngine : Engine() {
        private lateinit var surfaceView: TestGlSurfaceView

        override fun onSurfaceCreated(holder: SurfaceHolder) {
            surfaceView = TestGlSurfaceView(this@MatrixWallpaperService)
            surfaceView.surfaceCreated(holder)
        }

        override fun onSurfaceChanged(
            holder: SurfaceHolder,
            format: Int,
            width: Int,
            height: Int
        ) {
            surfaceView.surfaceChanged(holder, format, width, height)
        }

        override fun onVisibilityChanged(visible: Boolean) {
            if (visible) {
                surfaceView.onResume();
            } else {
                surfaceView.onPause();
            }
        }

        override fun onDestroy() {
            surfaceView.onPause()
        }
    }
}