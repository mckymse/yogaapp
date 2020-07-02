package com.example.contacttracing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextInputLayout signupName,sighnupEmail,signupPswrd,signupPhn;
    Button regBtn,regTosigninBtn;

   FirebaseAuth firebaseAuth;
   FirebaseFirestore firestore;

   String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = (TextInputLayout)findViewById(R.id.signup_nametxt);
        sighnupEmail = (TextInputLayout)findViewById(R.id.signup_email);
        signupPswrd = (TextInputLayout)findViewById(R.id.signup_pswrd);
        signupPhn = (TextInputLayout)findViewById(R.id.ph_no);

        regBtn = (Button)findViewById(R.id.signupbtn);
        regTosigninBtn = (Button)findViewById(R.id.forgetbtn);

        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Homepage.class));
            finish();
        }

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=sighnupEmail.getEditText().getText().toString().trim();
                String password = signupPswrd.getEditText().getText().toString().trim();
                final String name=signupName.getEditText().getText().toString();
                final String phone=signupPhn.getEditText().getText().toString();
                Log.d(TAG, "onClick: "+email);
                Log.d(TAG,"onclick"+password);
                if(TextUtils.isEmpty(email)){
                    sighnupEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    signupPswrd.setError("Password is required");
                    return;
                }
                if(password.length()<6){
                    signupPswrd.setError("Password must be greater than or equal to 6 characters");
                    return;
                }



                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this,"User Created",Toast.LENGTH_SHORT).show();
                            UserId = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("Users").document(UserId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Name",name);
                            user.put("email",email);
                            user.put("Phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d(TAG,"onSuccess:User Profile is created for"+UserId);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onFailure:"+e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Homepage.class));
                        }
                        else{
                            Toast.makeText(Signup.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onComplete: "+task.getException().getMessage());
                        }
                    }
                });
            }
        });




    }

    public void signin(View view) {
        Intent intent=new Intent(Signup.this,Signin.class);
        startActivity(intent);
    }
}
