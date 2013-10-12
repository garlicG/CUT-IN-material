package cutin.sample;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;

import com.garlicg.cutinlib.CutinService;

public class SimpleCutIn extends CutinService{
	private View mView;
	
	@Override
	protected View create() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout = inflater.inflate(R.layout.cutin_simple, null);
		mView = layout.findViewById(R.id.cutin_simple_view);
		return layout;
	}

	@Override
	protected void destroy() {
	}

	@Override
	protected void start(Intent intent, int flags, int startId) {
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
