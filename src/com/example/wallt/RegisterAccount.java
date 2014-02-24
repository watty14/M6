package com.example.wallt;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterAccount extends Activity {
	
	private String username;
	private DataBaseManager db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeraccount_activity);
		db = new DataBaseManager(this);
		Intent extras = getIntent();
		Bundle b = extras.getExtras();
		username = (String) b.get("username");
		
		TextView login = (TextView) findViewById(R.id.create);
		
		final TextView bankField = (TextView) findViewById(R.id.account_bankField);
        final TextView balanceField = (TextView) findViewById(R.id.account_balanceField);
        final TextView accNumber = (TextView) findViewById(R.id.account_numberField);
		
		login.setOnClickListener(new View.OnClickListener() {
 
        	
            public void onClick(View v) {
            	//this needs to be replaced with a SQL query
            	if (!bankField.getText().toString().equals("") && !balanceField.getText().toString().equals("")) {
            		db.addBankAccount(username, accNumber.getText().toString(), bankField.getText().toString(),
            				Integer.parseInt(balanceField.getText().toString()));
            		LinkedList<BankAccount> list = db.getBankAccounts(username);
            		for (BankAccount bank : list) {
            			System.out.println(bank.toString());
            		}
            	} else {
            		Toast.makeText(getApplicationContext(), "Incorrect Information.",
            				Toast.LENGTH_LONG).show();
            	}
            }
        });
 
        TextView back = (TextView) findViewById(R.id.backToMain);
 
        back.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                // Switching back to login screen
            	db.closeDataBase();
                finish();
            }
        });
	}

}
