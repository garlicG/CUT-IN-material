package cutin.sample.animation;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.Demo;

public class AnimationsActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Demo demo = new Demo(this);
		
		final ListView listView = new ListView(this);
		setContentView(listView);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				CutinItem data = (CutinItem) arg0.getItemAtPosition(arg2);
				demo.play(data.serviceClass);
			}
		});
		
		ArrayList<CutinItem> list = new ArrayList<CutinItem>();
		list.add(new CutinItem(AnimationSetCutin.class , AnimationSetCutin.class.getSimpleName()));
		list.add(new CutinItem(AnimationComboCutin.class , AnimationComboCutin.class.getSimpleName()));
		list.add(new CutinItem(AnimateDrawableCutin.class , AnimateDrawableCutin.class.getSimpleName()));
		list.add(new CutinItem(AnimatorCutin.class , AnimatorCutin.class.getSimpleName()));
		list.add(new CutinItem(GLSurfaceViewCutin.class , GLSurfaceViewCutin.class.getSimpleName()));
		
		String action = getIntent().getAction();
		final boolean fromCutinManager = action != null && action.equals(CutinInfo.ACTION_PICK_CUTIN);
		
		if(fromCutinManager){
			Button button = new Button(this);
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					CutinItem cutinItem = (CutinItem)listView.getItemAtPosition(listView.getCheckedItemPosition());
					if(cutinItem != null){
						Intent intent = CutinInfo.buildPickedIntent(cutinItem);
						setResult(RESULT_OK , intent);
						finish();
					}
				}
			});
			listView.addFooterView(button);
			listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			ArrayAdapter<CutinItem> adapter = new ArrayAdapter<CutinItem>(this, android.R.layout.simple_list_item_single_choice , list);
			listView.setAdapter(adapter);
		}
		else{
			ArrayAdapter<CutinItem> adapter = new ArrayAdapter<CutinItem>(this, android.R.layout.simple_list_item_1 , list);
			listView.setAdapter(adapter);
		}
	}
}