package com.garlicg.sample.cutin;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.SimpleCutinScreen;

	public class MainActivity extends ActionBarActivity implements SimpleCutinScreen.PickListener {
		
		private SimpleCutinScreen mScreen;
		@Override
		protected void onResume() {
			super.onResume();
			mScreen.resume();
		}
		
		@Override
		protected void onPause() {
			super.onPause();
			mScreen.pause();
		}
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			mScreen = new SimpleCutinScreen(this , getIntent());
			setContentView(mScreen.getView());
			
			// create cutin list
			ArrayList<CutinItem> items = new ArrayList<CutinItem>();
			items.add(new CutinItem(GarlinTornado.class,getString(R.string.garlin_tornado)));
			items.add(new CutinItem(GarlinMirage.class, getString(R.string.garlin_mirage)));
			items.add(new CutinItem(GarlinParade.class, getString(R.string.garlin_parade)));
			mScreen.setCutinList(items);
			
			// pick mode
			if(mScreen.getState() == SimpleCutinScreen.STATE_PICK){
				mScreen.setListener(this);
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			}
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			if(item.getItemId() == android.R.id.home){
				finish();
				return true;
			}
			return super.onOptionsItemSelected(item);
		}

		@Override
		public void ok(Intent intent) {
			setResult(RESULT_OK, intent);
			finish();
		}

		@Override
		public void cancel() {
			setResult(RESULT_CANCELED);
			finish();
		}
}
