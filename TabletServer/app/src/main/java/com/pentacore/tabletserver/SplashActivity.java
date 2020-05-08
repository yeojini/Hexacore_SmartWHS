package com.pentacore.tabletserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new SplashRunnable(), 1500);
    }

    private class SplashRunnable implements Runnable{
        @Override
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        // 스플래시 화면에서 뒤로가기 막아두기
    }
}
