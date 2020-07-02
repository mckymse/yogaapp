package com.example.contacttracing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {

    public static final String TAG = "TAG";
    Button callsignUp,loginBtn;
    ImageView signinLogo;
    TextView signinTitle,signinTag;
    TextInputLayout username,password;
    FirebaseAuth firebaseAuth;
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
        firebaseAuth=FirebaseAuth.getInstance();



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=username.getEditText().getText().toString().trim();
                String pass = password.getEditText().getText().toString().trim();

                Log.d(TAG, "onClick: "+email);
                Log.d(TAG, "onClick: "+pass);


                if(TextUtils.isEmpty(email)){
                    username.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    password.setError("Password is required");
                    return;
                }
                if(pass.length()<6){
                    password.setError("Password must be greater than or equal to 6 characters");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signin.this,"Logged in Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Homepage.class));
                        }
                        else{
                            Toast.makeText(Signin.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }

    public void signup(View view) {
        Intent intent = new Intent(Signin.this,Signup.class);
        startActivity(intent);
    }
}
