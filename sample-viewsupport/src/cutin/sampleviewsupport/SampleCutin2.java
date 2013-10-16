package cutin.sampleviewsupport;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;

import com.garlicg.cutinlib.CutinService;

public class SampleCutin2 extends CutinService{
	private View mView;

	@Override
	protected View create() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout = inflater.inflate(R.layout.cutin_service_layout,null);
		
		// Keep View for starting animation on start()
		mView = layout.findViewById(R.id.cutin);
		
		// need to return root view.
		return layout;
	}

	@Override
	protected void destroy() {
	}

	@Override
	protected void start(Intent arg0, int arg1, int arg2) {
		int centerX = mView.getWidth()/2;
		int centerY = mView.getHeight()/2;
		
		RotateAnimation anim = new RotateAnimation(0.f , 720f , centerX , centerY);
		anim.setDuration(2000);
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
