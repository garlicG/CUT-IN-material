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

public class CutinIdActivity extends Activity{
	private Demo mDemo;
	
	private void setupCutInIdButton(View demoButton , View okButton, final long cutInId){
		demoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// CutInIdCutIn service receive this cutInId.
				mDemo.play(CutinIdCutin.class , cutInId);
			}
		});
		
		okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// CutIn Manager register this cutInId
				CutinItem item = new CutinItem(CutinIdCutin.class, "CutInId:" + cutInId , cutInId);
				Intent intent = CutinInfo.buildPickedIntent(item);
				setResult(RESULT_OK , intent);
				finish();
			}
		});
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		mDemo = new Demo(this);

		// launched from CutInManager
		if (intent != null && !TextUtils.isEmpty(intent.getAction())
				&& intent.getAction().equals(CutinInfo.ACTION_PICK_CUTIN)) {
			
			setContentView(R.layout.activity_cutinid_picked);
			
			View cutInIdButton1 = findViewById(R.id.cutInIdButton1);
			View okButton1 = findViewById(R.id.okButton1);
			setupCutInIdButton(cutInIdButton1, okButton1, 1);
			
			View cutInIdButton2 = findViewById(R.id.cutInIdButton2);
			View okButton2 = findViewById(R.id.okButton2);
			setupCutInIdButton(cutInIdButton2, okButton2, 2);
			
			View cutInIdButton3 = findViewById(R.id.cutInIdButton3);
			View okButton3 = findViewById(R.id.okButton3);
			setupCutInIdButton(cutInIdButton3, okButton3, 3);
		}
		
		
		// launched from MainActivity
		else {
			setContentView(R.layout.activity_cutinid_description);
			
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
						Toast.makeText(CutinIdActivity.this, "Download CutinManager!",Toast.LENGTH_SHORT).show();
					}
				}
			});
		}
	}
}
