package com.garlicg.sample.cutin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.garlicg.cutinlib.CutinService;

public class GarlinTornado extends CutinService {
	
	ImageView mImage;
	
	/**
	 * カットインで利用するデータを生成し、表示するルートビューを返却してください。
	 */
	@Override
	public View create() {
		View layout = LayoutInflater.from(this).inflate(R.layout.garlic_tornado, null);
		mImage = (ImageView)layout.findViewById(R.id.garlic_tornado_Image);
		return layout;
	}
	
	/**
	 * カットインアニメーションを実装してください。
	 * アニメーションの終了時には必ずfinishCutinを呼び出してください。
	 */
	@Override
	protected void start() {
		
		ValueAnimator rotateIn = ObjectAnimator.ofFloat(mImage,ImageView.ROTATION,1080f);
		rotateIn.setDuration(600);
		ValueAnimator scaleAnimX = ObjectAnimator.ofFloat(mImage, ImageView.SCALE_X, 1.5f);
		scaleAnimX.setDuration(600);
		scaleAnimX.setInterpolator(new DecelerateInterpolator());
		ValueAnimator scaleAnimY = ObjectAnimator.ofFloat(mImage, ImageView.SCALE_Y, 1.5f);
		scaleAnimY.setDuration(600);
		scaleAnimY.setInterpolator(new DecelerateInterpolator());
		ValueAnimator rotateOut = ObjectAnimator.ofFloat(mImage,ImageView.ROTATION,2160f);
		rotateOut.setDuration(600);
		ValueAnimator fadeOut = ObjectAnimator.ofFloat(mImage,ImageView.ALPHA,0.0f);
		fadeOut.setDuration(600);
		
		AnimatorSet tornado = new AnimatorSet();
		tornado.play(rotateIn).before(scaleAnimX);
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
	
	/**
	 * カットインで利用したリソース等を開放してください。
	 * 
	 * onDestroyは自身でfinishCutinを呼び出した場合か、
	 * ContextWrapper.stopServiceやCut-inアプリから停止された場合に呼び出されます。
	 */
	@Override
	protected void destroy() {
		// none
	}
}
