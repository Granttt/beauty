package beauty.beautydemo.custview.imageprocessing.filter.colour;import android.content.Context;import beauty.beautydemo.R;/** *  A photo filter based on a Photoshop action by Amatorka: http://amatorka.deviantart.com/art/Amatorka-Action-2-121069631 .  * @author Chris Batt */public class AmatorkaFilter extends LookupFilter {	public AmatorkaFilter(Context context) {		super(context, R.drawable.lookup_amatorka);	}}