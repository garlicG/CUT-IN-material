package cutin.sample.animation;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinService;
import com.garlicg.cutinlib.Demo;

public class AnimationsActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String action = getIntent().getAction();
		final boolean fromCutinManager = action != null && action.equals(CutinInfo.ACTION_PICK_CUTIN);
		final Demo demo = new Demo(this);
		
		ListView listView = new ListView(this);
		setContentView(listView);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Data data = (Data) arg0.getItemAtPosition(arg2);
				demo.play(data.cutinService);
			}
		});
		
		ArrayList<Data> list = new ArrayList<Data>();
		list.add(new Data(GLSurfaceViewCutIn.class));
		list.add(new Data(AnimateDrawableCutIn.class));
		int layoutRes = fromCutinManager ? android.R.layout.simple_list_item_single_choice : android.R.layout.simple_list_item_1;
		ArrayAdapter<Data> adapter = new ArrayAdapter<Data>(this, layoutRes , list);
		listView.setAdapter(adapter);
	}
	
	private class Data{
		private String name;
		private Class<? extends CutinService> cutinService;
		private Data(Class<? extends CutinService> cutinService){
			this.cutinService = cutinService;
			name = cutinService.getSimpleName();
		}
		@Override
		public String toString() {
			return name;
		}
	}
}