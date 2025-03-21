package com.company.guessingnumbergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    Animation animationImage, animationText ;//creating 2 objects from animation class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        //load the animation files that i created for these objects
        animationImage = AnimationUtils.loadAnimation(this,R.anim.image_animation);
        animationText = AnimationUtils.loadAnimation(this,R.anim.text_animation);

        //start the animation
        imageView.setAnimation(animationImage);
        textView.setAnimation(animationText);

        /*after the animation is finished, the main activity needs to be open(we use
         CountDownTimer(  ,  ) class
         :the 1st parameter is after how many seconds that we want the main activity to open
         : the 2nd will be the time will increase by one second interval to reach 6sec */
        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long l) {
                /*this method will state what will happen at each second */
            }

            @Override
            public void onFinish() {
                /*this method states what happens when the time expires*/
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();//removed this activity from the back stack
            }
        }.start();//start the CountDownTimer method
    }
}