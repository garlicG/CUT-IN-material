package cutin.sample.animation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

import com.garlicg.cutinlib.CutinService;

import cutin.sample.R;

public class RecursiveAnimationCutin extends CutinService{
	private View mView;
	private int mCount = 0;
	
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
		int baseDistance = mView.getWidth()/2;
		recursiveAnimation(baseDistance);
	}
	
	private void recursiveAnimation(final int distance){
		int vector = (mCount+1)%2 == 0 ? -1 : 1;
		int preVector = vector * -1;
		TranslateAnimation anim = new TranslateAnimation(distance * preVector, distance *vector, 0, 0);
		anim.setDuration(500);
		anim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				if(++mCount == 4){
					mView.setVisibility(View.GONE);
					finishCutin();
				}
				else{
					recursiveAnimation( distance);
				}
			}
		});
		mView.startAnimation(anim);
		
	}
	
}

