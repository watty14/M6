package com.example.wallt;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;

public class BankAccountListActivity extends ListActivity {

	private String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bankaccountlist_activity);
		Intent extras = getIntent();
		Bundle b = extras.getExtras();
		username = (String) b.get("username");
		
		final ListView list = (ListView) findViewById(R.id.list);
		final DataBaseManager db = new DataBaseManager(this);
		final LinkedList<BankAccount> accList = db.getBankAccounts(username);
		
		ArrayAdapter<BankAccount> adapter = new ArrayAdapter<BankAccount>(this,
		        android.R.layout.simple_list_item_1, accList);
		    setListAdapter(adapter);
	
	TextView AddAccount = (TextView) findViewById(R.id.add_button);
    
    // Listener for Login button
    AddAccount.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
      	  Intent i = new Intent(getApplicationContext(), RegisterAccount.class);
		  i.putExtra("username", username);
      	  startActivity(i);
        }
    });

	
	TextView RemoveAccount = (TextView) findViewById(R.id.remove_button);
    
    // Listener for Login button
    RemoveAccount.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
      	  
        }
    });

	}
	
	}
