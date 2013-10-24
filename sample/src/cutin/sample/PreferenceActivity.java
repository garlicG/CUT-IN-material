package cutin.sample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinItem;

public class PreferenceActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		
		// launched from CutInManager
		if (intent != null && !TextUtils.isEmpty(intent.getAction())
				&& intent.getAction().equals(CutinInfo.ACTION_PICK_CUTIN)) {
			
			setContentView(R.layout.activity_preference_picked);
			
			final EditText edit = (EditText)findViewById(R.id.preferenceEdit);
			SharedPreferences prefs = getSharedPreferences(getPackageName() ,MODE_MULTI_PROCESS);
			edit.setText(prefs.getString(PreferenceCutin.KEY_NAME, "DEFAULT"));
			
			View okButton = findViewById(R.id.okButton);
			okButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Save the name to preference. Saved name is used when show PreferenceCutIn.
					String name = edit.getText().toString();
					SharedPreferences prefs = getSharedPreferences(getPackageName() ,MODE_MULTI_PROCESS);
					Editor edit = prefs.edit();
					edit.putString(PreferenceCutin.KEY_NAME, name);
					edit.commit();
					
					// Register SimpleCutIn to CutIn Manager.
					CutinItem item = new CutinItem(PreferenceCutin.class, "Preference");
					Intent intent = CutinInfo.buildPickedIntent(item);
					setResult(RESULT_OK , intent);
					finish();
				}
			});
			
			View cancelButton = findViewById(R.id.cancelButton);
			cancelButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					setResult(RESULT_CANCELED);
					finish();
				}
			});
		}
		
		
		// launched from MainActivity
		else {
			setContentView(R.layout.activity_preference_description);
			
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
			
			View launchCutInManager = findViewById(R.id.launch_cutin_manager);
			launchCutInManager.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = getPackageManager().getLaunchIntentForPackage("com.garlicg.cutin");
					if(intent != null){
						startActivity(intent);
					}else{
						Toast.makeText(PreferenceActivity.this, "Download CutinManager!",Toast.LENGTH_SHORT).show();
					}
				}
			});
		}
	}
}
