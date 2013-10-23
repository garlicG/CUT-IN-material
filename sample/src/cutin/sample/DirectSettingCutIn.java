package cutin.sample;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinService;

public class DirectSettingCutIn extends CutinService {
	private View mView;
	private TextView mTextView;

	@Override
	protected View create() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout = inflater.inflate(R.layout.cutin_default, null);
		mView = layout.findViewById(R.id.cutin_default_view);
		mTextView = (TextView) layout.findViewById(R.id.cutin_default_text);
		mTextView.setText("DirectSetting");
		return layout;
	}

	@Override
	protected void destroy() {
	}

	@Override
	protected void start(Intent intent, int arg1, int arg2) {
		// Get CutInId from intent.
		long cutinId = intent.getLongExtra(CutinInfo.DATA_CUTIN_ID, 0);
		if(cutinId == 0){
			mView.setBackgroundResource(R.drawable.green_cutin);
		}
		else if(cutinId == 1){
			mView.setBackgroundResource(R.drawable.blue_cutin);
		}
		else if(cutinId == 2){
			mView.setBackgroundResource(R.drawable.yellow_cutin);
		}

		int centerX = mView.getWidth() / 2;
		int centerY = mView.getHeight() / 2;
		RotateAnimation anim = new RotateAnimation(0.f, 720f, centerX, centerY);
		anim.setDuration(1500);
		anim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				finishCutin();
			}
		});

		mView.startAnimation(anim);
	}

}
