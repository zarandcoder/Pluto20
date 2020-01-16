package de.hawlandshut.pluto20.web;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    public WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showAppVersion() {
        Toast.makeText(mContext, "You are running version 5", Toast.LENGTH_SHORT).show();
    }
}
