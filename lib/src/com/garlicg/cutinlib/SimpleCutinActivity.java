package com.garlicg.cutinlib;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.garlicg.cutinlib.util.Toaster;

public class SimpleCutinActivity extends Activity{
	private final SimpleCutinActivity self = this;
	
	private Intent mCutinIntent;
	private ListView mListView;
	
	public class CutinItem{
		private Class<? extends CutinService> serviceClass;
		private String cutinName;
		
		/**
		 * Official CUT-IN app uses the serviceClass as identifying for service intent. 
		 * @param serviceClass The class extends CutinService. also need to  definite class as name of service.
		 * @param cutinName 
		 */
		public CutinItem(Class<? extends CutinService> serviceClass, String cutinName){
			this.serviceClass = serviceClass;
			this.cutinName = cutinName;
		}
		@Override
		public String toString() {
			return cutinName;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_cutin);
		
		mListView =(ListView)findViewById(R.id.__cutin_simple_ListView);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				CutinItem item = (CutinItem)arg0.getItemAtPosition(position);
				
				if(mCutinIntent != null){
					if(!mCutinIntent.getComponent().getClassName().equals(item.serviceClass.getName())){
						stopService(mCutinIntent);
					}
				}
				mCutinIntent = new Intent(self , item.serviceClass);
				startService(mCutinIntent);
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			setResult(RESULT_CANCELED);
			finish();
			return true;
		}
		else{
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		setResult(RESULT_CANCELED);
	}
	
	protected void setCutinList(ArrayList<CutinItem> list){
		String action = getIntent().getAction();
		
		// Call from official cut-in app
		if(!TextUtils.isEmpty(action) && action.equals(CutinInfo.ACTION_PICK_CUTIN)){
			
			getActionBar().setDisplayHomeAsUpEnabled(true);
			
			// Set ListView with SingleChoiceMode.
			ArrayAdapter<CutinItem>adapter = new ArrayAdapter<CutinItem>(this, android.R.layout.simple_list_item_single_choice);
			adapter.addAll(list);
			mListView.setAdapter(adapter);
			mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
			// inflate footer
			ViewStub stub = (ViewStub)findViewById(R.id.__cutin_simple_PickerFrame);
			View bottomFrame = stub.inflate();
			
			// OK button
			Button okButton = (Button)bottomFrame.findViewById(R.id.__cutin_okButton);
			okButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = mListView.getCheckedItemPosition();
					CutinItem item = (CutinItem)mListView.getItemAtPosition(position);
					if(item != null){
						Intent intent = new Intent();
						intent.putExtra(CutinInfo.DATA_ACTION_NAME, item.serviceClass.getName());
						intent.putExtra(CutinInfo.DATA_CUTIN_NAME, item.cutinName);
						setResult(RESULT_OK, intent);
						finish();
					}
					else {
						Toaster.post(self, "No selected item");
					}
				}
			});
			
			// Cancel button
			Button cancel = (Button)bottomFrame.findViewById(R.id.__cutin_cancelButton);
			cancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					setResult(RESULT_CANCELED);
					finish();
				}
			});
		}
		
		// launch from launcher ,etc
		else{
			ArrayAdapter<CutinItem>adapter = new ArrayAdapter<CutinItem>(this, android.R.layout.simple_list_item_1);
			mListView.setAdapter(adapter);
			adapter.addAll(list);
		}
	}

}
