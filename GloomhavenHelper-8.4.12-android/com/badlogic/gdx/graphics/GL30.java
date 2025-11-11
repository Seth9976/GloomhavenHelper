package com.badlogic.gdx.graphics;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface GL30 extends GL20 {
    public static final int GL_ACTIVE_UNIFORM_BLOCKS = 0x8A36;
    public static final int GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH = 0x8A35;
    public static final int GL_ALREADY_SIGNALED = 0x911A;
    public static final int GL_ANY_SAMPLES_PASSED = 0x8C2F;
    public static final int GL_ANY_SAMPLES_PASSED_CONSERVATIVE = 36202;
    public static final int GL_BLUE = 6405;
    public static final int GL_BUFFER_ACCESS_FLAGS = 0x911F;
    public static final int GL_BUFFER_MAPPED = 35004;
    public static final int GL_BUFFER_MAP_LENGTH = 0x9120;
    public static final int GL_BUFFER_MAP_OFFSET = 0x9121;
    public static final int GL_BUFFER_MAP_POINTER = 35005;
    public static final int GL_COLOR = 0x1800;
    public static final int GL_COLOR_ATTACHMENT1 = 36065;
    public static final int GL_COLOR_ATTACHMENT10 = 36074;
    public static final int GL_COLOR_ATTACHMENT11 = 36075;
    public static final int GL_COLOR_ATTACHMENT12 = 36076;
    public static final int GL_COLOR_ATTACHMENT13 = 36077;
    public static final int GL_COLOR_ATTACHMENT14 = 36078;
    public static final int GL_COLOR_ATTACHMENT15 = 0x8CEF;
    public static final int GL_COLOR_ATTACHMENT2 = 36066;
    public static final int GL_COLOR_ATTACHMENT3 = 36067;
    public static final int GL_COLOR_ATTACHMENT4 = 36068;
    public static final int GL_COLOR_ATTACHMENT5 = 36069;
    public static final int GL_COLOR_ATTACHMENT6 = 36070;
    public static final int GL_COLOR_ATTACHMENT7 = 36071;
    public static final int GL_COLOR_ATTACHMENT8 = 36072;
    public static final int GL_COLOR_ATTACHMENT9 = 36073;
    public static final int GL_COMPARE_REF_TO_TEXTURE = 0x884E;
    public static final int GL_COMPRESSED_R11_EAC = 0x9270;
    public static final int GL_COMPRESSED_RG11_EAC = 37490;
    public static final int GL_COMPRESSED_RGB8_ETC2 = 0x9274;
    public static final int GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 0x9276;
    public static final int GL_COMPRESSED_RGBA8_ETC2_EAC = 0x9278;
    public static final int GL_COMPRESSED_SIGNED_R11_EAC = 0x9271;
    public static final int GL_COMPRESSED_SIGNED_RG11_EAC = 0x9273;
    public static final int GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC = 0x9279;
    public static final int GL_COMPRESSED_SRGB8_ETC2 = 0x9275;
    public static final int GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 0x9277;
    public static final int GL_CONDITION_SATISFIED = 0x911C;
    public static final int GL_COPY_READ_BUFFER = 0x8F36;
    public static final int GL_COPY_READ_BUFFER_BINDING = 0x8F36;
    public static final int GL_COPY_WRITE_BUFFER = 0x8F37;
    public static final int GL_COPY_WRITE_BUFFER_BINDING = 0x8F37;
    public static final int GL_CURRENT_QUERY = 0x8865;
    public static final int GL_DEPTH = 0x1801;
    public static final int GL_DEPTH24_STENCIL8 = 0x88F0;
    public static final int GL_DEPTH32F_STENCIL8 = 36013;
    public static final int GL_DEPTH_COMPONENT24 = 33190;
    public static final int GL_DEPTH_COMPONENT32F = 36012;
    public static final int GL_DEPTH_STENCIL = 0x84F9;
    public static final int GL_DEPTH_STENCIL_ATTACHMENT = 33306;
    public static final int GL_DRAW_BUFFER0 = 0x8825;
    public static final int GL_DRAW_BUFFER1 = 0x8826;
    public static final int GL_DRAW_BUFFER10 = 0x882F;
    public static final int GL_DRAW_BUFFER11 = 0x8830;
    public static final int GL_DRAW_BUFFER12 = 0x8831;
    public static final int GL_DRAW_BUFFER13 = 0x8832;
    public static final int GL_DRAW_BUFFER14 = 0x8833;
    public static final int GL_DRAW_BUFFER15 = 0x8834;
    public static final int GL_DRAW_BUFFER2 = 0x8827;
    public static final int GL_DRAW_BUFFER3 = 0x8828;
    public static final int GL_DRAW_BUFFER4 = 0x8829;
    public static final int GL_DRAW_BUFFER5 = 0x882A;
    public static final int GL_DRAW_BUFFER6 = 0x882B;
    public static final int GL_DRAW_BUFFER7 = 34860;
    public static final int GL_DRAW_BUFFER8 = 0x882D;
    public static final int GL_DRAW_BUFFER9 = 0x882E;
    public static final int GL_DRAW_FRAMEBUFFER = 36009;
    public static final int GL_DRAW_FRAMEBUFFER_BINDING = 36006;
    public static final int GL_DYNAMIC_COPY = 35050;
    public static final int GL_DYNAMIC_READ = 35049;
    public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV = 0x8DAD;
    public static final int GL_FLOAT_MAT2x3 = 0x8B65;
    public static final int GL_FLOAT_MAT2x4 = 0x8B66;
    public static final int GL_FLOAT_MAT3x2 = 0x8B67;
    public static final int GL_FLOAT_MAT3x4 = 0x8B68;
    public static final int GL_FLOAT_MAT4x2 = 0x8B69;
    public static final int GL_FLOAT_MAT4x3 = 35690;
    public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 0x8B8B;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE = 33301;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE = 33300;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 0x8210;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE = 0x8211;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE = 33302;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE = 0x8213;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE = 0x8212;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE = 33303;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 36052;
    public static final int GL_FRAMEBUFFER_DEFAULT = 33304;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE = 0x8D56;
    public static final int GL_FRAMEBUFFER_UNDEFINED = 33305;
    public static final int GL_GREEN = 6404;
    public static final int GL_HALF_FLOAT = 0x140B;
    public static final int GL_INTERLEAVED_ATTRIBS = 35980;
    public static final int GL_INT_2_10_10_10_REV = 0x8D9F;
    public static final int GL_INT_SAMPLER_2D = 0x8DCA;
    public static final int GL_INT_SAMPLER_2D_ARRAY = 0x8DCF;
    public static final int GL_INT_SAMPLER_3D = 0x8DCB;
    public static final int GL_INT_SAMPLER_CUBE = 36300;
    public static final int GL_INVALID_INDEX = -1;
    public static final int GL_MAJOR_VERSION = 33307;
    public static final int GL_MAP_FLUSH_EXPLICIT_BIT = 16;
    public static final int GL_MAP_INVALIDATE_BUFFER_BIT = 8;
    public static final int GL_MAP_INVALIDATE_RANGE_BIT = 4;
    public static final int GL_MAP_READ_BIT = 1;
    public static final int GL_MAP_UNSYNCHRONIZED_BIT = 0x20;
    public static final int GL_MAP_WRITE_BIT = 2;
    public static final int GL_MAX = 0x8008;
    public static final int GL_MAX_3D_TEXTURE_SIZE = 0x8073;
    public static final int GL_MAX_ARRAY_TEXTURE_LAYERS = 0x88FF;
    public static final int GL_MAX_COLOR_ATTACHMENTS = 0x8CDF;
    public static final int GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 0x8A33;
    public static final int GL_MAX_COMBINED_UNIFORM_BLOCKS = 0x8A2E;
    public static final int GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 0x8A31;
    public static final int GL_MAX_DRAW_BUFFERS = 0x8824;
    public static final int GL_MAX_ELEMENTS_INDICES = 33001;
    public static final int GL_MAX_ELEMENTS_VERTICES = 33000;
    public static final int GL_MAX_ELEMENT_INDEX = 36203;
    public static final int GL_MAX_FRAGMENT_INPUT_COMPONENTS = 0x9125;
    public static final int GL_MAX_FRAGMENT_UNIFORM_BLOCKS = 0x8A2D;
    public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 0x8B49;
    public static final int GL_MAX_PROGRAM_TEXEL_OFFSET = 0x8905;
    public static final int GL_MAX_SAMPLES = 0x8D57;
    public static final int GL_MAX_SERVER_WAIT_TIMEOUT = 0x9111;
    public static final int GL_MAX_TEXTURE_LOD_BIAS = 0x84FD;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS = 0x8C8A;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS = 0x8C8B;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS = 0x8C80;
    public static final int GL_MAX_UNIFORM_BLOCK_SIZE = 0x8A30;
    public static final int GL_MAX_UNIFORM_BUFFER_BINDINGS = 0x8A2F;
    public static final int GL_MAX_VARYING_COMPONENTS = 0x8B4B;
    public static final int GL_MAX_VERTEX_OUTPUT_COMPONENTS = 0x9122;
    public static final int GL_MAX_VERTEX_UNIFORM_BLOCKS = 0x8A2B;
    public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS = 0x8B4A;
    public static final int GL_MIN = 0x8007;
    public static final int GL_MINOR_VERSION = 33308;
    public static final int GL_MIN_PROGRAM_TEXEL_OFFSET = 0x8904;
    public static final int GL_NUM_EXTENSIONS = 33309;
    public static final int GL_NUM_PROGRAM_BINARY_FORMATS = 0x87FE;
    public static final int GL_NUM_SAMPLE_COUNTS = 0x9380;
    public static final int GL_OBJECT_TYPE = 0x9112;
    public static final int GL_PACK_ROW_LENGTH = 0xD02;
    public static final int GL_PACK_SKIP_PIXELS = 0xD04;
    public static final int GL_PACK_SKIP_ROWS = 0xD03;
    public static final int GL_PIXEL_PACK_BUFFER = 35051;
    public static final int GL_PIXEL_PACK_BUFFER_BINDING = 35053;
    public static final int GL_PIXEL_UNPACK_BUFFER = 35052;
    public static final int GL_PIXEL_UNPACK_BUFFER_BINDING = 0x88EF;
    public static final int GL_PRIMITIVE_RESTART_FIXED_INDEX = 36201;
    public static final int GL_PROGRAM_BINARY_FORMATS = 0x87FF;
    public static final int GL_PROGRAM_BINARY_LENGTH = 0x8741;
    public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 0x8257;
    public static final int GL_QUERY_RESULT = 0x8866;
    public static final int GL_QUERY_RESULT_AVAILABLE = 0x8867;
    public static final int GL_R11F_G11F_B10F = 0x8C3A;
    public static final int GL_R16F = 0x822D;
    public static final int GL_R16I = 0x8233;
    public static final int GL_R16UI = 0x8234;
    public static final int GL_R32F = 0x822E;
    public static final int GL_R32I = 0x8235;
    public static final int GL_R32UI = 0x8236;
    public static final int GL_R8 = 0x8229;
    public static final int GL_R8I = 0x8231;
    public static final int GL_R8UI = 33330;
    public static final int GL_R8_SNORM = 0x8F94;
    public static final int GL_RASTERIZER_DISCARD = 0x8C89;
    public static final int GL_READ_BUFFER = 0xC02;
    public static final int GL_READ_FRAMEBUFFER = 36008;
    public static final int GL_READ_FRAMEBUFFER_BINDING = 36010;
    public static final int GL_RED = 6403;
    public static final int GL_RED_INTEGER = 0x8D94;
    public static final int GL_RENDERBUFFER_SAMPLES = 36011;
    public static final int GL_RG = 0x8227;
    public static final int GL_RG16F = 0x822F;
    public static final int GL_RG16I = 0x8239;
    public static final int GL_RG16UI = 0x823A;
    public static final int GL_RG32F = 0x8230;
    public static final int GL_RG32I = 0x823B;
    public static final int GL_RG32UI = 33340;
    public static final int GL_RG8 = 0x822B;
    public static final int GL_RG8I = 0x8237;
    public static final int GL_RG8UI = 0x8238;
    public static final int GL_RG8_SNORM = 0x8F95;
    public static final int GL_RGB10_A2 = 0x8059;
    public static final int GL_RGB10_A2UI = 0x906F;
    public static final int GL_RGB16F = 0x881B;
    public static final int GL_RGB16I = 0x8D89;
    public static final int GL_RGB16UI = 0x8D77;
    public static final int GL_RGB32F = 0x8815;
    public static final int GL_RGB32I = 0x8D83;
    public static final int GL_RGB32UI = 36209;
    public static final int GL_RGB8 = 0x8051;
    public static final int GL_RGB8I = 0x8D8F;
    public static final int GL_RGB8UI = 0x8D7D;
    public static final int GL_RGB8_SNORM = 0x8F96;
    public static final int GL_RGB9_E5 = 35901;
    public static final int GL_RGBA16F = 0x881A;
    public static final int GL_RGBA16I = 0x8D88;
    public static final int GL_RGBA16UI = 0x8D76;
    public static final int GL_RGBA32F = 0x8814;
    public static final int GL_RGBA32I = 0x8D82;
    public static final int GL_RGBA32UI = 0x8D70;
    public static final int GL_RGBA8 = 0x8058;
    public static final int GL_RGBA8I = 0x8D8E;
    public static final int GL_RGBA8UI = 36220;
    public static final int GL_RGBA8_SNORM = 0x8F97;
    public static final int GL_RGBA_INTEGER = 0x8D99;
    public static final int GL_RGB_INTEGER = 0x8D98;
    public static final int GL_RG_INTEGER = 33320;
    public static final int GL_SAMPLER_2D_ARRAY = 0x8DC1;
    public static final int GL_SAMPLER_2D_ARRAY_SHADOW = 0x8DC4;
    public static final int GL_SAMPLER_2D_SHADOW = 0x8B62;
    public static final int GL_SAMPLER_3D = 0x8B5F;
    public static final int GL_SAMPLER_BINDING = 35097;
    public static final int GL_SAMPLER_CUBE_SHADOW = 0x8DC5;
    public static final int GL_SEPARATE_ATTRIBS = 0x8C8D;
    public static final int GL_SIGNALED = 0x9119;
    public static final int GL_SIGNED_NORMALIZED = 0x8F9C;
    public static final int GL_SRGB = 0x8C40;
    public static final int GL_SRGB8 = 35905;
    public static final int GL_SRGB8_ALPHA8 = 35907;
    public static final int GL_STATIC_COPY = 35046;
    public static final int GL_STATIC_READ = 35045;
    public static final int GL_STENCIL = 0x1802;
    public static final int GL_STREAM_COPY = 35042;
    public static final int GL_STREAM_READ = 35041;
    public static final int GL_SYNC_CONDITION = 0x9113;
    public static final int GL_SYNC_FENCE = 0x9116;
    public static final int GL_SYNC_FLAGS = 0x9115;
    public static final int GL_SYNC_FLUSH_COMMANDS_BIT = 1;
    public static final int GL_SYNC_GPU_COMMANDS_COMPLETE = 0x9117;
    public static final int GL_SYNC_STATUS = 37140;
    public static final int GL_TEXTURE_2D_ARRAY = 0x8C1A;
    public static final int GL_TEXTURE_3D = 0x806F;
    public static final int GL_TEXTURE_BASE_LEVEL = 33084;
    public static final int GL_TEXTURE_BINDING_2D_ARRAY = 0x8C1D;
    public static final int GL_TEXTURE_BINDING_3D = 0x806A;
    public static final int GL_TEXTURE_COMPARE_FUNC = 0x884D;
    public static final int GL_TEXTURE_COMPARE_MODE = 0x884C;
    public static final int GL_TEXTURE_IMMUTABLE_FORMAT = 0x912F;
    public static final int GL_TEXTURE_IMMUTABLE_LEVELS = 0x82DF;
    public static final int GL_TEXTURE_MAX_LEVEL = 33085;
    public static final int GL_TEXTURE_MAX_LOD = 33083;
    public static final int GL_TEXTURE_MIN_LOD = 33082;
    public static final int GL_TEXTURE_SWIZZLE_A = 0x8E45;
    public static final int GL_TEXTURE_SWIZZLE_B = 36420;
    public static final int GL_TEXTURE_SWIZZLE_G = 0x8E43;
    public static final int GL_TEXTURE_SWIZZLE_R = 0x8E42;
    public static final int GL_TEXTURE_WRAP_R = 0x8072;
    public static final int GL_TIMEOUT_EXPIRED = 0x911B;
    public static final long GL_TIMEOUT_IGNORED = -1L;
    public static final int GL_TRANSFORM_FEEDBACK = 0x8E22;
    public static final int GL_TRANSFORM_FEEDBACK_ACTIVE = 0x8E24;
    public static final int GL_TRANSFORM_FEEDBACK_BINDING = 0x8E25;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER = 0x8C8E;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING = 0x8C8F;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE = 0x8C7F;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE = 0x8C85;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START = 0x8C84;
    public static final int GL_TRANSFORM_FEEDBACK_PAUSED = 0x8E23;
    public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN = 0x8C88;
    public static final int GL_TRANSFORM_FEEDBACK_VARYINGS = 0x8C83;
    public static final int GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH = 0x8C76;
    public static final int GL_UNIFORM_ARRAY_STRIDE = 0x8A3C;
    public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS = 0x8A42;
    public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES = 0x8A43;
    public static final int GL_UNIFORM_BLOCK_BINDING = 0x8A3F;
    public static final int GL_UNIFORM_BLOCK_DATA_SIZE = 0x8A40;
    public static final int GL_UNIFORM_BLOCK_INDEX = 0x8A3A;
    public static final int GL_UNIFORM_BLOCK_NAME_LENGTH = 0x8A41;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 0x8A46;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER = 0x8A44;
    public static final int GL_UNIFORM_BUFFER = 0x8A11;
    public static final int GL_UNIFORM_BUFFER_BINDING = 0x8A28;
    public static final int GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT = 35380;
    public static final int GL_UNIFORM_BUFFER_SIZE = 35370;
    public static final int GL_UNIFORM_BUFFER_START = 0x8A29;
    public static final int GL_UNIFORM_IS_ROW_MAJOR = 35390;
    public static final int GL_UNIFORM_MATRIX_STRIDE = 0x8A3D;
    public static final int GL_UNIFORM_NAME_LENGTH = 0x8A39;
    public static final int GL_UNIFORM_OFFSET = 0x8A3B;
    public static final int GL_UNIFORM_SIZE = 0x8A38;
    public static final int GL_UNIFORM_TYPE = 0x8A37;
    public static final int GL_UNPACK_IMAGE_HEIGHT = 0x806E;
    public static final int GL_UNPACK_ROW_LENGTH = 0xCF2;
    public static final int GL_UNPACK_SKIP_IMAGES = 0x806D;
    public static final int GL_UNPACK_SKIP_PIXELS = 0xCF4;
    public static final int GL_UNPACK_SKIP_ROWS = 0xCF3;
    public static final int GL_UNSIGNALED = 0x9118;
    public static final int GL_UNSIGNED_INT_10F_11F_11F_REV = 0x8C3B;
    public static final int GL_UNSIGNED_INT_24_8 = 0x84FA;
    public static final int GL_UNSIGNED_INT_2_10_10_10_REV = 33640;
    public static final int GL_UNSIGNED_INT_5_9_9_9_REV = 35902;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D = 36306;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY = 0x8DD7;
    public static final int GL_UNSIGNED_INT_SAMPLER_3D = 36307;
    public static final int GL_UNSIGNED_INT_SAMPLER_CUBE = 36308;
    public static final int GL_UNSIGNED_INT_VEC2 = 0x8DC6;
    public static final int GL_UNSIGNED_INT_VEC3 = 0x8DC7;
    public static final int GL_UNSIGNED_INT_VEC4 = 0x8DC8;
    public static final int GL_UNSIGNED_NORMALIZED = 0x8C17;
    public static final int GL_VERTEX_ARRAY_BINDING = 0x85B5;
    public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR = 35070;
    public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER = 0x88FD;
    public static final int GL_WAIT_FAILED = 0x911D;

    void glBeginQuery(int arg1, int arg2);

    void glBeginTransformFeedback(int arg1);

    void glBindBufferBase(int arg1, int arg2, int arg3);

    void glBindBufferRange(int arg1, int arg2, int arg3, int arg4, int arg5);

    void glBindSampler(int arg1, int arg2);

    void glBindTransformFeedback(int arg1, int arg2);

    void glBindVertexArray(int arg1);

    void glBlitFramebuffer(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10);

    void glClearBufferfi(int arg1, int arg2, float arg3, int arg4);

    void glClearBufferfv(int arg1, int arg2, FloatBuffer arg3);

    void glClearBufferiv(int arg1, int arg2, IntBuffer arg3);

    void glClearBufferuiv(int arg1, int arg2, IntBuffer arg3);

    void glCopyBufferSubData(int arg1, int arg2, int arg3, int arg4, int arg5);

    void glCopyTexSubImage3D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9);

    void glDeleteQueries(int arg1, IntBuffer arg2);

    void glDeleteQueries(int arg1, int[] arg2, int arg3);

    void glDeleteSamplers(int arg1, IntBuffer arg2);

    void glDeleteSamplers(int arg1, int[] arg2, int arg3);

    void glDeleteTransformFeedbacks(int arg1, IntBuffer arg2);

    void glDeleteTransformFeedbacks(int arg1, int[] arg2, int arg3);

    void glDeleteVertexArrays(int arg1, IntBuffer arg2);

    void glDeleteVertexArrays(int arg1, int[] arg2, int arg3);

    void glDrawArraysInstanced(int arg1, int arg2, int arg3, int arg4);

    void glDrawBuffers(int arg1, IntBuffer arg2);

    void glDrawElementsInstanced(int arg1, int arg2, int arg3, int arg4, int arg5);

    void glDrawRangeElements(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6);

    void glDrawRangeElements(int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6);

    void glEndQuery(int arg1);

    void glEndTransformFeedback();

    void glFlushMappedBufferRange(int arg1, int arg2, int arg3);

    void glFramebufferTextureLayer(int arg1, int arg2, int arg3, int arg4, int arg5);

    void glGenQueries(int arg1, IntBuffer arg2);

    void glGenQueries(int arg1, int[] arg2, int arg3);

    void glGenSamplers(int arg1, IntBuffer arg2);

    void glGenSamplers(int arg1, int[] arg2, int arg3);

    void glGenTransformFeedbacks(int arg1, IntBuffer arg2);

    void glGenTransformFeedbacks(int arg1, int[] arg2, int arg3);

    void glGenVertexArrays(int arg1, IntBuffer arg2);

    void glGenVertexArrays(int arg1, int[] arg2, int arg3);

    String glGetActiveUniformBlockName(int arg1, int arg2);

    void glGetActiveUniformBlockName(int arg1, int arg2, Buffer arg3, Buffer arg4);

    void glGetActiveUniformBlockiv(int arg1, int arg2, int arg3, IntBuffer arg4);

    void glGetActiveUniformsiv(int arg1, int arg2, IntBuffer arg3, int arg4, IntBuffer arg5);

    void glGetBufferParameteri64v(int arg1, int arg2, LongBuffer arg3);

    Buffer glGetBufferPointerv(int arg1, int arg2);

    int glGetFragDataLocation(int arg1, String arg2);

    void glGetInteger64v(int arg1, LongBuffer arg2);

    void glGetQueryObjectuiv(int arg1, int arg2, IntBuffer arg3);

    void glGetQueryiv(int arg1, int arg2, IntBuffer arg3);

    void glGetSamplerParameterfv(int arg1, int arg2, FloatBuffer arg3);

    void glGetSamplerParameteriv(int arg1, int arg2, IntBuffer arg3);

    String glGetStringi(int arg1, int arg2);

    int glGetUniformBlockIndex(int arg1, String arg2);

    void glGetUniformIndices(int arg1, String[] arg2, IntBuffer arg3);

    void glGetUniformuiv(int arg1, int arg2, IntBuffer arg3);

    void glGetVertexAttribIiv(int arg1, int arg2, IntBuffer arg3);

    void glGetVertexAttribIuiv(int arg1, int arg2, IntBuffer arg3);

    void glInvalidateFramebuffer(int arg1, int arg2, IntBuffer arg3);

    void glInvalidateSubFramebuffer(int arg1, int arg2, IntBuffer arg3, int arg4, int arg5, int arg6, int arg7);

    boolean glIsQuery(int arg1);

    boolean glIsSampler(int arg1);

    boolean glIsTransformFeedback(int arg1);

    boolean glIsVertexArray(int arg1);

    Buffer glMapBufferRange(int arg1, int arg2, int arg3, int arg4);

    void glPauseTransformFeedback();

    void glProgramParameteri(int arg1, int arg2, int arg3);

    void glReadBuffer(int arg1);

    void glRenderbufferStorageMultisample(int arg1, int arg2, int arg3, int arg4, int arg5);

    void glResumeTransformFeedback();

    void glSamplerParameterf(int arg1, int arg2, float arg3);

    void glSamplerParameterfv(int arg1, int arg2, FloatBuffer arg3);

    void glSamplerParameteri(int arg1, int arg2, int arg3);

    void glSamplerParameteriv(int arg1, int arg2, IntBuffer arg3);

    void glTexImage3D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10);

    void glTexImage3D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, Buffer arg10);

    void glTexSubImage3D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11);

    void glTexSubImage3D(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, Buffer arg11);

    void glTransformFeedbackVaryings(int arg1, String[] arg2, int arg3);

    void glUniform1uiv(int arg1, int arg2, IntBuffer arg3);

    void glUniform3uiv(int arg1, int arg2, IntBuffer arg3);

    void glUniform4uiv(int arg1, int arg2, IntBuffer arg3);

    void glUniformBlockBinding(int arg1, int arg2, int arg3);

    void glUniformMatrix2x3fv(int arg1, int arg2, boolean arg3, FloatBuffer arg4);

    void glUniformMatrix2x4fv(int arg1, int arg2, boolean arg3, FloatBuffer arg4);

    void glUniformMatrix3x2fv(int arg1, int arg2, boolean arg3, FloatBuffer arg4);

    void glUniformMatrix3x4fv(int arg1, int arg2, boolean arg3, FloatBuffer arg4);

    void glUniformMatrix4x2fv(int arg1, int arg2, boolean arg3, FloatBuffer arg4);

    void glUniformMatrix4x3fv(int arg1, int arg2, boolean arg3, FloatBuffer arg4);

    boolean glUnmapBuffer(int arg1);

    void glVertexAttribDivisor(int arg1, int arg2);

    void glVertexAttribI4i(int arg1, int arg2, int arg3, int arg4, int arg5);

    void glVertexAttribI4ui(int arg1, int arg2, int arg3, int arg4, int arg5);

    void glVertexAttribIPointer(int arg1, int arg2, int arg3, int arg4, int arg5);

    @Override  // com.badlogic.gdx.graphics.GL20
    @Deprecated
    void glVertexAttribPointer(int arg1, int arg2, int arg3, boolean arg4, int arg5, Buffer arg6);
}

