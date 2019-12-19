package de.hawlandshut.pluto20.web;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import de.hawlandshut.pluto20.R;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "xx Create Acc Activity";

    private EditText mEditTextMail;
    private EditText mEditTextPassword1;
    private EditText mEditTextPassword2;
    private Button mButtonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mEditTextMail = (EditText) findViewById(R.id.createAccountEmail);
        mEditTextPassword1 = (EditText) findViewById(R.id.createAccountPassword1);
        mEditTextPassword2 = (EditText) findViewById(R.id.createAccountPassword2);
        mButtonCreateAccount = (Button) findViewById(R.id.createAccountButtonCreateAccount);

        //Register listener
        mButtonCreateAccount.setOnClickListener(this);

        // TODO: Remove presets later
        mEditTextMail.setText("vadim@zaripov.website");
        mEditTextPassword1.setText("1qayxsw2");
        mEditTextPassword2.setText("1qayxsw2");

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch(i) {
            case R.id.createAccountButtonCreateAccount:
                Log.d(TAG, "Create Account Button pressed");
                doCreateAccount();
                return;
            default:
                return;
        }
    }


    private void doCreateAccount() {

        String  email = mEditTextMail.getText().toString();
        String password1 = mEditTextPassword1.getText().toString();
        String password2 = mEditTextPassword2.getText().toString();

        //TODO Password validation: password equal?
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "User created", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "User creation failed", Toast.LENGTH_LONG).show();
                    Log.d(TAG, task.getException().getLocalizedMessage());
                }
            }
        });
    }
}