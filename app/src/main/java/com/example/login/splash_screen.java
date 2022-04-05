package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {

    private static int timer = 400;

    ImageView imageView;
    TextView textView1, textView2;
    Animation upperanimation, bottomanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();



        imageView = findViewById(R.id.splash_image);
        textView1 = findViewById(R.id.splash_text);
        textView2 = findViewById(R.id.splash_sub_title);



        upperanimation = AnimationUtils.loadAnimation(this,R.anim.upper_animation);
        bottomanimation = AnimationUtils.loadAnimation(this,R.anim.button_animation);


        imageView.setAnimation(upperanimation);
        textView1.setAnimation(bottomanimation);
        textView2.setAnimation(bottomanimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this,login.class);
                startActivity(intent);
                finish();
            }
        },timer);




    }
}