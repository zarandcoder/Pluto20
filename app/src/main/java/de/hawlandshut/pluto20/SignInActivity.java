package de.hawlandshut.pluto20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "xx SignIn Activity";

    EditText mEditTextEmail;
    EditText mEditTextPassword;

    Button mButtonSignIn;
    Button mButtonResetPassword;
    Button mButtonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mEditTextEmail = (EditText) findViewById(R.id.signInEmail);
        mEditTextPassword =(EditText) findViewById(R.id.signInPassword);
        mButtonSignIn = (Button) findViewById(R.id.sign_in_button_sign_in);
        mButtonResetPassword = (Button) findViewById(R.id.sign_in_button_reset_password);
        mButtonCreateAccount = (Button) findViewById(R.id.sign_in_button_create_account);

        //Register Listener "this" = implementiert einen OnClickListener
        mButtonSignIn.setOnClickListener(this);
        mButtonCreateAccount.setOnClickListener(this);
        mButtonResetPassword.setOnClickListener(this);

        mEditTextEmail.setText("vadim.zaripov@web.de");
        mEditTextPassword.setText("ketchup&mayo");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        int i = v.getId();
        switch (i) {

            case R.id.sign_in_button_create_account:
                intent = new Intent(getApplication(), CreateAccountActivity.class);
                startActivity(intent);
                return;
            case R.id.sign_in_button_sign_in:
                doSignIn();
                return;
            case R.id.sign_in_button_reset_password:
                doSendResetPasswordMail();
                return;
        }
    }


    private void doSignIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            Toast.makeText(getApplicationContext(), "You are signed in. Sign out first",
                    Toast.LENGTH_LONG).show();
            return;
        }

        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Signed in!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Signed in failed!(Check log messages)",
                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, task.getException().getLocalizedMessage());
                }
            }
        });
    }

    private void doSendResetPasswordMail() {
        String email = mEditTextEmail.getText().toString();
        FirebaseAuth.getInstance().sendPasswordResetEmail( email ).addOnCompleteListener(
                this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText( getApplicationContext(), "Mail sent!.",
                                    Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText( getApplicationContext(),
                                    "Sending mail failed!(Check log messages)",
                                    Toast.LENGTH_LONG).show();
                            Log.d(TAG,task.getException().getLocalizedMessage());
                        }
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
        // Check, if we have a user. This can only happen, if we return from CreateAccount
        FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            finish();
        }
    }
}


