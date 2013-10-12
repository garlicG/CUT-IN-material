package cutin.sample;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.garlicg.cutinlib.CutinInfo;
import com.garlicg.cutinlib.CutinService;

public class CutInIdCutIn extends CutinService{
	private View mView;
	private TextView mTextView;
	
	@Override
	protected View create() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout = inflater.inflate(R.layout.cutin_default, null);
		mView = layout.findViewById(R.id.cutin_default_view);
		mTextView = (TextView)layout.findViewById(R.id.cutin_default_text);
		return layout;
	}

	@Override
	protected void destroy() {
	}

	@Override
	protected void start(Intent intent, int flags, int startId) {
		// Get CutInId from intent.
		long cutinId = intent.getLongExtra(CutinInfo.DATA_CUTIN_ID, -1);
		mTextView.setText("CutInId:" + cutinId);
		
		int centerX = mView.getWidth()/2;
		int centerY = mView.getHeight()/2;
		RotateAnimation anim = new RotateAnimation(0.f, 720f , centerX , centerY);
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
