package com.example.wallt;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.LinkedList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;

public class TransactionActivity extends ListActivity {

	private String username;
	LinkedList<BankAccount> accList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		
		Intent extras = getIntent();
		Bundle b = extras.getExtras();
		username = (String) b.getString("username");
		final DataBaseManager db = new DataBaseManager(this);
		accList = db.getBankAccounts(username);
		ArrayAdapter<BankAccount> adapter = new ArrayAdapter<BankAccount>(this, 
				android.R.layout.simple_list_item_1, accList);
		setListAdapter(adapter);
		
		TextView removeAccount = (TextView) findViewById(R.id.back_button);

		removeAccount.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				db.closeDataBase();
				finish();
			}
		});

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		BankAccount desired = accList.get(position);
		Intent i = new Intent(getApplicationContext(), PerformTransaction.class);
	    i.putExtra("account", desired.getKey());
		startActivity(i);

	  }
	
}