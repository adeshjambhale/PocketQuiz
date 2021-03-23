package com.example.adesh.pocketquiz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends Activity implements View.OnClickListener {

    private Button register_user;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextF_Name;
    private EditText editTextL_Name;
    private TextView textViewSignIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_page);

        progressDialog =new ProgressDialog(this);

        register_user = (Button) findViewById(R.id.register_user);
        editTextEmail =(EditText)  findViewById(R.id.username);
        editTextPassword =(EditText) findViewById(R.id.password);
        editTextF_Name =(EditText)  findViewById(R.id.First_name);
        editTextL_Name =(EditText)  findViewById(R.id.Last_name);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);
        register_user.setOnClickListener(this);

        //initializing firebase auth object
        firebaseAuth=FirebaseAuth.getInstance();


        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginPage.class));
            }
        });
    }


    private void registerUser(){
        final String Firstname = editTextF_Name.getText().toString().trim();
        final String Lastname = editTextL_Name.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();

        if (Firstname.isEmpty()) {
            //Firstname is empty
            Toast.makeText(this,"Please Enter First Name",Toast.LENGTH_SHORT).show();
            return;
        }

        if (Lastname.isEmpty()) {
            //Lastname is empty
            Toast.makeText(this,"Please Enter Last Name",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email) ){
            //email is empty
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(Password) ){
            //password is empty
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(
                                    Firstname,
                                    Lastname,
                                    email
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), LoginPage.class));
                                    } else {
                                        Toast.makeText(RegisterPage.this, "Could Not Register..Please Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(RegisterPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });

    }

    @Override
    public void onClick(View v) {
        if (v == register_user) {
            registerUser();
        }

    }
}