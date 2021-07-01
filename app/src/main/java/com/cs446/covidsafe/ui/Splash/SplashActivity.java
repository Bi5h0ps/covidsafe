package com.cs446.covidsafe.ui.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.cs446.covidsafe.R;
import com.cs446.covidsafe.ui.Main.MainActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final long showTime = 2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.animation_view)
    LottieAnimationView mAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Handler handler = new Handler(getMainLooper());
        Runnable myRunnable= this::junpToMainActivity;

        handler.postDelayed(myRunnable, showTime*1000);
    }

    public void junpToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}