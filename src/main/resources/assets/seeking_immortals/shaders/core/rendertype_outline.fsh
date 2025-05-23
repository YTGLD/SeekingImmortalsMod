#version 150

uniform sampler2D Sampler0;

// 将 ColorModulator 设置为红色
uniform vec4 ColorModulator = vec4(1.0, 0.0, 0.0, 1.0);

in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0);
    if (color.a == 0.0) {
        discard;
    }
    fragColor = vec4(ColorModulator.rgb * vertexColor.rgb, ColorModulator.a);
}
