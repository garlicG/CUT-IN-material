package cutin.sampleviewsupport;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.viewsupport.SimpleCutinScreen;

public class CutinIconListActivity extends Activity implements SimpleCutinScreen.PickListener{
	private SimpleCutinScreen mScreen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mScreen = new SimpleCutinScreen(this ,  getIntent());
		setContentView(mScreen.getView());
		
		ArrayList<CutinItem> list = new ArrayList<CutinItem>();
		for(int i = 0 ; i < 20 ; i++){
			// See the Service tag of AndroidManifest.xml
			list.add(new CutinItem(SampleCutin2.class, SampleCutin2.class.getSimpleName()));
		}
		mScreen.setCutinList(list);
		
		if(mScreen.getState() == SimpleCutinScreen.STATE_PICK){
			mScreen.setListener(this);
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mScreen.pause();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void ok(Intent intent) {
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void cancel() {
		setResult(RESULT_CANCELED);
		finish();
	}
}
