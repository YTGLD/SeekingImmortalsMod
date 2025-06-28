#version 150

in vec3 in_Position;

out vec3 frag_Position;

uniform mat4 modelMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

void main() {
	vec4 worldPosition = modelMatrix * vec4(in_Position, 1.0);
	gl_Position = projectionMatrix * viewMatrix * worldPosition;

	frag_Position = worldPosition.xyz;
}
