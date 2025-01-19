attribute vec4 aPosition;
uniform float uTime;     // Time uniform
const float uVelocity = -0.11; // Constant velocity for all particles

void main() {
    vec4 position = aPosition;

    // Update the Y position based on velocity and time
    position.y += uVelocity * uTime * abs(position.z);

    // Wrap particle back to the top if it goes off-screen
    if (position.y <= -1.0) {
        position.y += 2.0;
    }

    gl_Position = position;
    gl_PointSize = 10.0;
}