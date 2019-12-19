package de.hawlandshut.pluto20.web;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hawlandshut.pluto20.R;

public class ManageAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "xx Manage Account acty";

    private TextView mEmail;
    private TextView mAccountState;
    private TextView mTechnicalId;

    private Button mButtonSignOut;
    private Button mButtonDeleteAccount;
    private Button mButtonSendActivationEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = (TextView) findViewById(R.id.manageAccountTechnicalId);
        setContentView(R.layout.activity_manage_account);

        mEmail = findViewById(R.id.manageAccountEmail);
        mAccountState = findViewById(R.id.manageAccountVerificationState);
        mTechnicalId = findViewById(R.id.manageAccountTechnicalId);


        mButtonSignOut = findViewById(R.id.manageAccountButtonSignOut);
        mButtonDeleteAccount = findViewById(R.id.manageAccountButtonDeleteAccount);
        mButtonSendActivationEmail = findViewById(R.id.manageAccountButtonSendActivationMail);


        mButtonSignOut.setOnClickListener(this);
        mButtonDeleteAccount.setOnClickListener(this);
        mButtonSendActivationEmail.setOnClickListener(this);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            finish();
        }

        mEmail.setText("Email: " + user.getEmail());
        mTechnicalId.setText("Technical ID: " + user.getUid());
        mAccountState.setText("Accout verified: " + user.isEmailVerified());
    }


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
        }
    }


    public void doDeleteAccount() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            Toast.makeText(getApplicationContext(),
                    "Cannot delete Account. You are not signed in!", Toast.LENGTH_LONG).show();
            return;
        }

        user.delete().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Account deleted!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Account deletion failed. Try to sign out and sign in again.",
                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, task.getException().getLocalizedMessage());
                }
            }
        });
    }


    public void doSendActivationMail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            Toast.makeText(getApplicationContext(),
                    "Sending not possible. You are not signed in!", Toast.LENGTH_LONG).show();
            return;
        }

        if(user.isEmailVerified()) {
            Toast.makeText(getApplicationContext(),
                    "Account already verified!", Toast.LENGTH_LONG).show();
            return;
        }
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Verification mail sent!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Sending mail failed (Check log messages!)",
                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, task.getException().getLocalizedMessage());
                }
            }
        });
    }

    public void doSignOut() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            Toast.makeText(getApplicationContext(),
                    "You are not signed in!", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseAuth.getInstance().signOut();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) {
            Toast.makeText(getApplicationContext(),
                    "Signed out!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Signed out failed!", Toast.LENGTH_LONG).show();
        }
    }
}
