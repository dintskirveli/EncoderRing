package com.nijine.encoderring;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PasswordList extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is where passwords will be saved");
        setContentView(textview);
    }
}