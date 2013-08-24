package com.garlicg.cutinlib;


import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.garlicg.cutinlib.util.Toaster;

public class SimpleCutinScreen{
	public final static int STATE_VIEW = 0;
	public final static int STATE_PICK = 1;
	private int mState = STATE_VIEW;
	private View mViewParent;
	private PickListener mListener;
	private Context mContext;
	Intent mCutinIntent;
	
	public SimpleCutinScreen(Context context , Intent intent){
		mContext = context;
		mViewParent = LayoutInflater.from(context).inflate(R.layout.activity_simple_cutin, null);
		String action = intent.getAction();
		if(!TextUtils.isEmpty(action) && action.equals(CutinInfo.ACTION_PICK_CUTIN)){
			// Call from official cut-in app
			mState = STATE_PICK;
		}
		else{
			mState = STATE_VIEW;
		}
	}
	
	public View getView(){
		return mViewParent;
	}
	
	public void loadIntent(Intent intent){
	}
	
	public int getState(){
		return mState;
	}
	
	public interface PickListener{
		public void ok(Intent intent);
		public void cancel();
	}
	
	public void setListener(PickListener listener){
		mListener = listener;
	}
	
	public void setCutinList(ArrayList<CutinItem> list , Intent intent){
		final ListView listView = (ListView)mViewParent.findViewById(R.id.__cutin_simple_ListView);

		// launched from launcher ,etc
		if(mState == STATE_VIEW){
			ArrayAdapter<CutinItem> adapter = new ArrayAdapter<SimpleCutinScreen.CutinItem>(mViewParent.getContext(), android.R.layout.simple_list_item_1, list);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					CutinItem item = (CutinItem)arg0.getItemAtPosition(arg2);
					play(item.serviceClass);
				}
			});
		}
		
		else if(mState == STATE_PICK){
			// Set ListView with SingleChoiceMode.
			ArrayAdapter<CutinItem> adapter = new ArrayAdapter<SimpleCutinScreen.CutinItem>(mViewParent.getContext(), android.R.layout.simple_list_item_single_choice, list);
			listView.setAdapter(adapter);
			listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
			// inflate footer
			ViewStub stub = (ViewStub)mViewParent.findViewById(R.id.__cutin_simple_PickerFrame);
			View bottomFrame = stub.inflate();
			
			// OK button
			View okButton = bottomFrame.findViewById(R.id.__cutin_okButton);
			okButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = listView.getCheckedItemPosition();
					CutinItem item = (CutinItem)listView.getItemAtPosition(position);
					if(item != null){
						Intent intent = new Intent();
						intent.putExtra(CutinInfo.DATA_ACTION_NAME, item.serviceClass.getName());
						intent.putExtra(CutinInfo.DATA_CUTIN_NAME, item.cutinName);
						mListener.ok(intent);
					}
					else {
						Toaster.post(v.getContext(), "No selected item");
					}
				}
			});
			
			// Play button
			View demoButton = bottomFrame.findViewById(R.id.__cutin_demoButton);
			demoButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = listView.getCheckedItemPosition();
					CutinItem item = (CutinItem)listView.getItemAtPosition(position);
					if(item == null){
						Toaster.post(v.getContext(), "No selected item");
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
					mListener.cancel();
				}
			});
		}
	}
	
	private void play( Class<? extends CutinService> serviceClass){
		if(mCutinIntent != null){
			if(!mCutinIntent.getComponent().getClassName().equals(serviceClass.getName())){
				mContext.stopService(mCutinIntent);
			}
		}
		mCutinIntent = new Intent(mContext , serviceClass);
		mContext.startService(mCutinIntent);
	}
	
	public static class CutinItem{
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
