CUT-IN material
---

What is CUT-IN? It is effects on your phone, like in games but not in games. Try out the sample app on the Google Play.

[pic]

CUT-IN Material is based on CUT-IN Manager app. CUT-IN Manger call Materials at the follow cases:

 * Screen turns on.
 * External power is connected.
 * External power os disconnected.
 
So it is easy to make apps. You just need to write animation or some function if you have your own contents.

If you have made an Material app , please message me. CUT-IN Manager have own random advertising area for CUT-IN Materials. It is free and will promote your apps.

GarlicG : garlicg@gmail.com

Quick Start
---

For a working implementation of this project , add the lib/ as the AndroidLibraryProject and see the sample/ folder.

1. Create a class for showing CUT-IN extends CutinService.

 |return|methods|	description|
 |---|---|---|
 |View	| create |	Create root view which is inflated to full screen window.
 |void	| start |	It is called after create(). At this time view size is possible to get. You must call finishCutin() or stopSelf() after your execution ending.
 |void |	destroy |	Release resources, etc.　
 
2. Copy the sample/cutin/sample/MainActivity and replace these lines to your CutinServices in your onCreate.
 
```java    
 		// Create your CutinService list:
		// cutinName(SAMPLE) used for showing on the display in your app and CUT-IN Manager. 
		ArrayList<CutinItem> list = new ArrayList<CutinItem>();
		list.add(new CutinItem(CutinService1.class, "SAMPLE1"));
		list.add(new CutinItem(CutinService2.class, "SAMPLE2"));
		list.add(new CutinItem(CutinService3.class, "SAMPLE3"));
		mScreen.setCutinList(list);
```

3. AndroidManifest.xml

Activity

```xml
    <activity
        android:name="(your)ActivityName"
        android:label="@string/app_name" >
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
        <intent-filter>
            <action android:name="com.garlicg.cutin.action.PICK" />
            <category android:name="com.garlicg.cutin.category.RESOURCE" />
            <category android:name="android.intent.category.DEFAULT" />
        </intent-filter>
    </activity>
```

CutinService
android:process must start .(dot) charactor.

```xml
    <service
        android:name="(your)package.CutinServiceClassName"
        android:process=".(your)cutinname" >
        <intent-filter>
            <action android:name="(your)package.CutinServiceClassName" />
        </intent-filter>
    </service>
```
    
Intent from CUT-IN Manager
---
```java
		String action = getIntent().getAction();
		if(!TextUtils.isEmpty(action) && action.equals(CutinInfo.ACTION_PICK_CUTIN)){
			// Called from CUT-IN Manager
		}
```

Return Intent to CUT-IN Manager
---
```java
		CutinItem item = new CutinItem(CutinService.class , "SAMPLE 1");
		Intent intent = CutinInfo.buildPickedIntent(item);
		setResult(RESULT_OK , intent);
		finish();
```

Debug
---
If logcat do not show logs, remove android:process defination of CutinService in `AndroidManifest.xml`.


License
---

    Copyright 2013 Takahiro GOTO

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

