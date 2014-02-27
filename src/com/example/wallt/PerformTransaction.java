package com.example.wallt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PerformTransaction extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perform_transaction);
		TextView depositButton = (TextView) findViewById(R.id.deposit);
		TextView withdrawButton = (TextView) findViewById(R.id.withdraw);
		final TextView amount = (TextView) findViewById(R.id.transactionAmount);
        Intent extras = getIntent();
        Bundle b = extras.getExtras();
        final int key = (Integer) b.get("account");
        final DataBaseManager db = new DataBaseManager(this);
        final BankAccount account = db.getOneAccount(key);
        
        depositButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		double balanceChange = Integer.parseInt(amount.getText().toString());
        		db.updateBalance(key, account.getBalance() + balanceChange);
        	}
        });
        
        withdrawButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		double balanceChange = Integer.parseInt(amount.getText().toString());
        		db.updateBalance(key, account.getBalance() - balanceChange);
        	}
        });

  
	}

}
