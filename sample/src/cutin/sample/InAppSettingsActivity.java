package cutin.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.CutinManagerUtils;
import com.garlicg.cutinlib.Demo;

public class InAppSettingsActivity extends Activity{
	private final InAppSettingsActivity self = this;
	private Demo mDemo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDemo = new Demo(this);
		setContentView(R.layout.activity_inappsettings);
		
		final CutinItem item = new CutinItem(SampleCutin.class, "InAppSettings!");
		View demoButton = findViewById(R.id.button1);
		demoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDemo.play(item);
			}
		});
		
		View setButton = findViewById(R.id.button2);
		setButton.setOnClickListener(new View.OnClickListener() {
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
