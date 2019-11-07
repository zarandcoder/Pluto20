package de.hawlandshut.pluto20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ManageAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mEmail;
    private TextView mAccountState;
    private TextView mTechnicalId;
    private EditText mPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        mEmail = (TextView) findViewById(R.id.manageAccountEmail);
        mAccountState = (TextView) findViewById(R.id.manageAccountVerificationState);
        mTechnicalId = (TextView) findViewById(R.id.manageAccountTechnicalId);
        mPassword = (EditText) findViewById(R.id.manageAccountPassword);

        mEmail.setText("Mail: " + "user@demo.de");
        mAccountState.setText("Account verified: NO");
        mTechnicalId.setText("ID : " + "uis-1231231");
        mPassword.setText("**********");

        ((Button) findViewById(R.id.manageAccountButtonSignOut)).setOnClickListener(this);
        ((Button) findViewById(R.id.manageAccountButtonSendActivationMail)).setOnClickListener(this);
        ((Button) findViewById(R.id.manageAccountButtonDeleteAccount)).setOnClickListener(this);
    }

    //TODO Implement logic
    public void doDeleteAccount(){}
    public void doSignOut(){}
    public void doSendActivationMail(){}


    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.manageAccountButtonDeleteAccount:
                doDeleteAccount();
                return;
            case R.id.manageAccountButtonSignOut:
                doSignOut();
                return;
            case R.id.manageAccountButtonSendActivationMail:
                doSendActivationMail();
                return;
            default:
                return;
        }
    }
}
