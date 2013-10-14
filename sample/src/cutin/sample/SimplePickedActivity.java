package cutin.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.Demo;

public class SimplePickedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		
		// launched from CutInManager
		if (intent != null && !TextUtils.isEmpty(intent.getAction())
				&& intent.getAction().equals(CutinInfo.ACTION_PICK_CUTIN)) {
			
			setContentView(R.layout.activity_simplepicked_picked);
			final Demo demo = new Demo(this);
			
			View demoButton = findViewById(R.id.demoButton);
			demoButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					demo.play(SimpleCutin.class);
				}
			});
			
			View okButton = findViewById(R.id.okButton);
			okButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Register SimpleCutIn to CutIn Manager.
					CutinItem item = new CutinItem(SimpleCutin.class, "SimplePicked");
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
			setContentView(R.layout.activity_simplepicked_description);
			
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
						Toast.makeText(SimplePickedActivity.this, "Download CutinManager!",Toast.LENGTH_SHORT).show();
					}
				}
			});
		}
	}
}
