CutIn Material
---

What is CutIn? It is effects on your phone, like in games but not in games. Try out the [sample app](https://play.google.com/store/apps/details?id=cutin.sample) on the Google Play.

![garlin_padado](garlin_parado.png)

CutIn Material is based on [CutIn Manager](https://play.google.com/store/apps/details?id=com.garlicg.cutin) app. CutIn Manger call Materials at the follow cases:

 * Screen turns on.
 * External power is connected.
 * Wired headset plugged
 * The date changed
 * Indicated low battery condition
 
If you have made an Material app , please message me. CutIn Manager have own random advertising area for CutIn Materials. It is free and will promote your apps.

GarlicG : garlicg@gmail.com

Directory
---
| name | description |
|---|---|
| cutin-connector.jar | Core library. |
| lib/ | Source code of `cutin-connector.jar`. |
| lib-viewsupport/ | AndroidLibraryProject for using default simple layout. It is include cutin-connecor. |
| sample/ | A Sample project using `lib-viewsupport/` |
| garlin/ | A Sample project for general user. It needs [v7 appcompat library](http://developer.android.com/tools/support-library/index.html) as Android library project.|

Quick Start
---

For a working implementation of this project , add the `lib-viewsupport/` as the AndroidLibraryProject and see the `sample/` folder.

1. Create a class for showing CutIn extends CutinService.

 |return|methods|	description|
 |---|---|---|
 |View	| create |	Create root view which is inflated to full screen window.
 |void	| start |	It is called after create(). At this time view size is possible to get. You must call finishCutin() or stopSelf() after your execution ending.
 |void |	destroy |	Release resources, etc.　
 
2. Copy the `sample/cutin/sample/MainActivity` and replace these lines to your CutinServices in your onCreate.
 
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
```xml
    <service android:name="(your)package.CutinServiceClassName">
        <intent-filter>
            <action android:name="(your)package.CutinServiceClassName" />
        </intent-filter>
    </service>
```

Intent from CutIn Manager
---
`onCreate` in your `Activity`.
```java
		String action = getIntent().getAction();
		if(!TextUtils.isEmpty(action) && action.equals(CutinInfo.ACTION_PICK_CUTIN)){
			// Called from CUT-IN Manager
		}
```

Return Intent to CutIn Manager
---
Somewhere to finish in your `Activity`.
```java
		CutinItem item = new CutinItem(CutinService.class , "SAMPLE 1");
		Intent intent = CutinInfo.buildPickedIntent(item);
		setResult(RESULT_OK , intent);
		finish();
```


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

