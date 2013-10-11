package cutin.sample;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		list.add(new Data(IconActivity.class));
		list.add(new Data(ParamActivity.class));
		list.add(new Data(PreferenceActivity.class));
		list.add(new Data(AnimationsActivity.class));
		ArrayAdapter<Data> adapter = new ArrayAdapter<MainActivity.Data>(this, android.R.layout.simple_list_item_1 , list);
		listView.setAdapter(adapter);
		
		
		setContentView(listView);
	}
	
	private class Data{
		private String name;
		private Class<?extends Activity> activity;
		private Data(Class<?extends Activity> activity){
			this.activity = activity;
			name = activity.getSimpleName();
		}
		@Override
		public String toString() {
			return name;
		}
	}
}
