package com.example.wallt;

import java.util.LinkedList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


public class ListViewTest extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_view_test);
		//final ListView listview = (ListView) findViewById(R.id.listview);
		final LinkedList<String> list = new LinkedList<String>();
		list.add("hello");
		list.add("nano");
		list.add("ocho");
		//final StableArrayAdapter adapter = new StableArrayAdapter(this,
		  //      android.R.layout.simple_list_item_1, list);
		//listview.setAdapter(adapter);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
	}

}
