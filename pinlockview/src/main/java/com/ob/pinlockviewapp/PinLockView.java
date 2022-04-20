package com.ob.pinlockviewapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class PinLockView extends LinearLayout
{
    private LinearLayout viewContainer;
    private LinearLayout pinContainer;
    private ArrayList<ImageView> pinImages = new ArrayList<>();
    private ArrayList<ImageButton> pinButtons = new ArrayList<>();
    private LayoutParams params;
    private PinLockListener pinLockListener;

    private int pinLength = 4;
    private int curDigit = 0;
    String pinCode = "";
    String curPinCode = "";

    public PinLockView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PinLockView(Context context, @Nullable AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle)
    {
        inflate(context, R.layout.activity_pin_lock, this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.PinLockView);

        float buttonSize = typedArray.getDimension(R.styleable.PinLockView_button_size, 64F);
        pinLength = typedArray.getInt(R.styleable.PinLockView_pin_length,4);
        boolean borderEnabled = typedArray.getBoolean(R.styleable.PinLockView_border_enabled,false);
        typedArray.recycle();

        initComponents((int)buttonSize,borderEnabled);
        initPinLength(context,pinLength,(int)buttonSize);
    }

    private void initComponents(int buttonSize, boolean borderEnabled)
    {
        viewContainer = findViewById(R.id.linearLayout1);
        pinContainer = findViewById(R.id.linearLayout2);

        pinButtons.add(findViewById(R.id.imageButton1));
        pinButtons.add(findViewById(R.id.imageButton2));
        pinButtons.add(findViewById(R.id.imageButton3));
        pinButtons.add(findViewById(R.id.imageButton4));
        pinButtons.add(findViewById(R.id.imageButton5));
        pinButtons.add(findViewById(R.id.imageButton6));
        pinButtons.add(findViewById(R.id.imageButton7));
        pinButtons.add(findViewById(R.id.imageButton8));
        pinButtons.add(findViewById(R.id.imageButton9));
        pinButtons.add(findViewById(R.id.imageButton10));
        pinButtons.add(findViewById(R.id.imageButton11));
        pinButtons.add(findViewById(R.id.imageButton12));

        if(borderEnabled)
        {
            viewContainer.setBackgroundResource(R.drawable.layout_border);
        }

        for(ImageButton pinButton : pinButtons)
        {
            params = new LayoutParams(buttonSize, buttonSize);

            pinButton.setLayoutParams(params);
            pinButton.setOnClickListener(this::pinButtonClickListener);
        }
    }

    private void initPinLength(Context context, int pinLength, int buttonSize)
    {
        if(pinLength>7)
        {
            pinLength = 7;
        }
        else if(pinLength<4)
        {
            pinLength = 4;
        }

        for(int i=1 ;i<pinLength; i++)
        {
            pinCode+=("1");
        }

        for(int i=0; i<pinLength; i++)
        {
            LayoutParams layoutParams = new LayoutParams(buttonSize/3, buttonSize/3);
            ImageView pinImage = new ImageView(context);
            pinImage.setImageResource(R.drawable.ic_dot_empty);
            layoutParams.setMargins(5,5,5,5);
            pinImage.setLayoutParams(layoutParams);
            pinImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            pinImages.add(pinImage);
            pinContainer.addView(pinImage);
        }
    }

    public void setPinLockListener(PinLockListener pinLockListener)
    {
        this.pinLockListener = pinLockListener;
    }

    public void setPinCode(String pinCode)
    {
        if(pinCode != "" && pinCode.length() == pinLength)
        {
            try
            {
                this.pinCode = pinCode;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void clearPinCode()
    {
        curPinCode = "";
        curDigit = 0;

        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                for(int i=0; i<pinLength; i++)
                {
                    pinImages.get(i).setImageResource(R.drawable.ic_dot_empty);
                }
            }
        },300);
    }

    private void pinButtonClickListener(View view)
    {
        int btnId = view.getId();

        if(btnId!=R.id.imageButton12 && btnId!=R.id.imageButton10)
        {
            if(curDigit == pinLength)
            {
                return;
            }

            if(curDigit != pinLength)
            {
                pinImages.get(curDigit).setImageResource(R.drawable.ic_dot_fill);
                pinLockListener.onPinEnter();
                curDigit++;

                ImageButton imageButton = findViewById(btnId);
                int pinDigit = (pinButtons.indexOf(imageButton) + 1) % 11;

                curPinCode += pinDigit;
            }

            if(curDigit == pinLength)
            {
                if(curPinCode.matches(pinCode))
                {
                    clearPinCode();
                    pinLockListener.onPinComplete(true);
                }
                else
                {
                    clearPinCode();
                    pinLockListener.onPinComplete(false);
                }
            }
        }
        else
        {
            if(curDigit == 0)
            {
                return;
            }

            if(curDigit != 0)
            {
                curPinCode = curPinCode.substring(0, curPinCode.length() - 1);

                pinImages.get(curDigit-1).setImageResource(R.drawable.ic_dot_empty);
                pinLockListener.onPinDelete();

                curDigit--;
            }

            if(curDigit == 0)
            {
                pinLockListener.onPinEmpty();
            }
        }
    }
}
