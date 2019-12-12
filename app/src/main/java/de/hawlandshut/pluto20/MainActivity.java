package de.hawlandshut.pluto20;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hawlandshut.pluto20.model.Post;
import de.hawlandshut.pluto20.test.TestData;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "xx Main Activity";

    private ListView mListView;
    private ArrayList<Post> mPostList;
    private ArrayAdapter<Post> mAdapter;


    //Only for testing purposes
    private static final String TEST_MAIL = "hans.huber@gmail.com";
    private static final String TEST_PASSWORD ="123456";

    //Connection to Databse
    ChildEventListener mCEL;
    Query mQuerry;

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

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                Post post = getItem(getCount() - position - 1);

                text1.setText(post.getAuthor());
                text2.setText(post.getTitle() + "\n" + post.getBody());
                return view;
            }
        };

        //Adapter der listView zuordnen
        mListView.setAdapter(mAdapter);

        //Query und CEL initialisieren
        mCEL = getChildEventListener();
        mQuerry = FirebaseDatabase.getInstance().getReference().child("posts/");
        mQuerry.addChildEventListener(mCEL);
    }


    private ChildEventListener getChildEventListener() {
        ChildEventListener cel = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "Child added : " + dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Log.d(TAG, "Child changed : " + dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "Child deleted : " + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "Child moved : " + dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Listener cancelled.");
            }
        };
        return cel;
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

            case R.id.menu_manage_account:
                //Goto ManageAccount
                intent = new Intent(getApplication(), ManageAccountActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_post:
                intent = new Intent(getApplication(), PostActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_write:
                //TODO Implement Testwriting
                Map<String, Object> postMap = new HashMap<>();
                postMap.put("uid", "das ist die UID");
                postMap.put("author", "my Author");
                postMap.put("title", "my Title");
                postMap.put("body", "my Body");
                postMap.put("timestamp", ServerValue.TIMESTAMP);

                //Schreiben
                DatabaseReference mDataBase;
                try {
                    mDataBase = FirebaseDatabase.getInstance().getReference().child("posts/");
                    mDataBase.push().setValue(postMap);
                } catch (Exception e) {
                    Log.d(TAG, "Fehler beim Schreiben :" + e.getLocalizedMessage());
                }

                return true;

            default:
                return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            //TODO Reset App

            Intent intent = new Intent(getApplication(), SignInActivity.class);
            startActivity(intent);
        }
    }
}