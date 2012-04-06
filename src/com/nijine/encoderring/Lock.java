package com.nijine.encoderring;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Lock extends Activity {

private EditText password;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock);
        password = (EditText)findViewById(R.id.password);
        
/* if password exists
 * create one edittextview
 * create one button, set onclikc to checkppassword
 * 
 * if password doesnt exist
 * create two edittextviews
 * create one button, onclick compares the two strings and sets the password if they are equal
 * 
 */
    }
    public void checkPassword(View view) {
    	String input = password.getText().toString();
    	if (input.equals("awesome")) {
    		password.setText("");
    		Intent myIntent = new Intent(Lock.this, TabWidget.class);
    		Lock.this.startActivity(myIntent);
    	}
    }
    
}

	