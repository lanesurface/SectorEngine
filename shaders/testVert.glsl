#version 430 core

layout (location = 0) in vec4 offset;
layout (location = 1) in vec4 color;

out vec4 vs_color;

void main(void)
{
	const vec4 vertices[6] = vec4[6](vec4( 0.0,  1.0, 0.5, 1.0),
									 vec4(-1.0, -1.0, 0.5, 1.0),
									 vec4( 1.0, -1.0, 0.5, 1.0),

									 vec4( 0.0, -1.0, 0.5, 1.0),
									 vec4( 1.0,  1.0, 0.5, 1.0),
									 vec4(-1.0,  1.0, 0.5, 1.0));
	gl_Position = vertices[gl_VertexID] + offset;

	vs_color = color;
}