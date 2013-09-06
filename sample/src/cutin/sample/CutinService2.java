package cutin.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;

import com.garlicg.cutinlib.CutinService;

public class CutinService2 extends CutinService {

	private View mDroid;

	@Override
	protected View create() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout = inflater.inflate(R.layout.cutin_service_layout, null);
		mDroid = layout.findViewById(R.id.droid);
		return layout;
	}

	@Override
	protected void start() {
		// Animation which droid -2 rotate.
		int centerX = mDroid.getWidth() / 2;
		int centerY = mDroid.getHeight() / 2;
		RotateAnimation anim = new RotateAnimation(0.f, -720f, centerX, centerY);
		anim.setDuration(1000);
		anim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				finishCutin();
			}
		});
		mDroid.startAnimation(anim);
	}

	@Override
	protected void destroy() {
	}
}
