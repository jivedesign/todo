/*
TodoApp: Track Todos, check them off, archive them, and share them

Copyright (C) 2014 Joshua Nguyen jhn@ualberta.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.

*/

/*
 * Emails a list of tasks
 */


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

	// http://www.tutorialspoint.com/android/android_sending_email.htm

	// http://www.tutorialspoint.com/about/about_disclaimer.htm
	// "You are granted permission to access the website and its contents only for the purpose of learning and/or business expansion."

	public Emailer() {
		// TODO Auto-generated constructor stub
	}

	public static void emailTasks(Context context, String list_type,
			String email_address, ArrayList<Task> taskArray) {

		// Emails the list of tasks
		
		// http://www.tutorialspoint.com/android/android_sending_email.htm

		String email_body = "";

		Intent intent = new Intent(Intent.ACTION_SEND);

		intent.setData(Uri.parse("mailto:"));
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email_address });
		intent.putExtra(Intent.EXTRA_SUBJECT, "Todo App: Here's an email of " + list_type);

//		Log.d("onclick", "*LIST SIZE " + taskArray.size());
//		if (taskArray.size() > 0) {
//			Log.d("onclick", "*LIST SIZE " + taskArray.get(0).getTaskName());
//		}
		for (int i = 0; i < taskArray.size(); i++) {

			email_body += "Task name: " + taskArray.get(i).getTaskName()
					+ "\t\t\t" + "Type: " + taskArray.get(i).getGroup() + "\t\t"
					+ "Done?: " + taskArray.get(i).getStatus() + "\n\n";
			//Log.d("onclick", "*EMAIL Body " + email_body);
		}

		intent.putExtra(Intent.EXTRA_TEXT, email_body);

		context.startActivity(Intent.createChooser(intent,
				"Tasks are on their way!"));

	}

}
