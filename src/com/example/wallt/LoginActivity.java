/**
 * the log in form page
 */
package com.example.wallt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
 
public class LoginActivity extends Activity {
	
	private DataBaseManager db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.login_activity);
        db = new DataBaseManager(this);
        
        TextView login = (TextView) findViewById(R.id.btnLogin);
        
        final TextView userName = (TextView) findViewById(R.id.usernameField);
        final TextView passWord = (TextView) findViewById(R.id.passwordField);
        
        Intent extras = getIntent();
        Bundle b = extras.getExtras();
        if (b != null) {
        	userName.setText((String) b.get("registeredUserName"));
        }
        
        login.setOnClickListener(new View.OnClickListener() {
 
        	
            public void onClick(View v) {
            	//this needs to be replaced with a SQL query
            	if (userName.getText().toString().equals("admin")
            			&& passWord.getText().toString().equals("pass123")) {
            		Intent i = new Intent(getApplicationContext(), AdminHub.class);
            		startActivity(i);
            	}
            	else if (db.loginVerify(userName.getText().toString(),
            			passWord.getText().toString())) {
	            	Intent i = new Intent(getApplicationContext(), UIActivity.class);
	            	i.putExtra("username", userName.getText().toString());
	          	  	startActivity(i);
            	} else {
            		Toast.makeText(getApplicationContext(), "Incorrect User ID or Password.",
            				Toast.LENGTH_LONG).show();
            		passWord.setText("");
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