package com.writon.phonesyncandriod;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.writon.dataccess.PhoneBookEntry;


public class ChangeDetailsActivity extends Activity {

	private static String url = "http://10.0.2.2:8080/phonesync/user/updatedPhoneBook?uid=952f489f-1096-45e3-803e-0578bd139350";
	
	   
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.user_details);
	        
	        Button backbtn = (Button) findViewById(R.id.backbutton);
	        //Button orderButton = (Button)findViewById(R.id.order);

	        backbtn.setOnClickListener(new View.OnClickListener() {

	          @Override
	          public void onClick(View view) {
	            Intent intent = new Intent(/*FirstActivity.this*/ view.getContext(), PhoneSyncMainActivity.class);
	            startActivity(intent);
	          }

	        });

	    }
	        
}
