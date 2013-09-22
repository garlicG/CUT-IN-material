package com.garlicg.sample.cutin;

import java.io.IOException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.garlicg.cutinlib.CutinService;
import com.garlicg.sample.cutin.util.Logger;

public class GarlinTornado extends CutinService {
	
	private ImageView mImage;
	private MediaPlayer mPlayer;
	
	@Override
	public View create() {
		View layout = LayoutInflater.from(this).inflate(R.layout.garlic_tornado, null);
		mImage = (ImageView)layout.findViewById(R.id.garlic_tornado_Image);
		if(Prefs.getSound(this)){
			mPlayer = MediaPlayer.create(this, R.raw.tornado);
			try {
				mPlayer.prepare();
			} catch (IllegalStateException e) {
				Logger.e("GarlinTornado", e.toString());
			} catch (IOException e) {
				Logger.e("GarlinTornado", e.toString());
			}
		}
		
		return layout;
	}

	@SuppressLint("NewApi")
	@Override
	protected void start(Intent intent, int flags, int startId) {
		
		// for 2.x 3.x
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH){
			int centerX = mImage.getWidth()/2;
			int centerY = mImage.getHeight()/2;
			AnimationSet tornado = new AnimationSet(false);
			// in
			RotateAnimation rotateIn = new RotateAnimation(0, 1080 , centerX , centerY);
			rotateIn.setDuration(600);
			tornado.addAnimation(rotateIn);
			
			// scale up
			ScaleAnimation scale = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f , centerX ,centerY);
			scale.setDuration(600);
			scale.setStartOffset(600);
			tornado.addAnimation(scale);
			
			// out
			RotateAnimation rotateOut = new RotateAnimation(0, 1080 , mImage.getWidth()/2 , mImage.getHeight()/2);
			rotateOut.setDuration(600);
			rotateOut.setStartOffset(1200);
			tornado.addAnimation(rotateOut);
			
			AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
			fadeOut.setDuration(600);
			fadeOut.setStartOffset(1200);
			tornado.addAnimation(fadeOut);
			
			tornado.setAnimationListener(new AnimationListener() {
				
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
			mImage.startAnimation(tornado);
		}
		
		// for 4.x
		else{
			// in
			ValueAnimator rotateIn = ObjectAnimator.ofFloat(mImage,ImageView.ROTATION,1080f);
			rotateIn.setDuration(600);
			
			// scale up
			DecelerateInterpolator di = new DecelerateInterpolator();
			ValueAnimator scaleAnimX = ObjectAnimator.ofFloat(mImage, ImageView.SCALE_X, 1.5f);
			scaleAnimX.setDuration(600);
			scaleAnimX.setInterpolator(di);
			ValueAnimator scaleAnimY = ObjectAnimator.ofFloat(mImage, ImageView.SCALE_Y, 1.5f);
			scaleAnimY.setDuration(600);
			scaleAnimY.setInterpolator(di);
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
		
		if(mPlayer != null){
			try{
				mPlayer.start();
			}catch(IllegalStateException e){
				Logger.e("GarlicTornado", e.toString());
			}
		}
	}

	@Override
	protected void destroy() {
		if(mPlayer != null){
			if(mPlayer.isPlaying()){
			try{
				mPlayer.stop();
			}catch(IllegalStateException e){
				Logger.e("GarlicTornado", e.toString());
			}
			}
			mPlayer.reset();
			mPlayer.release();
		}
	}
}
