package com.ob.pinlockviewapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    PinLockView pinLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pinLockView = findViewById(R.id.pinLockView1);

        pinLockView.setPinLockListener(new PinLockListener()
        {
            @Override
            public void onPinEnter()
            {
                Log.d("ONR","Pin Enter");
            }

            @Override
            public void onPinComplete()
            {
                Log.d("ONR","Pin Complete");
            }

            @Override
            public void onPinDelete()
            {
                Log.d("ONR","Pin Delete");
            }

            @Override
            public void onPinEmpty()
            {
                Log.d("ONR","Pin Empty");
            }
        });
    }
}