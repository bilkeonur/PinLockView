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

public class PinLockView extends LinearLayout
{
    LinearLayout viewContainer;
    LinearLayout pinContainer;
    ArrayList<ImageView> pinImages = new ArrayList<>();
    ArrayList<ImageButton> pinButtons = new ArrayList<>();

    LayoutParams params;

    private PinLockListener pinLockListener;

    int pinLength = 4;
    int curDigit = 0;

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

        initComponents();

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.PinLockView);

        float buttonSize = typedArray.getDimension(R.styleable.PinLockView_button_size, 64F);
        float buttonMargin = typedArray.getDimension(R.styleable.PinLockView_button_margin,5.0F);
        pinLength = typedArray.getInt(R.styleable.PinLockView_pin_length,4);

        typedArray.recycle();

        initPinLength(context,pinLength);
        //setButtonSize(dpToPx(buttonSize));
        //setButtonMargins((int)buttonMargin);
    }

    private void initComponents()
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

        for(ImageButton pinButton : pinButtons)
        {
            pinButton.setOnClickListener(this::pinButtonClickListener);
        }
    }

    public void setButtonSize(int size)
    {
        params = new LayoutParams(size, size);
    }

    public void setButtonMargins(int margin)
    {
        params.setMargins(margin, margin, margin, margin);

        for (ImageButton pinButton : pinButtons)
        {
            pinButton.setLayoutParams(params);
        }
    }

    public void initPinLength(Context context, int pinLength)
    {
        for(int i=0; i<pinLength; i++)
        {
            ImageView pinImage = new ImageView(context);
            pinImage.setImageResource(R.drawable.ic_dot_empty);
            pinImage.setLayoutParams(new LayoutParams(96, 96));
            pinImages.add(pinImage);
            pinContainer.addView(pinImage);
        }
    }

    public void setPinLockListener(PinLockListener pinLockListener)
    {
        this.pinLockListener = pinLockListener;
    }

    private void pinButtonClickListener(View view)
    {
        int btnId = view.getId();

        if(curDigit!=pinLength-1)
        {
            curDigit++;
            pinLockListener.onPinEnter();
        }
        else
        {
            pinLockListener.onComplete();
        }

        if(btnId == R.id.imageButton1)
        {

        }
    }
}
