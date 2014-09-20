package com.jivedesign.todo;

import java.lang.reflect.Array;
import java.util.ArrayList;

import controller.Task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class Emailer extends Activity {

	//http://www.tutorialspoint.com/android/android_sending_email.htm
	
	//http://www.tutorialspoint.com/about/about_disclaimer.htm
	//"You are granted permission to access the website and its contents only for the purpose of learning and/or business expansion."
	
	
	public Emailer() {
		// TODO Auto-generated constructor stub
	}

	
	public static void emailTasks(Context context, String list_type, String email_address, ArrayList<Task> taskArray ) {
		
		//http://www.tutorialspoint.com/android/android_sending_email.htm
		
		String email_body = "";
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		
		intent.setData(Uri.parse("mailto:"));
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{ email_address });
		intent.putExtra(Intent.EXTRA_SUBJECT, "A task list of " + list_type);
		
		Log.d("onclick", "*LIST SIZE " + taskArray.size());
		
		for (int i = 0; i < taskArray.size(); i++) {
			
			email_body += 
					"Task name: " + taskArray.get(i).getTaskName() + "\t\t"
					+ "Type: " + taskArray.get(i).getGroup() + "\t"
					+ "Done?: " + taskArray.get(i).getStatus()	+ "\n"				
					;
			Log.d("onclick", "*EMAIL Body " + email_body);
		}
		
		
		
		intent.putExtra(Intent.EXTRA_TEXT   , email_body);
		
		context.startActivity(Intent.createChooser(intent, "Sending email of tasks"));
		
	}
	
	
}
