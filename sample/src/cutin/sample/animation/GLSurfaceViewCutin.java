/*
 * Copyright (C) 2007 The Android Open Source Project
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

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.view.View;

import com.garlicg.cutinlib.CutinService;


/**
 * Wrapper activity demonstrating the use of {@link GLSurfaceView} to
 * display translucent 3D graphics.
 */
public class GLSurfaceViewCutin extends CutinService {
	private GLSurfaceView mGLSurfaceView;

	@Override
	protected View create() {
		mGLSurfaceView = new GLSurfaceView(this);
		// We want an 8888 pixel format because that's required for
		// a translucent window.
		// And we want a depth buffer.
		mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		// Tell the cube renderer that we want to render a translucent version
		// of the cube:
		mGLSurfaceView.setRenderer(new CubeRenderer(true));
		// Use a surface format with an Alpha channel:
		mGLSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		return mGLSurfaceView;
	}

	@Override
	protected void destroy() {
		mGLSurfaceView.onPause();
	}

	@Override
	protected void start(Intent arg0, int arg1, int arg2) {
		mGLSurfaceView.onResume();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				finishCutin();
			}
		}, 2000);
	}
}


