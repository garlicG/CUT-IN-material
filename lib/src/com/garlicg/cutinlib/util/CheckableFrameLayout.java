package com.garlicg.cutinlib.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

public class CheckableFrameLayout extends FrameLayout implements Checkable {
	private Checkable mCheckable;
	public CheckableFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public CheckableFrameLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		int count = getChildCount();
		for(int i = 0 ; i < count ; i++){
			View v = getChildAt(i);
			if(v instanceof Checkable){
				mCheckable = (Checkable)v;
				break; // 1個見つけるver
			}
		}
	}
	@Override
	public boolean isChecked() {
		if(mCheckable != null){
			return mCheckable.isChecked();
		}
		return false;
	}
	@Override
	public void setChecked(boolean checked) {
		if(mCheckable != null){
			mCheckable.setChecked(checked);
		}
	}
	@Override
	public void toggle() {
		if(mCheckable != null){
			mCheckable.toggle();
		}
	}
}
