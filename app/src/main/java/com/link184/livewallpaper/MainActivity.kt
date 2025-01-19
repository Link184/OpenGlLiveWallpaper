package com.link184.livewallpaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.link184.livewallpaper.opengl.RainSurfaceView

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(RainSurfaceView(this))
//        setContent {
//            Box(modifier = Modifier.fillMaxSize()) {
//                Button(onClick = {
//                    val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER).apply {
//                        putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
//                            ComponentName(this@MainActivity, MatrixWallpaperService::class.java)
//                        )
//                    }
//                    startActivity(intent)
//                }) {
//                    Text("Set wallpaper")
//                }
//            }
//        }
    }
}