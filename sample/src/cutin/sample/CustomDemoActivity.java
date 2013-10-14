package cutin.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CustomDemoActivity extends Activity{
	private Intent mCutInIntent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_customdemo);
		
		final EditText edit = (EditText)findViewById(R.id.customdemoEdit);
		
		View demoButton = findViewById(R.id.demoButton);
		demoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				play("" + edit.getText().toString());
			}
		});
		
	}
	
	private void play(String name){
		stop();
		mCutInIntent = new Intent(this , CustomDemoCutin.class);
		mCutInIntent.putExtra(CustomDemoCutin.KEY_CUSTOM, name);
		startService(mCutInIntent);
	}
	
	private void stop(){
		if(mCutInIntent != null){
			stopService(mCutInIntent);
		}
		mCutInIntent = null;
	}
}
