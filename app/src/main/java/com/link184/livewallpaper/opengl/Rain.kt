package com.link184.livewallpaper.opengl

import android.content.Context
import android.opengl.GLES20
import com.link184.livewallpaper.opengl.particle.ParticleSystem

private const val COORDS_PER_VERTEX = 3

class Rain(context: Context) {
    private val program = RainShaderProgram().loadProgram(context)
    private var startTime = System.currentTimeMillis()
    private var timeUniform = 0
    private val particleCount = 300
    private val particleSystem = ParticleSystem(particleCount)

    fun draw() {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(program)

        timeUniform = GLES20.glGetUniformLocation(program, "uTime")

        // Update the time uniform
        val currentTime = (System.currentTimeMillis() - startTime) / 1000.0f
        GLES20.glUniform1f(timeUniform, currentTime)

        // Inside your rendering loop or initialization

        val vertexBuffer = particleSystem.vertexBuffer

        // Get the attribute location
        val positionAttribute = GLES20.glGetAttribLocation(program, "aPosition")
        GLES20.glEnableVertexAttribArray(positionAttribute)
        GLES20.glVertexAttribPointer(positionAttribute, 4, GLES20.GL_FLOAT, false, 0, vertexBuffer)

        // Draw the point
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, particleCount)

        // Disable the attribute
        GLES20.glDisableVertexAttribArray(positionAttribute)
    }
}