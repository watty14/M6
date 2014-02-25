package com.example.wallt;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class UIActivity extends ListActivity {
	
	private String username;
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Intent extras = getIntent();
		Bundle b = extras.getExtras();
		username = (String) b.get("username");
		String[] values = new String[] { "Accounts", "Transactions", "Budgets", "Cash Flow",
		    "Spending", "Income" };
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
	}

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
	  if(position == 0) {
		  Intent i = new Intent(getApplicationContext(), BankAccountListActivity.class);
		  i.putExtra("username", username);
		  startActivity(i);
	  }
//    String item = (String) getListAdapter().getItem(position);
//    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
  }
} 