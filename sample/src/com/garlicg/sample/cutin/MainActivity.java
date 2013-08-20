package com.garlicg.sample.cutin;

import java.util.ArrayList;

import android.os.Bundle;

import com.garlicg.cutinlib.SimpleCutinActivity;

public class MainActivity extends SimpleCutinActivity {
	
	/**
	 * onCreate
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// create CUT-IN list.
		ArrayList<CutinItem> items = new ArrayList<MainActivity.CutinItem>();
		items.add(new CutinItem(GarlinTornado.class,getString(R.string.garlin_tornado)));
		items.add(new CutinItem(GarlinMirage.class, getString(R.string.garlin_mirage)));
		items.add(new CutinItem(GarlinParade.class, getString(R.string.garlin_parade)));
		setCutinList(items);
	}
}
