package com.example.contacttracing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int INTRO_SCREEN = 5000;
    //Variables for Animation
    Animation iconanim,titleanim;
    ImageView icon;
    TextView title,tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Load Animation for intro screen
        iconanim = AnimationUtils.loadAnimation(this, R.anim.icon_animation);
        titleanim=AnimationUtils.loadAnimation(this, R.anim.title_animation);

        //definitions
        icon = findViewById(R.id.icon);
        title = (TextView)findViewById(R.id.title);
        tagline = (TextView)findViewById(R.id.tagline);

        icon.setAnimation(iconanim);
        title.setAnimation(titleanim);
        tagline.setAnimation(titleanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Signin.class);
                Pair[] pairs = new Pair[2];
                pairs[0] =  new Pair<View,String>(icon,"logo_image");
                pairs[1] =  new Pair<View,String>(title,"title_text");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent, options.toBundle());
            }
        }, INTRO_SCREEN);

    }
}
