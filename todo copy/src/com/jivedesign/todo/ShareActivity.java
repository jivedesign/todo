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
 * Share Activity class displays a selection of options for sending todos or archives
 * */

package com.jivedesign.todo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.jivedesign.todo.R;

import controller.Task;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ShareActivity extends Activity {

	String todo_file = "todoFile.sav";
	String arch_file = "archFile.sav";
	private String email_address = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_activity);
		
		try {
			fileSaverLoader.readObject(this, TaskSingleton.GetTodoObject(), todo_file);
			fileSaverLoader.readObject(this, TaskSingleton.GetArchObject(),
					arch_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void emailAll(View v) {

		// Loads all Todos and Archives into one list for sending through email
		
		RadioGroup radioButtonGroup = (RadioGroup) findViewById(R.id.shareRadioMenu);

		int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
		View radioButton = radioButtonGroup.findViewById(radioButtonID);
		int idx = radioButtonGroup.indexOfChild(radioButton);

		switch (idx) {
		case 0:
			ArrayList<Task> emailEverythingList = new ArrayList<Task>();
			emailEverythingList.addAll(TaskSingleton.GetTodoObject());
			emailEverythingList.addAll(TaskSingleton.GetArchObject());
			
			Emailer.emailTasks(this, "everything!", email_address, emailEverythingList);
			break;
		case 1:
			 Emailer.emailTasks(this, "all Todos!", email_address, TaskSingleton.GetTodoObject());
			 break;
		case 2:
			 Emailer.emailTasks(this, "all Archives!", email_address, TaskSingleton.GetArchObject());
			 break;
		default:
			break;
			
		}

	}

	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_todo) {
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
			return true;
		} else if (id == R.id.action_archive) {
			Intent i = new Intent(this, ArchiveActivity.class);
			startActivity(i);
			return true;
		} else if (id == R.id.action_share) {
			Intent i = new Intent(this, ShareActivity.class);
			startActivity(i);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}



	public void toSendTodos(View v) {
		
		// takes user to ShareTodoActivity
		
		Intent i = new Intent(this, ShareTodoActivity.class);
		i.putExtra("email", email_address);
		startActivity(i);
	}

	public void toSendArchives(View v) {
		
		// takes user to SharearchiveActivity
		
		Intent i = new Intent(this, ShareArchiveActivity.class);
		i.putExtra("email", email_address);
		startActivity(i);
	}

}
