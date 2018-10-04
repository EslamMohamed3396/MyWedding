package com.example.eslam.mywedding.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.eslam.mywedding.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {
    private Animation mAnimation;
    @BindView(R.id.im_splash)
    TextView imageView;
    private static final int TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        imageView.setAnimation(mAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);

    }
}
