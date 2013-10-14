package cutin.sample;

import java.util.ArrayList;

import cutin.sample.animation.AnimationsActivity;
import cutin.sample.animation.GLSurfaceViewCutIn;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * SimplePickedActivity
 * - SimpleCutIn
 * 
 * IconicActivity
 *  - IconicCutIn
 * 
 * ParamActivity 
 * - ParamCutIn
 * 
 * PreferenceActivity
 *  - PreferenceCutIn
 *  - CustomDemo
 * 
 * AnimationsActivity
 *  - AnimCutInanim1
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView listView = new ListView(this);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Data data = (Data)arg0.getItemAtPosition(arg2);
				Intent intent = new Intent(getApplicationContext(), data.activity);
				startActivity(intent);
			}
		});
		
		ArrayList<Data> list = new ArrayList<Data>();
		list.add(new Data(SimplePickedActivity.class));
		list.add(new Data(IconicPickedActivity.class));
		list.add(new Data(CutInIdActivity.class));
		list.add(new Data(PreferenceActivity.class));
		list.add(new Data(CustomDemoActivity.class));
		list.add(new Data(AnimationsActivity.class));
		ArrayAdapter<Data> adapter = new ArrayAdapter<MainActivity.Data>(this, android.R.layout.simple_list_item_1 , list);
		listView.setAdapter(adapter);
		
		
		setContentView(listView);
	}
	
	private class Data{
		private String name = "";
		private Class<?extends Activity> activity;
		private Data(Class<?extends Activity> activity){
			this.activity = activity;
			ComponentName cn = new ComponentName(getApplicationContext(), activity);
			try {
				name = getPackageManager().getActivityInfo(cn, PackageManager.GET_META_DATA).loadLabel(getPackageManager()).toString();
			} catch (NameNotFoundException e) {
				Log.e("MainActivity", e.toString());
			}
		}
		@Override
		public String toString() {
			return name;
		}
	}
}
