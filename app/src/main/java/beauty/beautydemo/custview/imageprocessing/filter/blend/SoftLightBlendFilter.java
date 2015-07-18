package beauty.beautydemo.custview.imageprocessing.filter.blend;import beauty.beautydemo.custview.imageprocessing.filter.MultiInputFilter;/** * Applies a soft light blend of two images * @author Chris Batt */public class SoftLightBlendFilter extends MultiInputFilter {	public SoftLightBlendFilter() {		super(2);	}		@Override	protected String getFragmentShader() {		return 				 "precision mediump float;\n" 				+"uniform sampler2D "+UNIFORM_TEXTURE0+";\n"  				+"uniform sampler2D "+UNIFORM_TEXTUREBASE+1+";\n" 				+"varying vec2 "+VARYING_TEXCOORD+";\n"							  		+"void main(){\n"		  		+"   vec4 color1 = texture2D("+UNIFORM_TEXTURE0+","+VARYING_TEXCOORD+");\n"		  		+"   vec4 color2 = texture2D("+UNIFORM_TEXTUREBASE+1+","+VARYING_TEXCOORD+");\n"	   			+"   gl_FragColor = color1 * (color2.a * (color1 / color1.a) + (2.0 * color2 * (1.0 - (color1 / color1.a)))) + color2 * (1.0 - color1.a) + color1 * (1.0 - color2.a);\n"		  		+"}\n";		}}