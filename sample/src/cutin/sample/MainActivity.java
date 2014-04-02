package cutin.sample;

import java.util.ArrayList;

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
import android.widget.Button;
import android.widget.ListView;

import com.garlicg.cutinlib.CutinManagerUtils;

import cutin.sample.animation.DemosActivity;

/**
 * Demos
 * 
 * InAppSettings
 * InAppSettings with CutinId
 * InAppSettings with Trigger
 * InAppSettings with Notification
 * InAppSettings with Icon
 * 
 * PickedSettings
 */
public class MainActivity extends Activity {
	private Activity self = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView listView = new ListView(this);
		
		// Need to download CutInManager for interactive functions.
		if(!CutinManagerUtils.existManager(this)){
			Button getCutinManager = new Button(this);
			getCutinManager.setText(R.string.get_on_google_play);
			getCutinManager.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CutinManagerUtils.startActivityMarketOnCutinManagerPage(self);
				}
			});
			listView.addHeaderView(getCutinManager);
		}
		
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
		list.add(new Data(DemosActivity.class));
		list.add(new Data(InAppSettingsActivity.class));
		list.add(new Data(InAppSettingsCutinIdActivity.class));
		list.add(new Data(InAppSettingsIconActivity.class));
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
