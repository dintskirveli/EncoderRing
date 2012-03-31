package com.nijine.encoderring;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EncoderRing extends Activity {

private EditText simple;
private EditText complex;
private long time;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        simple = (EditText)findViewById(R.id.textInput);
        complex = (EditText)findViewById(R.id.textOutput);
    }


    public void Calculate(View view) {
    	String input = simple.getText().toString();
    	byte[] mutation = input.getBytes();
    	int[] timeArray = new int[20];
    	int i = 0;
    	int j = mutation.length - 1;
    	time = System.currentTimeMillis();
    	
    	while (time > 0) {
    		long digit = time % 10;
    		timeArray[i] = (int) digit;
    		i++;
    		time /= 10;
    	}
    	
    	while (i > -1) {
    		if (time >= 38)
    			time -= timeArray[i];
    		if (time < 38)
    			time += timeArray[i];
    		i--;
    	}
    	
    	while (j > -1) {
    		if (mutation[j] < 79) {
    			mutation[j] = (byte) (mutation[j] + time);
    		}
    		if (mutation[j] > 79) {
    			mutation[j] = (byte) (mutation[j] - time);
    		}
    		j--;
    	}
    	
    	// if (mutation[0] > 0)
    	//     mutation[0] = (byte) (mutation[0] - timeArray[0]);
    	
    	// input = String.valueOf(timeArray[0]);
    	
    	// byte[] bite = input.getBytes();
    	
    	// input = Byte.toString(mutation[0]);
    	
    	input = new String(mutation);
    	
    	complex.setText(input);
    }
    
    public void initializeTime() {
    	time = System.currentTimeMillis();
	}
    
    public int[] makeArrayofTime(long clock) {
    	int[] arrayofTime = new int[15];
    	
    	for (int i = 0; clock > 0; i++) {
    		long digit = clock % 10;
    		arrayofTime[i] = (int) digit;
    		clock /= 10;
    	}
    	
    	return arrayofTime;
    }
    
    public void scrambleTime(int[] arrayofTime) {
    	double subtotal = 0;
    	double[] bits = new double[5];
    	
    	for (int i = 0; i < 15; i+=3) {
    		for (int j = i; j < i + 3; j++) {
    			subtotal += arrayofTime[j] * Math.pow(10, j - i);
    		}
    	bits[i / 3] = subtotal;
    	}
    	
    	// *continue here*
    }
    
}

	