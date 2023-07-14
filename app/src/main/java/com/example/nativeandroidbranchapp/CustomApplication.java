package com.example.nativeandroidbranchapp;

import android.app.Application;

import io.branch.referral.Branch;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Branch.enableLogging();
        // Branch object initialization
        Branch.getAutoInstance(this);
    }
}
