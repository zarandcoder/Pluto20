package de.hawlandshut.pluto20.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

import de.hawlandshut.pluto20.R;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "xx PostActivity";


    private EditText mEditTextTitle;
    private EditText mEditTextMessage;
    private Button mButtonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mEditTextTitle = findViewById(R.id.post_edittext_title);
        mEditTextMessage = findViewById(R.id.post_edittext_text);
        mButtonSend = findViewById(R.id.post_button_send);

        mButtonSend.setOnClickListener(this);

        mEditTextMessage.setText("Hello TextMessage");
        mEditTextTitle.setText("Hello TextTitle");
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.post_button_send:

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user == null) {
                    Log.d(TAG, "Severe Error: User is null in Posting");
                    finish();
                }

                Map<String, Object> postMap = new HashMap<>();
                postMap.put("uid", user.getUid());
                postMap.put("author", user.getEmail());
                postMap.put("title", mEditTextTitle.getText().toString());
                postMap.put("body", mEditTextMessage.getText().toString());
                postMap.put("timestamp", ServerValue.TIMESTAMP);

                DatabaseReference mDatabase = FirebaseDatabase.getInstance()
                        .getReference("posts/");

                mDatabase.push().setValue(postMap);
                finish();

                return;
        }
    }
}
