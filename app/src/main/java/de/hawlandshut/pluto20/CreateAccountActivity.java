package de.hawlandshut.pluto20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "xx CreateAcc Activity";

    EditText mEditTextMail;
    EditText mEditTextPassword1;
    EditText mEditTextPassword2;
    Button mButtonCreateAccount;

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
        switch (i) {
            case R.id.createAccountButtonCreateAccount:
                Log.d(TAG, "Create Account Button pressed");
                return;
            default:
                return;
        }
    }
}
