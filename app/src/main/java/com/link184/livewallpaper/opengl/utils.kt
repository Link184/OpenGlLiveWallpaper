package com.link184.livewallpaper.opengl

import android.content.Context
import android.opengl.GLES20

fun loadShader(type: Int, shaderCode: String): Int {

    // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
    // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
    return GLES20.glCreateShader(type).also { shader ->

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode)
        GLES20.glCompileShader(shader)
    }
}

private fun loadShaderFromResource(context: Context, resourceId: Int): String {
    return context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
}