package de.hawlandshut.pluto20;

import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "xx Main Activity";

    //Similar like main() first method that starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate() called");
        //Small comment on the bottom appears for some seconds
        Toast.makeText(getApplicationContext(), "Konto wurde angelegt",
                Toast.LENGTH_SHORT).show();
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
        switch (item.getItemId()) {
            case R.id.mainMenuPost:
                Log.d(TAG, "Post was pressed");
                Intent intentPost = new Intent(getApplication(), PostActivity.class);
                startActivity(intentPost);
                return true;
            case R.id.mainMenuSignIn:
                Log.d(TAG, "Go to sign in");
                Intent intentSignIn = new Intent(getApplication(), SignInActivity.class);
                startActivity(intentSignIn);
                return true;
            case R.id.mainMenuCreateAccount:
                Log.d(TAG, "Test was pressed");
                Intent intentCreateAcc = new Intent(getApplication(), CreateAccountActivity.class);
                startActivity(intentCreateAcc);
                return true;
            case R.id.mainMenuManageAccount:
                Log.d(TAG, "ManageAccount was pressed");
                Intent intentManage = new Intent(getApplication(), ManageAccountActivity.class);
                startActivity(intentManage);
                return true;
            case R.id.mainMenuDelete:
                Log.d(TAG, "Delete was pressed");
                Intent intentDelete = new Intent(getApplication(), SignInActivity.class);
                startActivity(intentDelete);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
        Methods from the Application Lifecycle
        Alle Methoden werden hintereinander ausgeführt
    */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

    }

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
}