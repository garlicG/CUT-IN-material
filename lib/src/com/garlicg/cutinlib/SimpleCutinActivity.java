package com.garlicg.cutinlib;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.garlicg.cutinlib.util.Toaster;

public class SimpleCutinActivity extends Activity{
	private final SimpleCutinActivity self = this;
	
	private Intent mCutinIntent;
	private ListView mListView;
	protected final static int STATE_VIEW = 0;
	protected final static int STATE_PICK = 1;
	private int mState;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String action = getIntent().getAction();
		if(!TextUtils.isEmpty(action) && action.equals(CutinInfo.ACTION_PICK_CUTIN)){
			mState = STATE_PICK;
		}
		else{
			mState = STATE_VIEW;
		}
		
		setContentView(R.layout.activity_simple_cutin);
		
		mListView =(ListView)findViewById(R.id.__cutin_simple_ListView);
		if(mState == STATE_VIEW){
			mListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
						long arg3) {
					CutinItem item = (CutinItem)arg0.getItemAtPosition(position);
					play(item.serviceClass);
				}
			});
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		setResult(RESULT_CANCELED);
	}
	
	protected int getState(){
		return mState;
	}
	
	protected void setCutinList(ArrayList<CutinItem> list){
		// launch from launcher ,etc
		if(mState == STATE_VIEW){
			ArrayAdapter<CutinItem> adapter = new ArrayAdapter<SimpleCutinActivity.CutinItem>(this, android.R.layout.simple_list_item_1, list);
			mListView.setAdapter(adapter);
		}
		
		// Call from official cut-in app
		else if(mState == STATE_PICK){
			// Set ListView with SingleChoiceMode.
			ArrayAdapter<CutinItem> adapter = new ArrayAdapter<SimpleCutinActivity.CutinItem>(this, android.R.layout.simple_list_item_single_choice, list);
			mListView.setAdapter(adapter);
			mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
			// inflate footer
			ViewStub stub = (ViewStub)findViewById(R.id.__cutin_simple_PickerFrame);
			View bottomFrame = stub.inflate();
			
			// OK button
			View okButton = bottomFrame.findViewById(R.id.__cutin_okButton);
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
			
			// Play button
			View demoButton = bottomFrame.findViewById(R.id.__cutin_demoButton);
			demoButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = mListView.getCheckedItemPosition();
					CutinItem item = (CutinItem)mListView.getItemAtPosition(position);
					if(item == null){
						Toaster.post(self, "No selected item");
						return;
					}
					play(item.serviceClass);
				}
			});
			
			// Cancel button
			View cancel = bottomFrame.findViewById(R.id.__cutin_cancelButton);
			cancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					setResult(RESULT_CANCELED);
					finish();
				}
			});
		}
	}
	
	private void play( Class<? extends CutinService> serviceClass){
		if(mCutinIntent != null){
			if(!mCutinIntent.getComponent().getClassName().equals(serviceClass.getName())){
				stopService(mCutinIntent);
			}
		}
		mCutinIntent = new Intent(self , serviceClass);
		startService(mCutinIntent);
	}
	
	public class CutinItem{
		private Class<? extends CutinService> serviceClass;
		private String cutinName;
		
		/**
		 * Official CUT-IN app uses the serviceClass as identifying for service intent. 
		 * @param serviceClass The class extends CutinService need to definite an action on Manifest.
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
}
