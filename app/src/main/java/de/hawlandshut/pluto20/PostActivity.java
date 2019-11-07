package de.hawlandshut.pluto20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "xx PostActivity";

    private EditText mPostTitle;
    private EditText mPostBody;
    private Button mButtonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mPostTitle = (EditText) findViewById(R.id.postTitle);
        mPostBody = (EditText) findViewById(R.id.postText);
        mButtonSend = (Button) findViewById(R.id.postButtonPost);
    }

    //TODO implement method
    public void doPost() {}
    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.postButtonPost:
                doPost();
                return;
            default:
                return;
        }
    }
}
