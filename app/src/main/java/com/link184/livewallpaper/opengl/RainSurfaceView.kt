package com.link184.livewallpaper.opengl

import android.content.Context
import android.opengl.GLES20.glCullFace
import android.opengl.GLES20.glEnable
import android.opengl.GLSurfaceView
import javax.microedition.khronos.opengles.GL10

class RainSurfaceView(context: Context): GLSurfaceView(context) {
    init {
        setEGLContextClientVersion(2)
        setRenderer(RainRenderer(context))
//        renderMode = RENDERMODE_WHEN_DIRTY
        // enable face culling feature
        glEnable(GL10.GL_CULL_FACE)
        // specify which faces to not draw
        glCullFace(GL10.GL_BACK)
    }
}