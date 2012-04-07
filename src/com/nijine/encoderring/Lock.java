package com.nijine.encoderring;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Lock extends Activity {

final String FILE_NAME = "saved_password";
private EditText passwordEditText;
private EditText newPasswordEditText;
private EditText confirmNewPasswordEditText;
String stored;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout lock = new LinearLayout(this);
        lock.setOrientation(lock.VERTICAL);
        
        PasswordTransformationMethod transMode = new PasswordTransformationMethod();
        try {
        	passwordEditText = new EditText(this);
        	DataInputStream in = new DataInputStream(openFileInput(FILE_NAME));
			stored = in.readUTF();
			in.close();
			Log.d("Stored password", stored);
			Button unlockButton = new Button(this);
			unlockButton.setText("Unlock");
			unlockButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					String one = passwordEditText.getText().toString();
					checkPassword(stored, one);
				}
	        });
			passwordEditText.setTransformationMethod(transMode);
			lock.addView(passwordEditText);
			lock.addView(unlockButton);
			
		} catch (FileNotFoundException e) {
			newPasswordEditText = new EditText(this);
			confirmNewPasswordEditText = new EditText(this);
			Button newPasswordButton = new Button(this);	
			newPasswordButton.setText("Save Password");
			newPasswordButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					String one = newPasswordEditText.getText().toString();
					String two = confirmNewPasswordEditText.getText().toString();
					if (true == checkPassword(one, two)){
						try {
							DataOutputStream out = 
					                new DataOutputStream(openFileOutput(FILE_NAME, v.getContext().MODE_PRIVATE));
					        out.writeUTF(one);
					        out.close();
							Log.d("Written password", one);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
	        });
			newPasswordEditText.setTransformationMethod(transMode);
			confirmNewPasswordEditText.setTransformationMethod(transMode);
			lock.addView(newPasswordEditText);
			lock.addView(confirmNewPasswordEditText);
			lock.addView(newPasswordButton);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setContentView(lock);
    }
    public boolean checkPassword(String one, String two) {
    	if (one.equals(two)) {
    		passwordEditText.setText("");
    		Intent myIntent = new Intent(Lock.this, TabWidget.class);
    		Lock.this.startActivity(myIntent);
    		return true;
    	}
    	else return false;
    }
   
    
}

	