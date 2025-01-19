package com.link184.livewallpaper.opengl

import com.link184.livewallpaper.R

class RainShaderProgram: ShaderProgram {
    override val vertexShader: Int = R.raw.rain_vertex_shader
    override val fragmentShader: Int = R.raw.rain_fragment_shader
}