package com.writon.phonesyncandriod;

import java.util.ArrayList;
import com.writon.dataccess.PhoneBookEntry;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PhoneSyncMainActivity extends ListActivity {

	private static String url = "http://10.0.2.2:8080/phonesync/user/updatedPhoneBook?uid=952f489f-1096-45e3-803e-0578bd139350";
	  private ProgressDialog m_ProgressDialog = null; 
	    private ArrayList<PhoneBookEntry> m_orders = null;
	    private PhoneSyncListAdapter m_adapter;
	    private Runnable viewPhoneSyncList;
	    private static final String CONTACTNAME = "contactName";
	    private static final String PHONENUMBERENTRYID = "phoneNumberEntryId";
	    private static final String CURRENTPHONENUMBER= "currentPhoneNumber";
	    private static final String COUNTRYCODE= "countryCode";
	    private static final String LINKEDTOUSER= "linkedToUser";
	   
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_phone_sync_main);
	        Button btn = (Button) findViewById(R.id.button1);
	        //Button orderButton = (Button)findViewById(R.id.order);

	        btn.setOnClickListener(new View.OnClickListener() {

	          @Override
	          public void onClick(View view) {
	            Intent intent = new Intent(/*FirstActivity.this*/ view.getContext(), ChangeDetailsActivity.class);
	            startActivity(intent);
	          }

	        });
	        m_orders = new ArrayList<PhoneBookEntry>();
	        this.m_adapter = new PhoneSyncListAdapter(this, R.layout.row, m_orders);
	        setListAdapter(this.m_adapter);
	        
	        viewPhoneSyncList = new Runnable(){
	            @Override
	            public void run() {
	                getPhoneSyncList();
	            }
	        };
	        Thread thread =  new Thread(null, viewPhoneSyncList, "MagentoBackground");
	        thread.start();
	        m_ProgressDialog = ProgressDialog.show(PhoneSyncMainActivity.this,    
	              "Please wait...", "Retrieving data ...", true);
	    }
	    private Runnable returnRes = new Runnable() {

	        @Override
	        public void run() {
	            if(m_orders != null && m_orders.size() > 0){
	                m_adapter.notifyDataSetChanged();
	                for(int i=0;i<m_orders.size();i++)
	                m_adapter.add(m_orders.get(i));
	            }
	            m_ProgressDialog.dismiss();
	            m_adapter.notifyDataSetChanged();
	        }
	    };
	    private void getPhoneSyncList(){
	    	 try{
	              m_orders = new ArrayList<PhoneBookEntry>();
	              PhoneBookEntry pb = new PhoneBookEntry();
	              m_orders = pb.getPhoneBookNumber(PHONENUMBERENTRYID, CONTACTNAME, COUNTRYCODE,CURRENTPHONENUMBER,LINKEDTOUSER, url);
	              //getPhoneBookNumber(String PHONENUMBERENTRYID, String CONTACTNAME,String COUNTRYCODE, String CURRENTPHONENUMBER,String LINKEDTOUSER,String url) throws 
	              Thread.sleep(5000);
	             
	            } catch (Exception e) { 
	              Log.e("BACKGROUND_PROC", e.getMessage());
	            }
	            runOnUiThread(returnRes);
	        }
	    private class PhoneSyncListAdapter extends ArrayAdapter<PhoneBookEntry> {

	        private ArrayList<PhoneBookEntry> items;

	        public PhoneSyncListAdapter(Context context, int textViewResourceId, ArrayList<PhoneBookEntry> items) {
	                super(context, textViewResourceId, items);
	                this.items = items;
	        }
	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	                View v = convertView;
	                if (v == null) {
	                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                    v = vi.inflate(R.layout.row, null);
	                }
	                PhoneBookEntry o = items.get(position);
	                if (o != null) {
	                        TextView tt = (TextView) v.findViewById(R.id.toptext);
	                        TextView bt = (TextView) v.findViewById(R.id.bottomtext);
	                        TextView rt = (TextView) v.findViewById(R.id.righttext);
	                        if (tt != null) {
	                              tt.setText("Name: "+o.getContactName());                            }
	                        if(bt != null){
	                              bt.setText("Contact: "+ o.getCountryCode() +" " +o.getPhoneNumber());
	                        }
	                        if(rt != null){
	                        	  if (o.getLinkedUser().length() > 6){
	                              rt.setText("FRIENDS");
	                        	  }
	                        }
	                }
	                return v;
	        }
	}
	}