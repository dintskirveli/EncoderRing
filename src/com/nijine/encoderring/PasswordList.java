package com.nijine.encoderring;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordList extends ListActivity{

	private ArrayList PASSWORDS;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initPasswords();
		setListAdapter(new ArrayAdapter<passwordItem>(this, R.layout.list_item, PASSWORDS));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				//Toast.makeText(getApplicationContext(), ((TextView) view).getText(),Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), PASSWORDS.get(position).toString(), Toast.LENGTH_SHORT).show();
			}
		});
	}
	public void initPasswords(){
		PASSWORDS = new ArrayList();
		PASSWORDS.add(new passwordItem("facebook", "bob", "123456"));
	}
	private class passwordItem {
		String website;
		String username;
		String password;
		
		public passwordItem(String web, String user, String pass){
			website = web;
			username = user;
			password = pass;
		}
		public String toString(){
			return website;
		}
	}
	
	
}