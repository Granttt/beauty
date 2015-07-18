package beauty.beautydemo.custview.imageprocessing.filter.blend;import beauty.beautydemo.custview.imageprocessing.filter.MultiInputFilter;/** * Applies a saturation blend of two images * @author Chris Batt */public class ScreenBlendFilter extends MultiInputFilter {	public ScreenBlendFilter() {		super(2);	}		@Override	protected String getFragmentShader() {		return 				 "precision mediump float;\n" 				+"uniform sampler2D "+UNIFORM_TEXTURE0+";\n"  				+"uniform sampler2D "+UNIFORM_TEXTUREBASE+1+";\n" 				+"varying vec2 "+VARYING_TEXCOORD+";\n"							  		+"void main(){\n"		  		+"   vec4 color1 = texture2D("+UNIFORM_TEXTURE0+","+VARYING_TEXCOORD+");\n"		  		+"   vec4 color2 = texture2D("+UNIFORM_TEXTUREBASE+1+","+VARYING_TEXCOORD+");\n"		  		+"   vec4 whiteColor = vec4(1.0);\n"		  		+"   gl_FragColor = whiteColor - ((whiteColor - color2) * (whiteColor - color1));\n"		  		+"}\n";		}}