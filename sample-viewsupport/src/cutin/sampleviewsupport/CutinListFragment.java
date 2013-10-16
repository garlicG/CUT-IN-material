package cutin.sampleviewsupport;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.viewsupport.SimpleCutinScreen;

public class CutinListFragment extends Fragment implements SimpleCutinScreen.PickListener{
	private SimpleCutinScreen mScreen;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mScreen = new SimpleCutinScreen(getActivity() ,  getActivity().getIntent());
		ArrayList<CutinItem> list = new ArrayList<CutinItem>();
		for(int i = 0 ; i < 20 ; i++){
			list.add(new CutinItem(SampleCutin.class, SampleCutin.class.getSimpleName()));
		}
		mScreen.setCutinList(list);
		
		if(mScreen.getState() == SimpleCutinScreen.STATE_PICK){
			mScreen.setListener(this);
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		return mScreen.getView();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mScreen.pause();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			getActivity().finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void ok(Intent intent) {
		getActivity().setResult(Activity.RESULT_OK , intent);
		getActivity().finish();
	}

	@Override
	public void cancel() {
		getActivity().finish();
	}
}
