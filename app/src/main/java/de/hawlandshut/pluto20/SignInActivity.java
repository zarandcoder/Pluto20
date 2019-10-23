package de.hawlandshut.pluto20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "xx SignIn Activity";

    EditText mEditTextEmail;
    EditText mEditTextPassword;
    Button mButtonSignIn;
    Button mButtonCreateAccount;
    Button mButtonResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mEditTextEmail = (EditText) findViewById(R.id.signInEmail);
        mEditTextPassword =(EditText) findViewById(R.id.signInPassword);
        mButtonSignIn = (Button) findViewById(R.id.signInButtonSignIn);
        mButtonCreateAccount = (Button) findViewById(R.id.signInButtonResetPassword);
        mButtonResetPassword = (Button) findViewById(R.id.signInButtonCreateAccount);

        //Register Listener "this" = implementiert einen OnClickListener
        mButtonSignIn.setOnClickListener(this);
        mButtonCreateAccount.setOnClickListener(this);
        mButtonResetPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.signInButtonSignIn:
                //TODO: doSignIn();
                Log.d(TAG, "SignIn pressed");
                return;
            case R.id.signInButtonResetPassword:
                //TODO: doResetPassword();
                Log.d(TAG, "ResetPassword pressed");
                return;
            case R.id.signInButtonCreateAccount:
                //TODO: doCreateAccount();
                Log.d(TAG, "CreateAccount pressed");
                return;
            default:
                return;
        }
    }
}
