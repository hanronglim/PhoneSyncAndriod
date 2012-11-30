package com.writon.dataccess;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.writon.phonesyncandriod.JSONParser;

public class PhoneBookEntry {

	
	String  contactType;
	int countryCode;
	int phoneNumber;
	String linkedUser;
	String contactName;
	
	 // contacts JSONArray
	    JSONArray contacts = null;
	 
	    public PhoneBookEntry (){
	    }
	    
	
	public PhoneBookEntry (String contactName, String contactType,int countryCode, int phoneNumber, String linkedUser) {
		this.contactName = contactName;
		this.contactType = contactType;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.linkedUser = linkedUser;
		
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLinkedUser() {
		return linkedUser;
	}
	public void setLinkedUser(String linkedUser) {
		this.linkedUser = linkedUser;
	}

@SuppressWarnings("null")
public ArrayList<PhoneBookEntry> getPhoneBookNumber(String PHONENUMBERENTRYID, String CONTACTNAME,String COUNTRYCODE, String CURRENTPHONENUMBER,String LINKEDTOUSER,String url) throws JSONException{
	System.out.println ("URL:" + url); 
	JSONParser jParser = new JSONParser();
	JSONArray jsonArray = new JSONArray(jParser.readTwitterFeed(url));
	jParser.readTwitterFeed(url);
	 ArrayList<PhoneBookEntry> contactList = new ArrayList<PhoneBookEntry>();
      // getting JSON string from URL
     // JSONObject json = jParser.getJSONFromUrl(url);
	try {
		
	        // Hashmap for ListView
	        Log.i("ARRAY", ""+ url);
System.out.println ("URL:" + url);
		   // Creating JSON Parser instance
        // Getting Array of Contacts
        
        // looping through All Contacts
        for(int i = 0; i < jsonArray.length(); i++){
           // JSONObject c = contacts.getJSONObject(i);
        	JSONObject jsonObject = jsonArray.getJSONObject(i);
            // Storing each json item in variable
            String name = jsonObject.getString(CONTACTNAME);
            JSONObject phone = jsonObject.getJSONObject(CURRENTPHONENUMBER);
            String phoneNumber = phone.getString("phoneNumber");
            String countryCode = phone.getString("countryCode");
            JSONObject linkUser = jsonObject.getJSONObject("retrivedPhoneNumberEntry");
            String linkedToUser = linkUser.getString(LINKEDTOUSER);
          //Log.d(phoneNumber, phoneNumber);
            System.out.println("OK!" +name);
            System.out.println("phnoe" +phoneNumber);
           //PhoneBookEntry pbe= new PhoneBookEntry (name,"Mobile", 65, Integer.parseInt(phoneNumber),"test");
        		  // System.out.println("phnoe obj" +pbe.getContactName());
//contactList.
            // adding HashList to ArrayList
           contactList.add(i,new PhoneBookEntry (name,"Mobile", 65, Integer.parseInt(phoneNumber),"test"));
            System.out.println("ADDED?: " + linkedToUser);
        	//return contactList;
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
	return contactList;
	 
}
	
}
