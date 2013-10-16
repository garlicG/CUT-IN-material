package cutin.sampleviewsupport;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class CutinListFragmentActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(android.R.id.content, new CutinListFragment());
		ft.commit();
	}


}
