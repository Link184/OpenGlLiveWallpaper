package com.link184.livewallpaper.opengl.particle

import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.random.Random

data class ParticleSystem(val maxParticles: Int) {
    private val particles: List<Particle>

    init {
        val random = Random(System.nanoTime())
        particles = List(maxParticles) {
            createParticle(random)
        }
    }

    private val vertexData by lazy {
        FloatArray(maxParticles * 4)
        val vertexData = FloatArray(maxParticles * 4)
        val random = Random(System.nanoTime())
        particles.forEachIndexed { index, particle ->
            vertexData[index * 4] = particle.x
            vertexData[index * 4 + 1] = particle.y // y: Evenly spaced from 1 to -1
            vertexData[index * 4 + 2] = random.nextDouble(-1.0, 1.0).toFloat() // z: 0 (not used)
            vertexData[index * 4 + 3] = 1f // w: 1 (homogeneous coordinate)
//            particleVelocities[index] = particle.velocity // Falling speed
        }

        vertexData
    }

    val vertexBuffer by lazy {
        ByteBuffer.allocateDirect(vertexData.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertexData).also {
                it.position(0)
            }
    }

    private fun createParticle(random: Random): Particle {
        val x = random.nextDouble(-1.0, 1.0).toFloat() // Random X position [-1, 1]
        val y = random.nextDouble(-1.0, 1.0).toFloat() // Random Y position [-1, 1]
        val velocityY = -0.02f - Math.random().toFloat() * 0.03f // Random downward speed
        val alpha = 0.5f + Math.random().toFloat() * 0.5f // Random transparency [0.5, 1.0]

        return Particle(x, y, velocityY, alpha)
    }
}