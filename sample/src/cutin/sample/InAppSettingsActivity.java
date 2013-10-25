package cutin.sample;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinItem;

public class InAppSettingsActivity extends Activity{
	private final InAppSettingsActivity self = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_inappsettings);
		
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
		
		final CutinItem item = new CutinItem(InAppSettingsCutin.class, "InAppSettings");
		
		// set up Button
		View okButton = findViewById(R.id.launch_cutin_manager);
		okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// direct setting
				Intent intent = CutinInfo.buildSetCutinIntent(item);
				try{
					startActivity(intent);
				}catch(ActivityNotFoundException e){
					Toast.makeText(self, "Activity not found.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	

}
