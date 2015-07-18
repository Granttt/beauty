package beauty.beautydemo.custview.imageprocessing.filter.colour;import beauty.beautydemo.custview.imageprocessing.filter.BasicFilter;import android.opengl.GLES20;/** * A hue adjusting filter implementation of BasicFilter. * This filter rotates the hue, in radians, by a set amount. * Value should be in [0,6.28]; however, all values should produce acceptable results. * @author Chris Batt */public class HueFilter extends BasicFilter {	private static final String UNIFORM_HUEADJUST = "u_HueAdjust";	private float hueAdjust;	private int hueAdjustHandle;		/**	 * Creates a HueFilter that adjusts the hue of input image by a given number of radians.	 * @param hueAdjust	 * The amount the hue should be adjusted in radians.	 */	public HueFilter(float hueAdjust) {		this.hueAdjust = hueAdjust;	}		// Adapted from http://stackoverflow.com/questions/9234724/how-to-change-hue-of-a-texture-with-glsl - see for code and discussion	@Override	protected String getFragmentShader() {		return 				 "precision mediump float;\n" 				+"uniform sampler2D "+UNIFORM_TEXTURE0+";\n"  				+"varying vec2 "+VARYING_TEXCOORD+";\n"					+"uniform float "+UNIFORM_HUEADJUST+";\n"				+"const vec4  kRGBToYPrime = vec4 (0.299, 0.587, 0.114, 0.0);\n"				+"const vec4  kRGBToI = vec4 (0.595716, -0.274453, -0.321263, 0.0);\n"				+"const vec4  kRGBToQ = vec4 (0.211456, -0.522591, 0.31135, 0.0);\n"				+"const vec4  kYIQToR = vec4 (1.0, 0.9563, 0.6210, 0.0);\n"				+"const vec4  kYIQToG = vec4 (1.0, -0.2721, -0.6474, 0.0);\n"				+"const vec4  kYIQToB = vec4 (1.0, -1.1070, 1.7046, 0.0);\n"						  		+"void main() {\n"		  		+"   vec4 color = texture2D("+UNIFORM_TEXTURE0+","+VARYING_TEXCOORD+");\n"		  		+"   float YPrime = dot(color, kRGBToYPrime);\n"		  		+"   float I = dot(color, kRGBToI);\n"		  		+"   float Q = dot(color, kRGBToQ);\n"		  		+"   float hue = atan(Q, I);\n"				+"   float chroma = sqrt(I * I + Q * Q);\n"		  		+"   hue += (-"+UNIFORM_HUEADJUST+");\n"		  		+"   Q = chroma * sin(hue);"				+"   I = chroma * cos(hue);" 		  		+"   vec4 yIQ = vec4(YPrime, I, Q, 0.0);\n"		  		+"   color.r = dot(yIQ, kYIQToR);\n"		  		+"   color.g = dot(yIQ, kYIQToG);\n"		  		+"   color.b = dot(yIQ, kYIQToB);\n"				+"   gl_FragColor = color;\n"		  		+"}\n";	}		@Override	protected void initShaderHandles() {		super.initShaderHandles();		hueAdjustHandle = GLES20.glGetUniformLocation(programHandle, UNIFORM_HUEADJUST);	} 		@Override	protected void passShaderValues() {		super.passShaderValues();		GLES20.glUniform1f(hueAdjustHandle, hueAdjust);	}}