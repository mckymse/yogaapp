package com.example.contacttracing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    TextInputLayout signupName,sighnupEmail,signupPswrd,signupPhn;
    Button regBtn,regTosigninBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

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

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                String name = signupName.getEditText().getText().toString();
                String email = sighnupEmail.getEditText().getText().toString();
                String pswrd = signupPswrd.getEditText().getText().toString();
                String phno = signupPhn.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name,email,pswrd,phno);

                reference.child(phno).setValue(helperClass);
            }
        });

    }
}
