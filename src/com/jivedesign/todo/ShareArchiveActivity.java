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

package com.jivedesign.todo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.jivedesign.todo.R;

import controller.Task;
import controller.task_ListAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ShareArchiveActivity extends Activity {

	String email_address ="";
	Context context = this;
	String text = "You have selected ";
	
	private ListView shareArchiveListView;
	private task_ListAdapter tla;
	private ArrayList<Task> someArchivesList = new ArrayList<Task>();
	
	public ShareArchiveActivity() {
		// TODO Auto-generated constructor stub
	}


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.share_archives);
	    
	    try {
			fileSaverLoader.readObject(this, TaskSingleton.GetArchObject(), "archFile.sav");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
//	    Intent intent = getIntent();
//	    email_address = intent.getStringExtra("email");

	    setup_adapter();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		someArchivesList.clear();
		setup_adapter();
		
	}
	
	
	
	
	public void setup_adapter() {
		
		// Sets up the list of Archives for selection
		
		tla = new task_ListAdapter(this, R.layout.share_archives, TaskSingleton.GetArchObject());
		shareArchiveListView = (ListView) findViewById(R.id.share_archiveList);
		shareArchiveListView.setAdapter(tla);
		
		shareArchiveListView
		.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Task selectedTask = tla.getItem(position);
				
//				int duration = Toast.LENGTH_SHORT;
//				Toast toast = Toast.makeText(context, text + selectedTask.getTaskName(), duration);
//				toast.show();
				
				if (!someArchivesList.contains(selectedTask)) {
					someArchivesList.add(selectedTask);
					view.setBackgroundColor(0xb3FFFFFF);
				} else if (someArchivesList.contains(selectedTask)) {
					someArchivesList.remove(selectedTask);
					view.setBackgroundColor(0x33FFFFFF);
					
				}
				
			}
		});
		
	}
	

	public void emailSomeArchives(View v) {
		
		// Emails a selection of archives
		
		//Log.d("onclick", "*LIST from Share Arch " + someArchivesList.size());
		Emailer.emailTasks(this, "some archives", email_address, someArchivesList);
		
	}
	
	
	public void cancel_share(View v) {
		
		Intent i = new Intent(this, ShareActivity.class);
		startActivity(i);
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

}
