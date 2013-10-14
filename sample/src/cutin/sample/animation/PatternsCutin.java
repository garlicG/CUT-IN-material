package cutin.sample.animation;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

import com.garlicg.cutinlib.CutinService;

public class PatternsCutin extends CutinService{
	
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
		}, 2000);
	}
	
	private static class SampleView extends View {
        private final Shader mShader2;
        private final Paint mPaint;
        private Matrix mMatrix = new Matrix();

        private float mDistance;

        public SampleView(Context context) {
            super(context);
            setFocusable(true);

            mShader2 = new BitmapShader(makeBitmap2(), Shader.TileMode.REPEAT,
                                        Shader.TileMode.REPEAT);
            mPaint = new Paint();
            mPaint.setShader(mShader2);
        }
        
        private Bitmap makeBitmap2() {
            Bitmap bm = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_4444);
            Canvas c = new Canvas(bm);
            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setColor(Color.GREEN);
            p.setAlpha(0xCC);
            c.drawCircle(32, 32, 27, p);
            return bm;
        }
        
        @Override 
        protected void onDraw(Canvas canvas) {
        	mMatrix.setTranslate(mDistance+=10,mDistance);
        	mShader2.setLocalMatrix(mMatrix);
            canvas.drawPaint(mPaint);
            invalidate();
        }
	}
}
