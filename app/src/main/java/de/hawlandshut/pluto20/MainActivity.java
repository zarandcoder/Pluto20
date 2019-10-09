package de.hawlandshut.pluto20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "xx Main Activity";

    //Similar like main()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "oncleate called");

        //Small comment on the bottom appears for some seconds
        Toast.makeText(getApplicationContext(), "Konto wurde angelegt",
                Toast.LENGTH_SHORT).show();
    }
}