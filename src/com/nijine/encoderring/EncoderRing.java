package com.nijine.encoderring;

import java.math.BigDecimal;

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
        setContentView(R.layout.encoderring);
        
        simple = (EditText)findViewById(R.id.textInput);
        complex = (EditText)findViewById(R.id.textOutput);
    }


    public void Calculate(View view) {
    	int[] array1 = new int[15];
    	int[] array2 = new int[5];
    	int key;
    	String outputText;
    	
    	
    /*	String input = simple.getText().toString();
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
    	
    	complex.setText(input); */
    	
    	initializeTime();
    	array1 = makeArrayofTime(time);
    	array2 = splitTime(array1);
    	key = key(array2);
    	
    	// outputText = encode(key, simple.getText().toString());
    	// ^ Scrambling algorithm needs work, encode function is incomplete and not fully functional
    	
        outputText = "" + key;
    	
    	complex.setText(outputText);
    	
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
    
    public int[] splitTime(int[] arrayofTime) {
    	int x = 0;
    	int output[] = new int[5];
    	
    	
    	// Initialize output array
    	for (int i = 0; i < 5; i++) {
    		output[i] = 0;
    	}
    	
        // Fill output array with values accordingly (5 sets of 3-digit values)
    	for (int i = 0; i < 5; i++) {
    		output[i] = output[i] + ( arrayofTime[x] * 100 );
    		x++;
    		
    		output[i] = output[i] + ( arrayofTime[x] * 10  );
    		x++;
    		
    		output[i] = output[i] + ( arrayofTime[x] );
    		x++;
    	}
    	
    	return output;
    }
    
    public int key(int[] blocks) {
    	double value = 0;
    	
    	// Do some math
    	value = Math.pow(blocks[0], 5);
    	value = value / (Math.pow(blocks[1], 4));
    	value = value / (Math.pow(blocks[2], 3));
    	value = value / (Math.pow(blocks[3], 2));
    	value = value / (Math.pow(blocks[4], 1));
    	
    	// Turn the math output into a usable number, our "key"
    	while ((value % 10) < 1) {
    		value = value * 10;
    	}
    	
    	value = value * Math.pow(10, 7);
    	
    	// A little more clean-up (cut off the decimals)
    	int output = ((int) value);
    	
    	return output;
    }
    
    public String encode(int key, String input) {
    	String output;
    	int[] numbers = new int[8];
    	int[] numbers2 = new int[8];
    	byte[] convert;
    	int first;
    	
    	for (int i = 0; i < 8; i++) {
    		numbers[i] = key % 10;
    		key /= 10;
    	}
    	
    	for (int i = 0; i < 8; i++) {
    		numbers2[i] = numbers[i];
    	}
    	
    	first = numbers2[0];
    	
    	for (int i = 0; i < 7; i++) {
    		numbers2[i] = numbers2[i] * numbers2[i + 1];
    	}
    	
    	numbers2[7] = numbers2[7] * first;
    	
    	for (int i = 0; i < 8; i++) {
    		numbers2[i] = numbers2[i] + (numbers[i] * 2);
    	}
    	
    	convert = input.getBytes();
    	
    	
    	
    	for (int i = 0; i < convert.length; i++) {
    		if (i > 7) { i = convert.length; }
    		else {
    				convert[i] = (byte) (convert[i] + numbers2[i]); 
    			if (convert[i] < 33) {
    				convert[i] = (byte) (convert[i] - numbers2[i]);
    			} 
    			
    		}
    	}
    	
    	output = new String (convert);
    	
    	return output;
    }
}

	