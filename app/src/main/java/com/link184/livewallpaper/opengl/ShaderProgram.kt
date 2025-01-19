package com.link184.livewallpaper.opengl

import android.content.Context
import android.opengl.GLES20

interface ShaderProgram {
    val vertexShader: Int
    val fragmentShader: Int

    fun loadProgram(context: Context): Int = GLES20.glCreateProgram().also {
        // add the vertex shader to program
        GLES20.glAttachShader(it, loadShaderFromResource(GLES20.GL_VERTEX_SHADER, context, vertexShader))

        // add the fragment shader to program
        GLES20.glAttachShader(it, loadShaderFromResource(GLES20.GL_FRAGMENT_SHADER, context, fragmentShader))

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(it)
    }

    private fun loadShader(type: Int, shaderCode: String): Int {
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        return GLES20.glCreateShader(type).also { shader ->

            // add the source code to the shader and compile it
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)
        }
    }

    fun loadShaderFromResource(type: Int, context: Context, resourceId: Int): Int {
        return loadShader(
            type,
            context.resources.openRawResource(resourceId).bufferedReader().use { it.readText().also(::println) })
    }
}