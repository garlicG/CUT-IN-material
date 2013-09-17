package com.garlicg.sample.cutin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.garlicg.cutinlib.util.SimpleCutinScreen;

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
	
	protected SimpleCutinScreen getCutinScreen(){
		return mScreen;
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
