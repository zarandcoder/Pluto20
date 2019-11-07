package de.hawlandshut.pluto20;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import de.hawlandshut.pluto20.model.Post;
import de.hawlandshut.pluto20.test.TestData;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "xx Main Activity";

    private ListView mListView;
    private ArrayList<Post> mPostList;
    private ArrayAdapter<Post> mAdapter;


    //Only for testing purposes
    private static final String TEST_USERNAME = "Hans Huber";
    private static final String TEST_MAIL = "hans.huber@gmail.com";
    private static final String TEST_PASSWORD ="123456";
    private static final String TEST_NEW_DISPLAY_NAME ="Sepp Maier";


    //Similar like main() first method that starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.mainListViewMessages);

        mPostList = (ArrayList<Post>) TestData.createTestData();
        mAdapter = new ArrayAdapter<Post>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, mPostList) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                Post post = getItem(getCount() - position - 1);

                text1.setText(post.getAuthor());
                text2.setText(post.getTitle() + "\n" + post.getBody());
                return view;
            }
        };

        mListView.setAdapter(mAdapter);
    }

    //Menu programming - inflate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //Create Menu-Events Listener
    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.createTestuser:
                doCreateUser(TEST_MAIL, TEST_PASSWORD);
                return true;
            case R.id.deleteTestuser:
                doDeleteTestUser();
                return true;
            case R.id.testAuthStatus:
                doTestAuth();
                return true;

            case R.id.signInTestuser:
                doSignIn(TEST_MAIL, TEST_PASSWORD);
                return true;

            case R.id.signOutTestuser:
                doTestSignOut();
                return true;

            case R.id.setDisplayName:
                doSetDisplayName(TEST_NEW_DISPLAY_NAME);
                return true;

            case R.id.sendResetPasswordMail:
                doSendResetPasswordMail(TEST_MAIL);
                return true;

            case R.id.sendActivationMail:
                doSendActivationMail();
                return true;

            case R.id.idSignInWithGoogle:
                doSignInWithGoogle();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    //TODO Implement further behaviour
    public void doCreateUser(String x, String y) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(TEST_MAIL, TEST_PASSWORD)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "User creation failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void doDeleteTestUser(){}
    public void doTestAuth(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String msg = (user == null) ? "Not authentificated" : ("Authentification successful" + user.getEmail());
    }
    public void doSignIn(String x, String y){}
    public void doTestSignOut(){}
    public void doSetDisplayName(String x){}
    public void doSendResetPasswordMail(String x){}
    public void doSendActivationMail(){}
    public void doSignInWithGoogle(){}

    /*
        Methods from the Application Lifecycle
        Alle Methoden werden hintereinander ausgeführt
    */
/*
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }
*/
    @Override
    protected void onStart() {
        super.onStart();
    }

}