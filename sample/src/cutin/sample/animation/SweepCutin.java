package cutin.sample.animation;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.view.View;

import com.garlicg.cutinlib.CutinService;

public class SweepCutin extends CutinService{

	@Override
	protected View create() {
		return new SampleView(this);
	}

	@Override
	protected void destroy() {
	}

	@Override
	protected void start(Intent arg0, int arg1, int arg2) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				finishCutin();
			}
		}, 1500);
	}
	
	private static class SampleView extends View {
        private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float mRotate;
        private Matrix mMatrix = new Matrix();
        private Shader mShader;

        public SampleView(Context context) {
            super(context);
            setFocusable(true);
            setFocusableInTouchMode(true);

            float x = 160;
            float y = 100;
            mShader = new SweepGradient(x, y, new int[] { Color.GREEN,
                                                  Color.RED,
                                                  Color.BLUE,
                                                  Color.GREEN }, null);
            mPaint.setShader(mShader);
            mPaint.setDither(true);
        }

        @Override 
        protected void onDraw(Canvas canvas) {
            Paint paint = mPaint;
            float x = 160;
            float y = 100;

            canvas.drawColor(Color.TRANSPARENT);

            mMatrix.setRotate(mRotate, x, y);
            mShader.setLocalMatrix(mMatrix);
            mRotate += 3;
            if (mRotate >= 360) {
                mRotate = 0;
            }
            canvas.drawCircle(x, y, 80, paint);
            
            invalidate();
        }
    }
}
