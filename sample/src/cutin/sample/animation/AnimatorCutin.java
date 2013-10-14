package cutin.sample.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.garlicg.cutinlib.CutinService;

import cutin.sample.R;

public class AnimatorCutin extends CutinService{
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

	@SuppressLint("NewApi")
	@Override
	protected void start(Intent arg0, int arg1, int arg2) {
		// in
		ValueAnimator rotateIn = ObjectAnimator.ofFloat(mView,ImageView.ROTATION,360f);
		rotateIn.setDuration(1000);
		DecelerateInterpolator di = new DecelerateInterpolator();
		ValueAnimator scaleAnimX = ObjectAnimator.ofFloat(mView, ImageView.SCALE_X, 1.5f);
		scaleAnimX.setDuration(1000);
		scaleAnimX.setInterpolator(di);
		ValueAnimator scaleAnimY = ObjectAnimator.ofFloat(mView, ImageView.SCALE_Y, 1.5f);
		scaleAnimY.setDuration(1000);
		scaleAnimY.setInterpolator(di);
		
		// out
		ValueAnimator rotateOut = ObjectAnimator.ofFloat(mView,ImageView.ROTATION,0f);
		rotateOut.setDuration(1000);
		ValueAnimator fadeOut = ObjectAnimator.ofFloat(mView,ImageView.ALPHA,0.0f);
		fadeOut.setDuration(1000);
		
		AnimatorSet tornado = new AnimatorSet();
		tornado.play(rotateIn).with(scaleAnimX);
		tornado.play(scaleAnimX).with(scaleAnimY);
		tornado.play(rotateOut).after(scaleAnimX);
		tornado.play(rotateOut).with(fadeOut);
		
		tornado.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				finishCutin();
			}
		});
		
		tornado.start();
	}

}
