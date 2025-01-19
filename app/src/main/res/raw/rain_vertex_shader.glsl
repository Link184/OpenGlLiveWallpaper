attribute vec4 aPosition;
uniform float uTime;     // Time uniform
const float VELOCITY = -0.11; // Constant velocity for all particles

const float POINT_BASE_SIZE = 10.0; // Base size of the particle
const float Z_NEAR = 0.1;          // Near clipping plane
const float Z_FAR = 10.0;          // Far clipping plane

void main() {
    vec4 position = aPosition;

    // Update the Y position based on velocity and time
    position.y += VELOCITY * uTime * abs(position.z);

    // Wrap particle back to the top if it goes off-screen
    if (position.y <= -1.0) {
        position.y += 2.0;
    }

    gl_Position = position;

    // Compute particle size based on Z position
    float zDepth = position.z;
    float size = POINT_BASE_SIZE * (1.0 - ((zDepth - Z_NEAR) / (Z_FAR - Z_NEAR)));

    // Clamp size to avoid too small or too large particles
    size = clamp(size, 1.0, 20.0);

    gl_PointSize = size;
}