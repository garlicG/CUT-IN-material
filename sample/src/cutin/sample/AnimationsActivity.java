package cutin.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AnimationsActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("AnimationsActivity");
		setContentView(textView);

	}
}
