package cutin.sample.animation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.garlicg.cutinlib.CutinService;

import cutin.sample.R;

public class AnimationSetCutin extends CutinService{
private View mView;
	
	@Override
	protected View create() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout = inflater.inflate(R.layout.cutin_default, null);
		mView = layout.findViewById(R.id.cutin_default_view);
		return layout;
	}

	@Override
	protected void destroy() {
	}

	@Override
	protected void start(Intent intent, int flags, int startId) {
		int centerX = mView.getWidth()/2;
		int centerY = mView.getHeight()/2;
		
		AnimationSet as = new AnimationSet(false);
		as.addAnimation(new TranslateAnimation(0f, 100f , 0f,100f));
		as.addAnimation(new AlphaAnimation(0.2f, 1f));
		as.addAnimation(new RotateAnimation(0f, 720f , centerX , centerY));
		as.addAnimation(new ScaleAnimation(0.2f, 1f, 0.2f, 1f , centerX , centerY));
		as.setDuration(2000);
		as.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				finishCutin();
			}
		});
		
		mView.startAnimation(as);
	}
}

