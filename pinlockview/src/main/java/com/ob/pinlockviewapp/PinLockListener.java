package com.ob.pinlockviewapp;

public interface PinLockListener
{
    void onPinEnter();
    void onPinComplete();
    void onPinDelete();
    void onPinEmpty();
}
