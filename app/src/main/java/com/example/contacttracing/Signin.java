package com.example.contacttracing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Signin extends AppCompatActivity {
    Button callsignUp,loginBtn;
    ImageView signinLogo;
    TextView signinTitle,signinTag;
    TextInputLayout username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        callsignUp = (Button)findViewById(R.id.callsignup);
        signinLogo = (ImageView)findViewById(R.id.signin_icon);
        signinTitle = (TextView)findViewById(R.id.signin_name);
        signinTag = (TextView)findViewById(R.id.signin_tag);
        username = (TextInputLayout)findViewById(R.id.username);
        password = (TextInputLayout)findViewById(R.id.password);
        loginBtn = (Button)findViewById(R.id.loginbtn);



        callsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signin.this,Signup.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View,String>(signinLogo,"logo_image");
                pairs[1] = new Pair<View,String>(signinTitle,"title_tag");
                pairs[2] = new Pair<View,String>(signinTag,"title_text");
                pairs[3] = new Pair<View,String>(username,"signin_usrname");
                pairs[4] = new Pair<View,String>(password,"signin_pswrd");
                pairs[5] = new Pair<View,String>(loginBtn,"btn1_trans");
                pairs[6] = new Pair<View,String>(callsignUp,"btn2_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signin.this, pairs);

                startActivity(intent,options.toBundle());

            }
        });
    }
}
