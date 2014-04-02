package cutin.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.CutinManagerUtils;
import com.garlicg.cutinlib.Demo;

public class InAppSettingsIconActivity extends Activity{
	private final InAppSettingsIconActivity self = this;
	private Demo mDemo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDemo = new Demo(this);
		setContentView(R.layout.activity_iconicpicked_picked);
		
		final CutinItem item = new CutinItem(IconicCutin.class, "InAppSettings Icon!");
		
		View demoButton = findViewById(R.id.button1);
		demoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDemo.play(item);
			}
		});
		
		View callButton = findViewById(R.id.button2);
		callButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean isStart = CutinManagerUtils.startActivityInAppSet(self, item);
				if(!isStart)Toast.makeText(self, "InAppSetting not found. Need to Download the CutIn Manager", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mDemo.forceStop();
	}
}
