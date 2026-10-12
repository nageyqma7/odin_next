#version 330
#extension GL_ARB_separate_shader_objects : require

layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    mat4 TextureMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
};

layout(location = 0) in vec4 vertexColor;
layout(location = 1) in vec2 localPos;
layout(location = 2) flat in vec2 halfSize;
layout(location = 3) flat in vec4 radii;
layout(location = 4) flat in float edgeWidth;

layout(location = 0) out vec4 fragColor;

float roundedBoxSDF(vec2 p, vec2 extent, float radius) {
    vec2 q = abs(p) - extent + radius;
    return length(max(q, 0.0)) + min(max(q.x, q.y), 0.0) - radius;
}

float shapeDistance() {
    float radius = localPos.x < 0.0
        ? (localPos.y < 0.0 ? radii.x : radii.w)
        : (localPos.y < 0.0 ? radii.y : radii.z);
    radius = min(radius, min(halfSize.x, halfSize.y));

    return roundedBoxSDF(localPos, halfSize, radius);
}

float shadowCoverage() {
    float dist = shapeDistance();
    float falloff = max(edgeWidth, max(fwidth(dist), 1e-4));

    float t = clamp(1.0 - dist / falloff, 0.0, 1.0);

    return t * t * t;
}

void main() {
    float coverage = shadowCoverage();
    if (coverage <= 0.0) discard;

    vec4 color = vertexColor * ColorModulator;

    fragColor = vec4(color.rgb, color.a * coverage);
}
