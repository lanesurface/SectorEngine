#version 430 core

layout (location = 0) in vec4 position;
layout (location = 0) in vec3 translation;

void main(void)
{
	mat4 translationMatrix = mat4(1.0, 0.0, 0.0, translation.x,
								  0.0, 1.0, 0.0, translation.y,
								  0.0, 0.0, 1.0, translation.z,
								  0.0, 0.0, 0.0, 1.0);
	gl_Position = translationMatrix * position;
}