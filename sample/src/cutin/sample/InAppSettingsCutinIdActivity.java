package cutin.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.CutinManagerUtils;
import com.garlicg.cutinlib.Demo;

public class InAppSettingsCutinIdActivity extends Activity{
	private Demo mDemo;
	private InAppSettingsCutinIdActivity self = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDemo = new Demo(this);

		setContentView(R.layout.activity_cutinid_picked);
		
		View demoButton1 = findViewById(R.id.button1_1);
		View setButton1 = findViewById(R.id.button1_2);
		CutinItem item1 = new CutinItem(SampleCutin.class, "CutInId:1" ,1);
		setupCutInIdButton(demoButton1, setButton1, item1);
		
		View demoButton2 = findViewById(R.id.button2_1);
		View setButton2 = findViewById(R.id.button2_2);
		CutinItem item2 = new CutinItem(SampleCutin.class, "CutInId:2" ,2);
		setupCutInIdButton(demoButton2, setButton2, item2);
		
		View demoButton3 = findViewById(R.id.button3_1);
		View setButton3 = findViewById(R.id.button3_2);
		CutinItem item3 = new CutinItem(SampleCutin.class, "CutInId:3" ,3);
		setupCutInIdButton(demoButton3, setButton3, item3);
	}
	
	
	private void setupCutInIdButton(View demoButton , View okButton, final CutinItem item){
		demoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDemo.play(item);
			}
		});
		
		okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CutinManagerUtils.startActivityInAppSet(self, item);
			}
		});
	}
}
