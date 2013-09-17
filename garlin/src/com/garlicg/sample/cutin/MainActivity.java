package com.garlicg.sample.cutin;

import java.util.ArrayList;

import android.os.Bundle;

import com.garlicg.cutinlib.CutinItem;


	public class MainActivity extends CutinBaseV7 {
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			// create cutin list
			ArrayList<CutinItem> items = new ArrayList<CutinItem>();
			items.add(new CutinItem(GarlinTornado.class,getString(R.string.garlin_tornado)));
			items.add(new CutinItem(GarlinMirage.class, getString(R.string.garlin_mirage)));
			items.add(new CutinItem(GarlinParade.class, getString(R.string.garlin_parade)));
			
			getCutinScreen().setCutinList(items);
		}
}
