package com.garlicg.sample.cutin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.garlicg.cutinlib.viewsupport.SimpleCutinScreen;

public class CutinBaseV7 extends ActionBarActivity implements SimpleCutinScreen.PickListener{
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
		
		if(mScreen.getState() == SimpleCutinScreen.STATE_PICK){
			mScreen.setListener(this);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem item = menu.findItem(R.id.menu_main_sound);
		item.setIcon(Prefs.getSound(this) ? R.drawable.ic_action_sound : R.drawable.ic_action_sound_off);
		return super.onPrepareOptionsMenu(menu);
	}
	
	protected SimpleCutinScreen getCutinScreen(){
		return mScreen;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
			return true;
		}
		else if(item.getItemId() == R.id.menu_main_sound){
			boolean toggle = !Prefs.getSound(this);
			Prefs.setSound(this, toggle);
			item.setIcon(toggle ? R.drawable.ic_action_sound : R.drawable.ic_action_sound_off);
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
