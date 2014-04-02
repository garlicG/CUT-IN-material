package cutin.sample;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.garlicg.cutinlib.CutinService;

public class SampleCutin extends CutinService{
	private View mView;
	private TextView mTextView;
	
	@Override
	protected View create() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View layout = inflater.inflate(R.layout.cutin_default, null);
		mView = layout.findViewById(R.id.cutin_default_view);
		mTextView = (TextView)layout.findViewById(R.id.cutin_default_text);
		return layout;
	}

	@Override
	protected void destroy() {
	}

	@Override
	protected void start(Intent intent, int flags, int startId) {
		StringBuilder sb = new StringBuilder("SampleCutin");
		
		//-- ここから optional
		// 登録したIDやトリガー毎にふるまいを変えたい場合は、以下のように情報を取得してからごにょごにょすることができます。
		long cutinId = intent.getLongExtra(EXTRA_CUTIN_ID, -1);
		sb.append("\ncutinId:" + cutinId);
		
		int triggerId = intent.getIntExtra(EXTRA_TRIGGER_ID, -1);
		sb.append("\ntriggerId:" + triggerId);
		if(triggerId == TRIGGER_ID_NOTIFICATION){
			String ticker = intent.getStringExtra(EXTRA_NOTIFICATION_TICKER);
			if(!TextUtils.isEmpty(ticker))sb.append("\nticker:" +ticker);
			
			String appName = intent.getStringExtra(EXTRA_NOTIFICATION_PACKAGE_NAME);
			if(!TextUtils.isEmpty(appName))sb.append("\npackageName:" + appName);
		}
		mTextView.setText(sb.toString());
		//-- ここまで
		
		int centerX = mView.getWidth()/2;
		int centerY = mView.getHeight()/2;
		RotateAnimation anim = new RotateAnimation(0.f, 720f , centerX , centerY);
		anim.setDuration(1500);
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
