package com.ob.pinlockviewapp;

public interface PinLockListener
{
    void onPinEnter();
    void onComplete();
    void onDelete();
    void onEmpty();
}
