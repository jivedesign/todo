package com.jivedesign.todo;

import java.lang.reflect.Array;
import java.util.ArrayList;

import controller.Task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Emailer extends Activity {

	static Context context;
	//http://www.tutorialspoint.com/android/android_sending_email.htm
	
	//http://www.tutorialspoint.com/about/about_disclaimer.htm
	//"You are granted permission to access the website and its contents only for the purpose of learning and/or business expansion."
	
	
	public Emailer() {
		// TODO Auto-generated constructor stub
	}

	
	public static void emailTasks(String email_address, ArrayList<Task> taskArray) {
//		
//		Intent intent = new Intent(Intent.ACTION_SEND);
//		
//		intent.setData(Uri.parse("mailto:"));
//		intent.setType("plain/text");
//		
//		intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"jhn@ualberta.ca"});
//		intent.putExtra(Intent.EXTRA_SUBJECT, "Test email");
//		intent.putExtra(Intent.EXTRA_TEXT   , "body of email");
//		
//		startActivity(Intent.createChooser(intent, "Send Mail Using :"));
//		
//		
//		Intent email = new Intent(Intent.ACTION_SEND);
//		email.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@gmail.com"});        
//		email.putExtra(Intent.EXTRA_SUBJECT, "Sunject Text Here..");
//		email.putExtra(Intent.EXTRA_TEXT, "");
//		email.setType("message/rfc822");
//		startActivity(Intent.createChooser(email, "Send Mail Using :"));
		
	}
	
	
}
