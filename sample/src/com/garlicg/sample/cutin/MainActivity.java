package com.garlicg.sample.cutin;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.garlicg.cutinlib.SimpleCutinScreen;
import com.garlicg.cutinlib.SimpleCutinScreen.CutinItem;
import com.garlicg.cutinlib.SimpleCutinScreen.PickListener;

	public class MainActivity extends ActionBarActivity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// SimpleCutinScreen read the intent for switching state PICK or VIEW.
		SimpleCutinScreen screen = new SimpleCutinScreen(this , getIntent());
		setContentView(screen.getView());
		
		// create CUT-IN list.
		ArrayList<CutinItem> items = new ArrayList<CutinItem>();
		items.add(new CutinItem(GarlinTornado.class,getString(R.string.garlin_tornado)));
		items.add(new CutinItem(GarlinMirage.class, getString(R.string.garlin_mirage)));
		items.add(new CutinItem(GarlinParade.class, getString(R.string.garlin_parade)));
		screen.setCutinList(items , getIntent());
		
		// when call from official cut-in app , screen layout state is STATE_PICK , other STATE_VIEW.
		if(screen.getState() == SimpleCutinScreen.STATE_PICK){
			screen.setListener(new PickListener() {
				@Override
				public void ok(Intent intent) {
					setResult(Activity.RESULT_OK, intent);
					finish();
				}
				@Override
				public void cancel() {
					finish();
				}
			});
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
}
