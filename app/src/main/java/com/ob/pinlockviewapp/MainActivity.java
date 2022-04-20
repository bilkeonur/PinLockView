package com.ob.pinlockviewapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    PinLockView pinLockView;

    String TAG = "ONR";

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

        pinLockView.setPinCode("123456");
    }
}