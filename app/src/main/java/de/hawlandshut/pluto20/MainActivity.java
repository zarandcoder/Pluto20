package de.hawlandshut.pluto20;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import de.hawlandshut.pluto20.model.Post;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "xx Main Activity";

    private ListView mListView;
    private ArrayList<Post> mPostList;
    private ArrayAdapter<Post> mAdapter;


    //Only for testing purposes
    private static final String TEST_MAIL = "hans.huber@gmail.com";
    private static final String TEST_PASSWORD ="123456";

    //Connection to Database
    boolean mListenerIsRunning;
    ChildEventListener mCEL;
    Query mQuery;

    //Similar like main() first method that starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.mainListViewMessages);

        //Initialize Post List
        mPostList = new ArrayList<Post>();

        mAdapter = new ArrayAdapter<Post>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, mPostList) {

            @NotNull
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                Post post = getItem(getCount() - 1 - position);

                text1.setText(post.getAuthor() + " (" + post.getTitle() + " )");
                text2.setText(post.getBody());
                return view;
            }
        };

        //Adapter der listView zuordnen
        mListView.setAdapter(mAdapter);

        //Initialize Query and CEL
        mCEL = getChildEventListener();
        mQuery = FirebaseDatabase.getInstance().getReference().child("posts/").limitToLast(5);

        mListenerIsRunning = false;

    }


    private ChildEventListener getChildEventListener() {

        ChildEventListener cel = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "Child added : " + dataSnapshot.getKey());
                //Process the received post
                Post p = Post.fromSnapShot(dataSnapshot);
                mPostList.add(p);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "Child changed : " + dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "Child deleted : " + dataSnapshot.getKey());
                String key = dataSnapshot.getKey();
                for(int i = 0; i < mPostList.size(); i++) {
                    if(key.equals(mPostList.get(i).getFirebaseKey())) {
                        mPostList.remove(i);
                        break;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "Child moved : " + dataSnapshot.getKey());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Listener cancelled.");
                mListenerIsRunning = false;
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

            case R.id.menu_simulate_crash:
                Crashlytics.getInstance().crash();

            case R.id.menu_manage_account:
                //Goto ManageAccount
                intent = new Intent(getApplication(), ManageAccountActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_post:
                intent = new Intent(getApplication(), PostActivity.class);
                startActivity(intent);
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
            resetApp();

            mPostList.clear();
            mAdapter.notifyDataSetChanged();

            Intent intent = new Intent(getApplication(), SignInActivity.class);
            startActivity(intent);

        } else {
            //Start listener if user available
            if (!mListenerIsRunning) {

                mPostList.clear();
                mAdapter.notifyDataSetChanged();

                mQuery.addChildEventListener(mCEL);
                mListenerIsRunning = true;
            }
        }
    }

    void resetApp(){

        if (mListenerIsRunning){
            mQuery.removeEventListener( mCEL );
            mListenerIsRunning = false;
        }

        mPostList.clear();
        mAdapter.notifyDataSetChanged();
    }
}