# PinLockView
Customizable Pin Lock View

![PinLockView](https://github.com/bilkeonur/PinLockView/blob/master/screens/screen.png)

### Gradle

Import to android studio:
```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

dependencies {
	compile 'com.github.bilkeonur:PinLockView:1.0.0'
}
```

# Usage

### Step 1

Place the view in your XML layout file.

```<com.ob.pinlockviewapp.PinLockView
        android:id="@+id/pinLockView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:button_size="96dp"
        app:pin_length="6"
        app:border_enabled="true"/>
```

### Step 2

Reference the view in code, set a new pin and add a listener to it.

```java
    PinLockView pinLockView = findViewById(R.id.pinLockView1);
    
    pinLockView.setPinCode("123456");
    
    pinLockView.setPinLockListener(new PinLockListener()
    {
        @Override
        public void onPinEnter()
        {
	    Log.d(TAG,"Pin Enter");
        }

        @Override
        public void onPinComplete(boolean result)
        {
	    Log.d(TAG,"Pin Complete");

	    if(result)
	    {
	        Toast.makeText(getApplicationContext(),"Pin Code Correct",Toast.LENGTH_SHORT).show();
	        Log.d(TAG,"Pin Correct");
	    }
	    else
	    {
	        Toast.makeText(getApplicationContext(),"Pin Code Wrong",Toast.LENGTH_SHORT).show();
	        Log.d(TAG,"Pin Wrong");
	    }
        }

        @Override
        public void onPinDelete()
        {
	    Log.d(TAG,"Pin Delete");
        }

        @Override
        public void onPinEmpty()
        {
	    Log.d(TAG,"Pin Empty");
        }
    });
  ```
# License

```
Copyright 2022 bilkeonur

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

