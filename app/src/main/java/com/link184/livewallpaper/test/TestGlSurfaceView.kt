package com.link184.livewallpaper.test

import android.content.Context
import android.opengl.GLES20.glCullFace
import android.opengl.GLES20.glEnable
import android.opengl.GLSurfaceView
import android.view.MotionEvent
import javax.microedition.khronos.opengles.GL10

private const val TOUCH_SCALE_FACTOR: Float = 180.0f / 320f

class TestGlSurfaceView(context: Context) : GLSurfaceView(context) {
    private val renderer: TestGlRenderer
    private var previousX: Float = 0f
    private var previousY: Float = 0f

    init {
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        renderer = TestGlRenderer()

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer)
        // Render the view only when there is a change in the drawing data.
        // To allow the triangle to rotate automatically, this line is commented out:
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY

        // enable face culling feature
        glEnable(GL10.GL_CULL_FACE)
        // specify which faces to not draw
        glCullFace(GL10.GL_BACK)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        val x: Float = event.x
        val y: Float = event.y

        when (event.action) {
            MotionEvent.ACTION_MOVE -> {

                var dx: Float = x - previousX
                var dy: Float = y - previousY

                // reverse direction of rotation above the mid-line
                if (y > height / 2) {
                    dx *= -1
                }

                // reverse direction of rotation to left of the mid-line
                if (x < width / 2) {
                    dy *= -1
                }

                renderer.angle += (dx + dy) * TOUCH_SCALE_FACTOR
                requestRender()
            }
        }

        previousX = x
        previousY = y
        return true
    }
}