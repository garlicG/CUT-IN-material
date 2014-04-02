package cutin.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.CutinManagerUtils;
import com.garlicg.cutinlib.Demo;

public class SimplePickedActivity extends Activity {

	private Demo mDemo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_simplepicked_picked);
		mDemo = new Demo(this);
		final CutinItem item = new CutinItem(SampleCutin.class, "SimplePicked");
		
		View demoButton = findViewById(R.id.demoButton);
		demoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDemo.play(item);
			}
		});
		
		View okButton = findViewById(R.id.okButton);
		okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = CutinManagerUtils.buildResultIntent(item);
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
	
	@Override
	protected void onPause() {
		super.onPause();
		mDemo.forceStop();
	}
}
