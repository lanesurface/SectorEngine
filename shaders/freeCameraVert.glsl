#version 430 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec4 newRotation;
layout (location = 2) in vec4 newPosition;

mat4 rotationMatrix(vec3 axis, float angle)
{
    axis = normalize(axis);
    float s = sin(angle);
    float c = cos(angle);
    float oc = 1.0 - c;
    
    return mat4(oc * axis.x * axis.x + c,           oc * axis.x * axis.y - axis.z * s,  oc * axis.z * axis.x + axis.y * s,  0.0,
                oc * axis.x * axis.y + axis.z * s,  oc * axis.y * axis.y + c,           oc * axis.y * axis.z - axis.x * s,  0.0,
                oc * axis.z * axis.x - axis.y * s,  oc * axis.y * axis.z + axis.x * s,  oc * axis.z * axis.z + c,           0.0,
                0.0,                                0.0,                                0.0,                                1.0);
}
void main(void)
{
	mat4 translationMatrix = mat4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, newPosition.x, newPosition.y, newPosition.z, 1.0);
	
	mat4 rotMatX = rotationMatrix(vec3(1.0, 0.0, 0.0), newRotation.x); 
	mat4 rotMatY = rotationMatrix(vec3(0.0, 1.0, 0.0), newRotation.y);
	mat4 rotMatZ = rotationMatrix(vec3(0.0, 0.0, 1.0), newRotation.z);
	mat4 rotationMatrix = rotMatZ * rotMatY * rotMatX;
	
	gl_Position = translationMatrix * rotationMatrix * position;
}