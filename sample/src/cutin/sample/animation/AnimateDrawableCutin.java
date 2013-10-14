/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cutin.sample.animation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

import com.garlicg.cutinlib.CutinService;

import cutin.sample.R;


public class AnimateDrawableCutin extends CutinService {
	private SampleView mView;

	@Override
	protected View create() {
		mView = new SampleView(this);
		return mView;
	}

	@Override
	protected void destroy() {
	}

	@Override
	protected void start(Intent arg0, int arg1, int arg2) {
		mView.startCutinAnimation(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				finishCutin();
			}
		});
	}
	
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(new SampleView(this));
//    }
//
	interface AnimationEndListner{
		void endAnimation();
	}
    private static class SampleView extends View {
        private AnimateDrawable mDrawable;
        private Animation mAnimation;

        public SampleView(Context context) {
            super(context);
            setFocusable(true);

            Drawable dr = context.getResources().getDrawable(R.drawable.yozakura);
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());

            mAnimation = new TranslateAnimation(0, 100, 0, 200);
            mAnimation.setDuration(2000);
            mAnimation.initialize(10, 10, 10, 10);
            mDrawable = new AnimateDrawable(dr, mAnimation);
        }
        
        private void startCutinAnimation(AnimationListener listener){
        	mAnimation.setAnimationListener(listener);
        	mAnimation.startNow();
        }

        @Override
        protected void onDraw(Canvas canvas) {
//            canvas.drawColor(Color.WHITE);

            mDrawable.draw(canvas);
            invalidate();
        }
    }
}

