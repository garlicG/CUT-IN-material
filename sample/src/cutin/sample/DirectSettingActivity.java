package cutin.sample;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.Demo;

public class DirectSettingActivity extends Activity{
	private static final String TAG = "DirectSettingActivity";
	private final DirectSettingActivity self = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Demo demo = new Demo(this);
		
		setContentView(R.layout.activity_directsetting);
		
		View getOnGooglePlay = findViewById(R.id.get_on_google_play);
		getOnGooglePlay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("market://details?id=com.garlicg.cutin"));
				startActivity(intent);
			}
		});
		
		// set up ListView
		ArrayList<CutinItem> list = new ArrayList<CutinItem>();
		list.add(new CutinItem(DirectSettingCutIn.class, "GREEN" ,0));
		list.add(new CutinItem(DirectSettingCutIn.class, "BLUE" ,1));
		list.add(new CutinItem(DirectSettingCutIn.class, "YELLOW" ,2));
		
		ArrayAdapter<CutinItem> adapter = new ArrayAdapter<CutinItem>(this, android.R.layout.simple_list_item_single_choice, list);
		
		final ListView listView = (ListView)findViewById(R.id.direct_setting_ListView);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				CutinItem item = (CutinItem)arg0.getItemAtPosition(arg2);
				demo.play(item.serviceClass,item.cutinId);
			}
		});
		
		// set up Button
		View okButton = findViewById(R.id.launch_cutin_manager);
		okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int position = listView.getCheckedItemPosition();
				Object obj = listView.getItemAtPosition(position);
				if(obj != null){
					// direct setting!
					Intent intent = CutinInfo.buildSetCutinIntent((CutinItem)obj);
					try{
						startActivity(intent);
					}catch(ActivityNotFoundException e){
						Toast.makeText(self, "Activity not found.", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
	

}
