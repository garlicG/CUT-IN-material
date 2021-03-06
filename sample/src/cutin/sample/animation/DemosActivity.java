package cutin.sample.animation;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.garlicg.cutinlib.CutinItem;
import com.garlicg.cutinlib.CutinManagerUtils;
import com.garlicg.cutinlib.Demo;

public class DemosActivity extends Activity{
	
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
		boolean more14 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
		
		ArrayList<CutinItem> list = new ArrayList<CutinItem>();
		list.add(new CutinItem(AnimationSetCutin.class , AnimationSetCutin.class.getSimpleName()));
		list.add(new CutinItem(RecursiveAnimationCutin.class , RecursiveAnimationCutin.class.getSimpleName()));
		list.add(new CutinItem(AnimateDrawableCutin.class , AnimateDrawableCutin.class.getSimpleName()));
		if(more14)list.add(new CutinItem(AnimatorCutin.class , AnimatorCutin.class.getSimpleName()));
		list.add(new CutinItem(SweepCutin.class , SweepCutin.class.getSimpleName()));
		list.add(new CutinItem(PathEffectsCutin.class , PathEffectsCutin.class.getSimpleName()));
		list.add(new CutinItem(PatternsCutin.class , PatternsCutin.class.getSimpleName()));
		list.add(new CutinItem(SurfaceViewCutin.class , SurfaceViewCutin.class.getSimpleName()));
		list.add(new CutinItem(GLSurfaceViewCutin.class , GLSurfaceViewCutin.class.getSimpleName()));
		
		if(CutinManagerUtils.isCalledFromCutinManager(getIntent())){
			Button button = new Button(this);
			button.setText("OK");
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					CutinItem cutinItem = (CutinItem)listView.getItemAtPosition(listView.getCheckedItemPosition());
					if(cutinItem != null){
						Intent intent = CutinManagerUtils.buildResultIntent(cutinItem);
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