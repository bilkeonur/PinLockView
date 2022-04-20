package com.ob.pinlockviewapp;

public interface PinLockListener
{
    void onPinEnter();
    void onPinComplete(boolean result);
    void onPinDelete();
    void onPinEmpty();
}
