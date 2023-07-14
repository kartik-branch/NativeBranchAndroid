package com.example.nativeandroidbranchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import io.branch.referral.Branch;

public class DeeplinkActivity extends AppCompatActivity {

    TextView tv;
    Button btn;

    @Override
    protected void onStart() {
        super.onStart();
        Branch.sessionBuilder(this).withCallback((referringParams, error) -> {
            if (error != null) {
                Log.d("BranchSDK_Tester", "branch init failed. Caused by -" + error.getMessage());
            } else {
                Log.d("BranchSDK_Tester", "branch init complete!");
                if (referringParams != null) {
                    tv.setText(referringParams.toString());
                    Log.d("BranchSDK_Tester", referringParams.toString());
                }
            }
        }).withData(this.getIntent().getData()).init();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        Branch.sessionBuilder(this).withCallback((referringParams, error) -> {
            if (error != null) {
                Log.e("BranchSDK_Tester", error.getMessage());
            } else if (referringParams != null) {
                tv.setText(referringParams.toString());
                Log.i("BranchSDK_Tester", referringParams.toString());
            }
        }).reInit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = findViewById(R.id.tvData);
        btn = findViewById(R.id.fetchData);

        btn.setOnClickListener(view -> {
            // capture logs
            //JSONObject sessionParams = Branch.getInstance().getLatestReferringParams();
            //Log.d("BranchSDK_Tester","" + sessionParams);
            //tv.setText(sessionParams.toString());
        });
    }
}