package com.badlogic.gdx.graphics;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface GL20 {
    public static final int GL_ACTIVE_ATTRIBUTES = 0x8B89;
    public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 0x8B8A;
    public static final int GL_ACTIVE_TEXTURE = 0x84E0;
    public static final int GL_ACTIVE_UNIFORMS = 0x8B86;
    public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 0x8B87;
    public static final int GL_ALIASED_LINE_WIDTH_RANGE = 33902;
    public static final int GL_ALIASED_POINT_SIZE_RANGE = 33901;
    public static final int GL_ALPHA = 6406;
    public static final int GL_ALPHA_BITS = 0xD55;
    public static final int GL_ALWAYS = 0x207;
    public static final int GL_ARRAY_BUFFER = 0x8892;
    public static final int GL_ARRAY_BUFFER_BINDING = 0x8894;
    public static final int GL_ATTACHED_SHADERS = 0x8B85;
    public static final int GL_BACK = 0x405;
    public static final int GL_BLEND = 3042;
    public static final int GL_BLEND_COLOR = 0x8005;
    public static final int GL_BLEND_DST_ALPHA = 0x80CA;
    public static final int GL_BLEND_DST_RGB = 0x80C8;
    public static final int GL_BLEND_EQUATION = 0x8009;
    public static final int GL_BLEND_EQUATION_ALPHA = 0x883D;
    public static final int GL_BLEND_EQUATION_RGB = 0x8009;
    public static final int GL_BLEND_SRC_ALPHA = 0x80CB;
    public static final int GL_BLEND_SRC_RGB = 0x80C9;
    public static final int GL_BLUE_BITS = 0xD54;
    public static final int GL_BOOL = 35670;
    public static final int GL_BOOL_VEC2 = 0x8B57;
    public static final int GL_BOOL_VEC3 = 0x8B58;
    public static final int GL_BOOL_VEC4 = 0x8B59;
    public static final int GL_BUFFER_SIZE = 34660;
    public static final int GL_BUFFER_USAGE = 0x8765;
    public static final int GL_BYTE = 0x1400;
    public static final int GL_CCW = 0x901;
    public static final int GL_CLAMP_TO_EDGE = 0x812F;
    public static final int GL_COLOR_ATTACHMENT0 = 0x8CE0;
    public static final int GL_COLOR_BUFFER_BIT = 0x4000;
    public static final int GL_COLOR_CLEAR_VALUE = 3106;
    public static final int GL_COLOR_WRITEMASK = 3107;
    public static final int GL_COMPILE_STATUS = 0x8B81;
    public static final int GL_COMPRESSED_TEXTURE_FORMATS = 0x86A3;
    public static final int GL_CONSTANT_ALPHA = 0x8003;
    public static final int GL_CONSTANT_COLOR = 0x8001;
    public static final int GL_COVERAGE_BUFFER_BIT_NV = 0x8000;
    public static final int GL_CULL_FACE = 0xB44;
    public static final int GL_CULL_FACE_MODE = 0xB45;
    public static final int GL_CURRENT_PROGRAM = 0x8B8D;
    public static final int GL_CURRENT_VERTEX_ATTRIB = 0x8626;
    public static final int GL_CW = 0x900;
    public static final int GL_DECR = 0x1E03;
    public static final int GL_DECR_WRAP = 0x8508;
    public static final int GL_DELETE_STATUS = 0x8B80;
    public static final int GL_DEPTH_ATTACHMENT = 0x8D00;
    public static final int GL_DEPTH_BITS = 0xD56;
    public static final int GL_DEPTH_BUFFER_BIT = 0x100;
    public static final int GL_DEPTH_CLEAR_VALUE = 0xB73;
    public static final int GL_DEPTH_COMPONENT = 6402;
    public static final int GL_DEPTH_COMPONENT16 = 0x81A5;
    public static final int GL_DEPTH_FUNC = 0xB74;
    public static final int GL_DEPTH_RANGE = 0xB70;
    public static final int GL_DEPTH_TEST = 0xB71;
    public static final int GL_DEPTH_WRITEMASK = 2930;
    public static final int GL_DITHER = 0xBD0;
    public static final int GL_DONT_CARE = 0x1100;
    public static final int GL_DST_ALPHA = 0x304;
    public static final int GL_DST_COLOR = 0x306;
    public static final int GL_DYNAMIC_DRAW = 35048;
    public static final int GL_ELEMENT_ARRAY_BUFFER = 0x8893;
    public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 0x8895;
    public static final int GL_EQUAL = 0x202;
    public static final int GL_ES_VERSION_2_0 = 1;
    public static final int GL_EXTENSIONS = 0x1F03;
    public static final int GL_FALSE = 0;
    public static final int GL_FASTEST = 0x1101;
    public static final int GL_FIXED = 0x140C;
    public static final int GL_FLOAT = 0x1406;
    public static final int GL_FLOAT_MAT2 = 0x8B5A;
    public static final int GL_FLOAT_MAT3 = 0x8B5B;
    public static final int GL_FLOAT_MAT4 = 0x8B5C;
    public static final int GL_FLOAT_VEC2 = 0x8B50;
    public static final int GL_FLOAT_VEC3 = 0x8B51;
    public static final int GL_FLOAT_VEC4 = 0x8B52;
    public static final int GL_FRAGMENT_SHADER = 0x8B30;
    public static final int GL_FRAMEBUFFER = 0x8D40;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 36049;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 0x8CD0;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 36050;
    public static final int GL_FRAMEBUFFER_BINDING = 36006;
    public static final int GL_FRAMEBUFFER_COMPLETE = 36053;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 36054;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS = 36057;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055;
    public static final int GL_FRAMEBUFFER_UNSUPPORTED = 36061;
    public static final int GL_FRONT = 0x404;
    public static final int GL_FRONT_AND_BACK = 0x408;
    public static final int GL_FRONT_FACE = 0xB46;
    public static final int GL_FUNC_ADD = 0x8006;
    public static final int GL_FUNC_REVERSE_SUBTRACT = 0x800B;
    public static final int GL_FUNC_SUBTRACT = 0x800A;
    public static final int GL_GENERATE_MIPMAP = 0x8191;
    public static final int GL_GENERATE_MIPMAP_HINT = 33170;
    public static final int GL_GEQUAL = 0x206;
    public static final int GL_GREATER = 0x204;
    public static final int GL_GREEN_BITS = 0xD53;
    public static final int GL_HIGH_FLOAT = 0x8DF2;
    public static final int GL_HIGH_INT = 0x8DF5;
    public static final int GL_IMPLEMENTATION_COLOR_READ_FORMAT = 0x8B9B;
    public static final int GL_IMPLEMENTATION_COLOR_READ_TYPE = 0x8B9A;
    public static final int GL_INCR = 0x1E02;
    public static final int GL_INCR_WRAP = 0x8507;
    public static final int GL_INFO_LOG_LENGTH = 0x8B84;
    public static final int GL_INT = 0x1404;
    public static final int GL_INT_VEC2 = 0x8B53;
    public static final int GL_INT_VEC3 = 0x8B54;
    public static final int GL_INT_VEC4 = 0x8B55;
    public static final int GL_INVALID_ENUM = 0x500;
    public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 0x506;
    public static final int GL_INVALID_OPERATION = 0x502;
    public static final int GL_INVALID_VALUE = 0x501;
    public static final int GL_INVERT = 0x150A;
    public static final int GL_KEEP = 0x1E00;
    public static final int GL_LEQUAL = 0x203;
    public static final int GL_LESS = 0x201;
    public static final int GL_LINEAR = 0x2601;
    public static final int GL_LINEAR_MIPMAP_LINEAR = 0x2703;
    public static final int GL_LINEAR_MIPMAP_NEAREST = 0x2701;
    public static final int GL_LINES = 1;
    public static final int GL_LINE_LOOP = 2;
    public static final int GL_LINE_STRIP = 3;
    public static final int GL_LINE_WIDTH = 0xB21;
    public static final int GL_LINK_STATUS = 0x8B82;
    public static final int GL_LOW_FLOAT = 0x8DF0;
    public static final int GL_LOW_INT = 0x8DF3;
    public static final int GL_LUMINANCE = 6409;
    public static final int GL_LUMINANCE_ALPHA = 6410;
    public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0x8B4D;
    public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;
    public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS = 0x8DFD;
    public static final int GL_MAX_RENDERBUFFER_SIZE = 34024;
    public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 34930;
    public static final int GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT = 0x84FF;
    public static final int GL_MAX_TEXTURE_SIZE = 0xD33;
    public static final int GL_MAX_TEXTURE_UNITS = 34018;
    public static final int GL_MAX_VARYING_VECTORS = 0x8DFC;
    public static final int GL_MAX_VERTEX_ATTRIBS = 0x8869;
    public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 35660;
    public static final int GL_MAX_VERTEX_UNIFORM_VECTORS = 0x8DFB;
    public static final int GL_MAX_VIEWPORT_DIMS = 0xD3A;
    public static final int GL_MEDIUM_FLOAT = 0x8DF1;
    public static final int GL_MEDIUM_INT = 0x8DF4;
    public static final int GL_MIRRORED_REPEAT = 0x8370;
    public static final int GL_NEAREST = 0x2600;
    public static final int GL_NEAREST_MIPMAP_LINEAR = 0x2702;
    public static final int GL_NEAREST_MIPMAP_NEAREST = 0x2700;
    public static final int GL_NEVER = 0x200;
    public static final int GL_NICEST = 0x1102;
    public static final int GL_NONE = 0;
    public static final int GL_NOTEQUAL = 0x205;
    public static final int GL_NO_ERROR = 0;
    public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 0x86A2;
    public static final int GL_NUM_SHADER_BINARY_FORMATS = 0x8DF9;
    public static final int GL_ONE = 1;
    public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 0x8004;
    public static final int GL_ONE_MINUS_CONSTANT_COLOR = 0x8002;
    public static final int GL_ONE_MINUS_DST_ALPHA = 0x305;
    public static final int GL_ONE_MINUS_DST_COLOR = 0x307;
    public static final int GL_ONE_MINUS_SRC_ALPHA = 0x303;
    public static final int GL_ONE_MINUS_SRC_COLOR = 0x301;
    public static final int GL_OUT_OF_MEMORY = 0x505;
    public static final int GL_PACK_ALIGNMENT = 0xD05;
    public static final int GL_POINTS = 0;
    public static final int GL_POLYGON_OFFSET_FACTOR = 0x8038;
    public static final int GL_POLYGON_OFFSET_FILL = 0x8037;
    public static final int GL_POLYGON_OFFSET_UNITS = 0x2A00;
    public static final int GL_RED_BITS = 3410;
    public static final int GL_RENDERBUFFER = 0x8D41;
    public static final int GL_RENDERBUFFER_ALPHA_SIZE = 0x8D53;
    public static final int GL_RENDERBUFFER_BINDING = 36007;
    public static final int GL_RENDERBUFFER_BLUE_SIZE = 0x8D52;
    public static final int GL_RENDERBUFFER_DEPTH_SIZE = 36180;
    public static final int GL_RENDERBUFFER_GREEN_SIZE = 0x8D51;
    public static final int GL_RENDERBUFFER_HEIGHT = 0x8D43;
    public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 0x8D44;
    public static final int GL_RENDERBUFFER_RED_SIZE = 0x8D50;
    public static final int GL_RENDERBUFFER_STENCIL_SIZE = 0x8D55;
    public static final int GL_RENDERBUFFER_WIDTH = 0x8D42;
    public static final int GL_RENDERER = 0x1F01;
    public static final int GL_REPEAT = 0x2901;
    public static final int GL_REPLACE = 0x1E01;
    public static final int GL_RGB = 6407;
    public static final int GL_RGB565 = 0x8D62;
    public static final int GL_RGB5_A1 = 0x8057;
    public static final int GL_RGBA = 6408;
    public static final int GL_RGBA4 = 0x8056;
    public static final int GL_SAMPLER_2D = 0x8B5E;
    public static final int GL_SAMPLER_CUBE = 0x8B60;
    public static final int GL_SAMPLES = 0x80A9;
    public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 0x809E;
    public static final int GL_SAMPLE_BUFFERS = 0x80A8;
    public static final int GL_SAMPLE_COVERAGE = 0x80A0;
    public static final int GL_SAMPLE_COVERAGE_INVERT = 0x80AB;
    public static final int GL_SAMPLE_COVERAGE_VALUE = 0x80AA;
    public static final int GL_SCISSOR_BOX = 0xC10;
    public static final int GL_SCISSOR_TEST = 3089;
    public static final int GL_SHADER_BINARY_FORMATS = 0x8DF8;
    public static final int GL_SHADER_COMPILER = 0x8DFA;
    public static final int GL_SHADER_SOURCE_LENGTH = 35720;
    public static final int GL_SHADER_TYPE = 0x8B4F;
    public static final int GL_SHADING_LANGUAGE_VERSION = 0x8B8C;
    public static final int GL_SHORT = 0x1402;
    public static final int GL_SRC_ALPHA = 770;
    public static final int GL_SRC_ALPHA_SATURATE = 0x308;
    public static final int GL_SRC_COLOR = 0x300;
    public static final int GL_STATIC_DRAW = 35044;
    public static final int GL_STENCIL_ATTACHMENT = 0x8D20;
    public static final int GL_STENCIL_BACK_FAIL = 0x8801;
    public static final int GL_STENCIL_BACK_FUNC = 0x8800;
    public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 0x8802;
    public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 0x8803;
    public static final int GL_STENCIL_BACK_REF = 36003;
    public static final int GL_STENCIL_BACK_VALUE_MASK = 36004;
    public static final int GL_STENCIL_BACK_WRITEMASK = 36005;
    public static final int GL_STENCIL_BITS = 0xD57;
    public static final int GL_STENCIL_BUFFER_BIT = 0x400;
    public static final int GL_STENCIL_CLEAR_VALUE = 0xB91;
    public static final int GL_STENCIL_FAIL = 0xB94;
    public static final int GL_STENCIL_FUNC = 0xB92;
    public static final int GL_STENCIL_INDEX = 6401;
    public static final int GL_STENCIL_INDEX8 = 0x8D48;
    public static final int GL_STENCIL_PASS_DEPTH_FAIL = 0xB95;
    public static final int GL_STENCIL_PASS_DEPTH_PASS = 0xB96;
    public static final int GL_STENCIL_REF = 0xB97;
    public static final int GL_STENCIL_TEST = 0xB90;
    public static final int GL_STENCIL_VALUE_MASK = 0xB93;
    public static final int GL_STENCIL_WRITEMASK = 0xB98;
    public static final int GL_STREAM_DRAW = 35040;
    public static final int GL_SUBPIXEL_BITS = 0xD50;
    public static final int GL_TEXTURE = 5890;
    public static final int GL_TEXTURE0 = 0x84C0;
    public static final int GL_TEXTURE1 = 0x84C1;
    public static final int GL_TEXTURE10 = 0x84CA;
    public static final int GL_TEXTURE11 = 0x84CB;
    public static final int GL_TEXTURE12 = 0x84CC;
    public static final int GL_TEXTURE13 = 0x84CD;
    public static final int GL_TEXTURE14 = 0x84CE;
    public static final int GL_TEXTURE15 = 0x84CF;
    public static final int GL_TEXTURE16 = 34000;
    public static final int GL_TEXTURE17 = 34001;
    public static final int GL_TEXTURE18 = 34002;
    public static final int GL_TEXTURE19 = 34003;
    public static final int GL_TEXTURE2 = 0x84C2;
    public static final int GL_TEXTURE20 = 34004;
    public static final int GL_TEXTURE21 = 34005;
    public static final int GL_TEXTURE22 = 34006;
    public static final int GL_TEXTURE23 = 34007;
    public static final int GL_TEXTURE24 = 34008;
    public static final int GL_TEXTURE25 = 34009;
    public static final int GL_TEXTURE26 = 34010;
    public static final int GL_TEXTURE27 = 34011;
    public static final int GL_TEXTURE28 = 34012;
    public static final int GL_TEXTURE29 = 34013;
    public static final int GL_TEXTURE3 = 0x84C3;
    public static final int GL_TEXTURE30 = 34014;
    public static final int GL_TEXTURE31 = 0x84DF;
    public static final int GL_TEXTURE4 = 0x84C4;
    public static final int GL_TEXTURE5 = 0x84C5;
    public static final int GL_TEXTURE6 = 33990;
    public static final int GL_TEXTURE7 = 0x84C7;
    public static final int GL_TEXTURE8 = 0x84C8;
    public static final int GL_TEXTURE9 = 0x84C9;
    public static final int GL_TEXTURE_2D = 0xDE1;
    public static final int GL_TEXTURE_BINDING_2D = 0x8069;
    public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;
    public static final int GL_TEXTURE_CUBE_MAP = 34067;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073;
    public static final int GL_TEXTURE_MAG_FILTER = 0x2800;
    public static final int GL_TEXTURE_MAX_ANISOTROPY_EXT = 0x84FE;
    public static final int GL_TEXTURE_MIN_FILTER = 0x2801;
    public static final int GL_TEXTURE_WRAP_S = 0x2802;
    public static final int GL_TEXTURE_WRAP_T = 0x2803;
    public static final int GL_TRIANGLES = 4;
    public static final int GL_TRIANGLE_FAN = 6;
    public static final int GL_TRIANGLE_STRIP = 5;
    public static final int GL_TRUE = 1;
    public static final int GL_UNPACK_ALIGNMENT = 0xCF5;
    public static final int GL_UNSIGNED_BYTE = 0x1401;
    public static final int GL_UNSIGNED_INT = 0x1405;
    public static final int GL_UNSIGNED_SHORT = 0x1403;
    public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 0x8033;
    public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 0x8034;
    public static final int GL_UNSIGNED_SHORT_5_6_5 = 0x8363;
    public static final int GL_VALIDATE_STATUS = 0x8B83;
    public static final int GL_VENDOR = 0x1F00;
    public static final int GL_VERSION = 0x1F02;
    public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 0x889F;
    public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 0x8622;
    public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 0x886A;
    public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 0x8645;
    public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 0x8623;
    public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 34340;
    public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 0x8625;
    public static final int GL_VERTEX_PROGRAM_POINT_SIZE = 34370;
    public static final int GL_VERTEX_SHADER = 0x8B31;
    public static final int GL_VIEWPORT = 0xBA2;
    public static final int GL_ZERO;

    void glActiveTexture(int arg1);

    void glAttachShader(int arg1, int arg2);

    void glBindAttribLocation(int arg1, int arg2, String arg3);

    void glBindBuffer(int arg1, int arg2);

    void glBindFramebuffer(int arg1, int arg2);

    void glBindRenderbuffer(int arg1, int arg2);

    void glBindTexture(int arg1, int arg2);

    void glBlendColor(float arg1, float arg2, float arg3, float arg4);

    void glBlendEquation(int arg1);

    void glBlendEquationSeparate(int arg1, int arg2);

    void glBlendFunc(int arg1, int arg2);

    void glBlendFuncSeparate(int arg1, int arg2, int arg3, int arg4);

    void glBufferData(int arg1, int arg2, Buffer arg3, int arg4);

    void glBufferSubData(int arg1, int arg2, int arg3, Buffer arg4);

    int glCheckFramebufferStatus(int arg1);

    void glClear(int arg1);

    void glClearColor(float arg1, float arg2, float arg3, float arg4);

    void glClearDepthf(float arg1);

    void glClearStencil(int arg1);

    void glColorMask(boolean arg1, boolean arg2, boolean arg3, boolean arg4);

    void glCompileShader(int arg1);

    void glCompressedTexImage2D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, Buffer arg8);

    void glCompressedTexSubImage2D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, Buffer arg9);

    void glCopyTexImage2D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8);

    void glCopyTexSubImage2D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8);

    int glCreateProgram();

    int glCreateShader(int arg1);

    void glCullFace(int arg1);

    void glDeleteBuffer(int arg1);

    void glDeleteBuffers(int arg1, IntBuffer arg2);

    void glDeleteFramebuffer(int arg1);

    void glDeleteFramebuffers(int arg1, IntBuffer arg2);

    void glDeleteProgram(int arg1);

    void glDeleteRenderbuffer(int arg1);

    void glDeleteRenderbuffers(int arg1, IntBuffer arg2);

    void glDeleteShader(int arg1);

    void glDeleteTexture(int arg1);

    void glDeleteTextures(int arg1, IntBuffer arg2);

    void glDepthFunc(int arg1);

    void glDepthMask(boolean arg1);

    void glDepthRangef(float arg1, float arg2);

    void glDetachShader(int arg1, int arg2);

    void glDisable(int arg1);

    void glDisableVertexAttribArray(int arg1);

    void glDrawArrays(int arg1, int arg2, int arg3);

    void glDrawElements(int arg1, int arg2, int arg3, int arg4);

    void glDrawElements(int arg1, int arg2, int arg3, Buffer arg4);

    void glEnable(int arg1);

    void glEnableVertexAttribArray(int arg1);

    void glFinish();

    void glFlush();

    void glFramebufferRenderbuffer(int arg1, int arg2, int arg3, int arg4);

    void glFramebufferTexture2D(int arg1, int arg2, int arg3, int arg4, int arg5);

    void glFrontFace(int arg1);

    int glGenBuffer();

    void glGenBuffers(int arg1, IntBuffer arg2);

    int glGenFramebuffer();

    void glGenFramebuffers(int arg1, IntBuffer arg2);

    int glGenRenderbuffer();

    void glGenRenderbuffers(int arg1, IntBuffer arg2);

    int glGenTexture();

    void glGenTextures(int arg1, IntBuffer arg2);

    void glGenerateMipmap(int arg1);

    String glGetActiveAttrib(int arg1, int arg2, IntBuffer arg3, IntBuffer arg4);

    String glGetActiveUniform(int arg1, int arg2, IntBuffer arg3, IntBuffer arg4);

    void glGetAttachedShaders(int arg1, int arg2, Buffer arg3, IntBuffer arg4);

    int glGetAttribLocation(int arg1, String arg2);

    void glGetBooleanv(int arg1, Buffer arg2);

    void glGetBufferParameteriv(int arg1, int arg2, IntBuffer arg3);

    int glGetError();

    void glGetFloatv(int arg1, FloatBuffer arg2);

    void glGetFramebufferAttachmentParameteriv(int arg1, int arg2, int arg3, IntBuffer arg4);

    void glGetIntegerv(int arg1, IntBuffer arg2);

    String glGetProgramInfoLog(int arg1);

    void glGetProgramiv(int arg1, int arg2, IntBuffer arg3);

    void glGetRenderbufferParameteriv(int arg1, int arg2, IntBuffer arg3);

    String glGetShaderInfoLog(int arg1);

    void glGetShaderPrecisionFormat(int arg1, int arg2, IntBuffer arg3, IntBuffer arg4);

    void glGetShaderiv(int arg1, int arg2, IntBuffer arg3);

    String glGetString(int arg1);

    void glGetTexParameterfv(int arg1, int arg2, FloatBuffer arg3);

    void glGetTexParameteriv(int arg1, int arg2, IntBuffer arg3);

    int glGetUniformLocation(int arg1, String arg2);

    void glGetUniformfv(int arg1, int arg2, FloatBuffer arg3);

    void glGetUniformiv(int arg1, int arg2, IntBuffer arg3);

    void glGetVertexAttribPointerv(int arg1, int arg2, Buffer arg3);

    void glGetVertexAttribfv(int arg1, int arg2, FloatBuffer arg3);

    void glGetVertexAttribiv(int arg1, int arg2, IntBuffer arg3);

    void glHint(int arg1, int arg2);

    boolean glIsBuffer(int arg1);

    boolean glIsEnabled(int arg1);

    boolean glIsFramebuffer(int arg1);

    boolean glIsProgram(int arg1);

    boolean glIsRenderbuffer(int arg1);

    boolean glIsShader(int arg1);

    boolean glIsTexture(int arg1);

    void glLineWidth(float arg1);

    void glLinkProgram(int arg1);

    void glPixelStorei(int arg1, int arg2);

    void glPolygonOffset(float arg1, float arg2);

    void glReadPixels(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7);

    void glReleaseShaderCompiler();

    void glRenderbufferStorage(int arg1, int arg2, int arg3, int arg4);

    void glSampleCoverage(float arg1, boolean arg2);

    void glScissor(int arg1, int arg2, int arg3, int arg4);

    void glShaderBinary(int arg1, IntBuffer arg2, int arg3, Buffer arg4, int arg5);

    void glShaderSource(int arg1, String arg2);

    void glStencilFunc(int arg1, int arg2, int arg3);

    void glStencilFuncSeparate(int arg1, int arg2, int arg3, int arg4);

    void glStencilMask(int arg1);

    void glStencilMaskSeparate(int arg1, int arg2);

    void glStencilOp(int arg1, int arg2, int arg3);

    void glStencilOpSeparate(int arg1, int arg2, int arg3, int arg4);

    void glTexImage2D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, Buffer arg9);

    void glTexParameterf(int arg1, int arg2, float arg3);

    void glTexParameterfv(int arg1, int arg2, FloatBuffer arg3);

    void glTexParameteri(int arg1, int arg2, int arg3);

    void glTexParameteriv(int arg1, int arg2, IntBuffer arg3);

    void glTexSubImage2D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, Buffer arg9);

    void glUniform1f(int arg1, float arg2);

    void glUniform1fv(int arg1, int arg2, FloatBuffer arg3);

    void glUniform1fv(int arg1, int arg2, float[] arg3, int arg4);

    void glUniform1i(int arg1, int arg2);

    void glUniform1iv(int arg1, int arg2, IntBuffer arg3);

    void glUniform1iv(int arg1, int arg2, int[] arg3, int arg4);

    void glUniform2f(int arg1, float arg2, float arg3);

    void glUniform2fv(int arg1, int arg2, FloatBuffer arg3);

    void glUniform2fv(int arg1, int arg2, float[] arg3, int arg4);

    void glUniform2i(int arg1, int arg2, int arg3);

    void glUniform2iv(int arg1, int arg2, IntBuffer arg3);

    void glUniform2iv(int arg1, int arg2, int[] arg3, int arg4);

    void glUniform3f(int arg1, float arg2, float arg3, float arg4);

    void glUniform3fv(int arg1, int arg2, FloatBuffer arg3);

    void glUniform3fv(int arg1, int arg2, float[] arg3, int arg4);

    void glUniform3i(int arg1, int arg2, int arg3, int arg4);

    void glUniform3iv(int arg1, int arg2, IntBuffer arg3);

    void glUniform3iv(int arg1, int arg2, int[] arg3, int arg4);

    void glUniform4f(int arg1, float arg2, float arg3, float arg4, float arg5);

    void glUniform4fv(int arg1, int arg2, FloatBuffer arg3);

    void glUniform4fv(int arg1, int arg2, float[] arg3, int arg4);

    void glUniform4i(int arg1, int arg2, int arg3, int arg4, int arg5);

    void glUniform4iv(int arg1, int arg2, IntBuffer arg3);

    void glUniform4iv(int arg1, int arg2, int[] arg3, int arg4);

    void glUniformMatrix2fv(int arg1, int arg2, boolean arg3, FloatBuffer arg4);

    void glUniformMatrix2fv(int arg1, int arg2, boolean arg3, float[] arg4, int arg5);

    void glUniformMatrix3fv(int arg1, int arg2, boolean arg3, FloatBuffer arg4);

    void glUniformMatrix3fv(int arg1, int arg2, boolean arg3, float[] arg4, int arg5);

    void glUniformMatrix4fv(int arg1, int arg2, boolean arg3, FloatBuffer arg4);

    void glUniformMatrix4fv(int arg1, int arg2, boolean arg3, float[] arg4, int arg5);

    void glUseProgram(int arg1);

    void glValidateProgram(int arg1);

    void glVertexAttrib1f(int arg1, float arg2);

    void glVertexAttrib1fv(int arg1, FloatBuffer arg2);

    void glVertexAttrib2f(int arg1, float arg2, float arg3);

    void glVertexAttrib2fv(int arg1, FloatBuffer arg2);

    void glVertexAttrib3f(int arg1, float arg2, float arg3, float arg4);

    void glVertexAttrib3fv(int arg1, FloatBuffer arg2);

    void glVertexAttrib4f(int arg1, float arg2, float arg3, float arg4, float arg5);

    void glVertexAttrib4fv(int arg1, FloatBuffer arg2);

    void glVertexAttribPointer(int arg1, int arg2, int arg3, boolean arg4, int arg5, int arg6);

    void glVertexAttribPointer(int arg1, int arg2, int arg3, boolean arg4, int arg5, Buffer arg6);

    void glViewport(int arg1, int arg2, int arg3, int arg4);
}

