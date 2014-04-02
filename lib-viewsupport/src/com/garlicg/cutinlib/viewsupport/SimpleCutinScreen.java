package com.garlicg.cutinlib.viewsupport;


import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.CutinManagerUtils;
import com.garlicg.cutinlib.CutinService;
import com.garlicg.cutinlib.Demo;

public class SimpleCutinScreen{
	public final static int STATE_VIEW = 0;
	public final static int STATE_PICK = 1;
	private int mState = STATE_VIEW;
	private View mViewParent;
	private PickListener mListener;
	private Demo mDemo;
	private View mGetView;
	private ListView mListView;
	private Context mContext;
	
	public SimpleCutinScreen(Context context , Intent intent){
		mContext = context;
		mDemo = new Demo(context);
		
		if(CutinManagerUtils.isCalledFromCutinManager(intent)){
			// Call from official cut-in app
			mState = STATE_PICK;
		}
		else{
			mState = STATE_VIEW;
		}
	}
	
	private View newPaddingView(Context context){
		View padding = new View(context);
		ListView.LayoutParams padding8 = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,dpToPx(context.getResources(),8));
		padding.setLayoutParams(padding8);
		return padding;
	}
	
	private int dpToPx(Resources res , int dp){
    	return (int)(res.getDisplayMetrics().density * dp + 0.5f);
	}
	
	public View getView(){
		mViewParent = LayoutInflater.from(mContext).inflate(R.layout.cutin_simple_screen, null);
		// setupListView
		
		mListView = (ListView)mViewParent.findViewById(R.id.__cutin_simple_ListView);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Object item = arg0.getItemAtPosition(arg2);
				if(item instanceof CutinItem){
					CutinItem ci = (CutinItem)item;
					mDemo.play(ci.serviceClass , ci.cutinId);
				}
				else if(arg2 == 0 && mGetView != null){
					Intent intent = new Intent(
							Intent.ACTION_VIEW,
							Uri.parse("market://details?id=com.garlicg.cutin"));
					mContext.startActivity(intent);
				}
			}
		});
		
		if(CutinManagerUtils.existManager(mContext)){
			mListView.addHeaderView(newPaddingView(mContext));
		}
		else{
			mGetView = LayoutInflater.from(mContext).inflate(R.layout.cutin_get_manager,null);
			mListView.addHeaderView(mGetView);
		}
		mListView.addFooterView(newPaddingView(mContext));
		return mViewParent;
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
	
	public void resume(){
		// remove view after get the manager app from this.
		if(CutinManagerUtils.existManager(mContext) && mGetView != null){
			mListView.removeHeaderView(mGetView);
			mGetView = null;
		}
	}
	
	public void pause(){
		mDemo.forceStop();
	}
	
	public void setCutinList(ArrayList<CutinItem> list){
		mListView = (ListView)mViewParent.findViewById(R.id.__cutin_simple_ListView);
		
		// launched from launcher ,etc
		if(mState == STATE_VIEW){
			SimpleCutinAdapter adapter = new SimpleCutinAdapter(mViewParent.getContext(), R.layout.cutin_list_item_1,list);
			mListView.setAdapter(adapter);
		}
		
		// launched from manage app
		else if(mState == STATE_PICK){
			// Set ListView with SingleChoiceMode.
			SimpleCutinAdapter adapter = new SimpleCutinAdapter(mViewParent.getContext(), R.layout.cutin_list_item_single_choice,list);
			mListView.setAdapter(adapter);
			mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
			// inflate footer
			ViewStub stub = (ViewStub)mViewParent.findViewById(R.id.__cutin_simple_PickerFrame);
			View bottomFrame = stub.inflate();
			
			// OK button
			View okButton = bottomFrame.findViewById(R.id.__cutin_okButton);
			okButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(mListener != null){
						int position = mListView.getCheckedItemPosition();
						Object item = mListView.getItemAtPosition(position);
						if(item != null && item instanceof CutinItem){
							CutinItem ci = (CutinItem)item;
							mListener.ok(CutinManagerUtils.buildResultIntent(ci));
						}
						else {
							// no  selected item
						}
					}
				}
			});
			
			// Cancel button
			View cancel = bottomFrame.findViewById(R.id.__cutin_cancelButton);
			cancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(mListener != null){
						mListener.cancel();
					}
				}
			});
		}
	}
	
	
	public static class Category extends CutinItem{
		public Category(String label){
			super(null, label);
		}
	}
	
	private class SimpleCutinAdapter extends ArrayAdapter<CutinItem>{
		private Drawable[] mDrawables;
		private final int RESOURCE_ID;
		private final int RESOURCE_CATEGORY_ID;
		LayoutInflater mInflater;
		
		public SimpleCutinAdapter(Context context, int resource,
				List<CutinItem> objects) {
			super(context, resource, android.R.id.text1,objects);
			RESOURCE_ID = resource;
			RESOURCE_CATEGORY_ID = R.layout.cutin_list_item_category;
			mInflater = LayoutInflater.from(context);
			if(objects != null){
				mDrawables = new Drawable[objects.size()];
				int size = mDrawables.length;
				PackageManager pm = context.getPackageManager();
				for(int i = 0 ; i < size ; i++){
					mDrawables[i] = getServiceIcon(objects.get(i).serviceClass, pm);
				}
			}
		}
		
		private Drawable getServiceIcon(Class<? extends CutinService> serviceClass ,PackageManager pm){
			Drawable icon = null;
			if(serviceClass != null){
				try {
					Resources res = getContext().getResources();
					ServiceInfo si = pm.getServiceInfo(new ComponentName(getContext(), serviceClass), 0);
					if(si.icon != 0){
						icon = res.getDrawable(si.icon);
						int bond = (int)(res.getDisplayMetrics().density * 48 + 0.5f);
						icon.setBounds(0, 0, bond,bond );
					}
				} catch (NameNotFoundException e) {
				} catch (Resources.NotFoundException e) {
				}
				
			}
			return icon;
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			CutinItem item = getItem(position);
			if(item instanceof Category){
				view = mInflater.inflate(RESOURCE_CATEGORY_ID, null);
				TextView text = (TextView)view.findViewById(android.R.id.text1);
				text.setText(item.cutinName);
			}
			else{
				view = mInflater.inflate(RESOURCE_ID, null);
				TextView text = (TextView)view.findViewById(android.R.id.text1);
				text.setText(item.cutinName);
				
				if(mDrawables[position] != null){
					text.setCompoundDrawables(mDrawables[position],null, null, null);
				}
				else{
					text.setCompoundDrawables(null,null, null, null);
				}
			}
			
			return view;
		}
		
		@Override
		public boolean isEnabled(int position) {
			Object obj = getItem(position);
			return !(obj instanceof Category);
		}
	}
}
