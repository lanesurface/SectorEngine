/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.opengl;

import org.lwjgl.*;
import java.nio.*;

public final class NVBlendEquationAdvanced {

	/**
	 *  Accepted by the &lt;cap&gt; parameter of Disable, Enable, and IsEnabled, and by
	 *  the &lt;pname&gt; parameter of GetIntegerv, GetBooleanv, GetFloatv, GetDoublev
	 *  and GetInteger64v:
	 */
	public static final int GL_BLEND_ADVANCED_COHERENT_NV = 0x9285;

	/**
	 *  Accepted by the &lt;pname&gt; parameter of BlendParameteriNV, GetBooleanv,
	 *  GetIntegerv, GetInteger64v, GetFloatv, and GetDoublev:
	 */
	public static final int GL_BLEND_PREMULTIPLIED_SRC_NV = 0x9280,
		GL_BLEND_OVERLAP_NV = 0x9281;

	/**
	 *  Accepted by the &lt;value&gt; parameter of BlendParameteriNV when &lt;pname&gt; is
	 *  BLEND_OVERLAP_NV:
	 */
	public static final int GL_UNCORRELATED_NV = 0x9282,
		GL_DISJOINT_NV = 0x9283,
		GL_CONJOINT_NV = 0x9284;

	/**
	 * Accepted by the &lt;mode&gt; parameter of BlendEquation and BlendEquationi: 
	 */
	public static final int GL_SRC_NV = 0x9286,
		GL_DST_NV = 0x9287,
		GL_SRC_OVER_NV = 0x9288,
		GL_DST_OVER_NV = 0x9289,
		GL_SRC_IN_NV = 0x928A,
		GL_DST_IN_NV = 0x928B,
		GL_SRC_OUT_NV = 0x928C,
		GL_DST_OUT_NV = 0x928D,
		GL_SRC_ATOP_NV = 0x928E,
		GL_DST_ATOP_NV = 0x928F,
		GL_MULTIPLY_NV = 0x9294,
		GL_SCREEN_NV = 0x9295,
		GL_OVERLAY_NV = 0x9296,
		GL_DARKEN_NV = 0x9297,
		GL_LIGHTEN_NV = 0x9298,
		GL_COLORDODGE_NV = 0x9299,
		GL_COLORBURN_NV = 0x929A,
		GL_HARDLIGHT_NV = 0x929B,
		GL_SOFTLIGHT_NV = 0x929C,
		GL_DIFFERENCE_NV = 0x929E,
		GL_EXCLUSION_NV = 0x92A0,
		GL_INVERT_RGB_NV = 0x92A3,
		GL_LINEARDODGE_NV = 0x92A4,
		GL_LINEARBURN_NV = 0x92A5,
		GL_VIVIDLIGHT_NV = 0x92A6,
		GL_LINEARLIGHT_NV = 0x92A7,
		GL_PINLIGHT_NV = 0x92A8,
		GL_HARDMIX_NV = 0x92A9,
		GL_HSL_HUE_NV = 0x92AD,
		GL_HSL_SATURATION_NV = 0x92AE,
		GL_HSL_COLOR_NV = 0x92AF,
		GL_HSL_LUMINOSITY_NV = 0x92B0,
		GL_PLUS_NV = 0x9291,
		GL_PLUS_CLAMPED_NV = 0x92B1,
		GL_PLUS_CLAMPED_ALPHA_NV = 0x92B2,
		GL_PLUS_DARKER_NV = 0x9292,
		GL_MINUS_NV = 0x929F,
		GL_MINUS_CLAMPED_NV = 0x92B3,
		GL_CONTRAST_NV = 0x92A1,
		GL_INVERT_OVG_NV = 0x92B4;

	private NVBlendEquationAdvanced() {}

	public static void glBlendParameteriNV(int pname, int value) {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glBlendParameteriNV;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendParameteriNV(pname, value, function_pointer);
	}
	static native void nglBlendParameteriNV(int pname, int value, long function_pointer);

	public static void glBlendBarrierNV() {
		ContextCapabilities caps = GLContext.getCapabilities();
		long function_pointer = caps.glBlendBarrierNV;
		BufferChecks.checkFunctionAddress(function_pointer);
		nglBlendBarrierNV(function_pointer);
	}
	static native void nglBlendBarrierNV(long function_pointer);
}