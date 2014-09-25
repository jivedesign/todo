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
 *  Is the Activity for Archive. Contains a listView of Archived tasks to view and modify
 *  
 */


package com.jivedesign.todo;

import java.io.FileNotFoundException;

import com.jivedesign.todo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import controller.Task;
import controller.archive_ListAdapter;

public class ArchiveActivity extends Activity {

	// public ArchiveActivity() {
	// // TODO Auto-generated constructor stub
	// }

	final Context context = this;
	private ListView archiveListView;
	private archive_ListAdapter ala;
	String todo_file = "todoFile.sav";
	String arch_file = "archFile.sav";

	private int total = 0;
	private int checked = 0;
	private int unchecked = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arch_frag);
		// TODO Auto-generated method stub

		try {
			fileSaverLoader.readObject(this, TaskSingleton.GetTodoObject(), todo_file);
			fileSaverLoader.readObject(this, TaskSingleton.GetArchObject(),
					arch_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		archiveListView = (ListView) findViewById(R.id.archive_ListView);
		displayCount();
		setup_adapter();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		// sets up the listview onresume
		
		setup_adapter();
		
	}

	public void setup_adapter() {
		
		// Loads archive items into the listview

		// Save the Singleton
		fileSaverLoader.saveObject(this, TaskSingleton.GetArchObject(),
				arch_file);
		displayCount();

		ala = new archive_ListAdapter(this, R.layout.arch_entity,
				TaskSingleton.GetArchObject());

		// Pump adapter into Listview

		ListView archListView = archiveListView;
		//Log.d("onclick", "********* THIS IS Archive SETUP ADAPTER: ");
		archListView.setAdapter(ala);

		archListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				{
					//Log.d("onclick", "********* THIS IS A ARCH LONG CLICK: ");
					// Task task = todoList.get(position);
					edit_arch(position);
				}
				return true;
			}
		});

	}

	public void edit_arch(final int position) {
		
		// Brings up dialog box with options to Unarchive or delete
		
		// http://developer.android.com/reference/android/app/AlertDialog.Builder.html#setSingleChoiceItems(java.lang.CharSequence[],
		// int, android.content.DialogInterface.OnClickListener)
		// Edit existing todo with LONG CLICK

		LayoutInflater inflator = LayoutInflater.from(this);

		View editTodo = inflator.inflate(R.layout.edit_todo_dialog, null);

		AlertDialog.Builder editDialog = new AlertDialog.Builder(this);

		editDialog
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						})
				.setNeutralButton("Delete",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								
								TaskSingleton.GetArchObject().remove(position);
								displayCount();
								setup_adapter();							}
						})
				.setPositiveButton("Unarchive",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
//								Log.d("onclick", "********* UNARCHIVE ");
								
								toTodo(position);
								
							}
						});
		AlertDialog alertDialog = editDialog.create();
		alertDialog.show();

		
	}

	

	public void toTodo(int pos) {
		
		// Sends Archived tasks to the Todo list

		TaskSingleton.GetArchObject().get(pos).setGroup("todo");
		TaskSingleton.GetTodoObject().add((Task) TaskSingleton.GetArchObject().get(pos));
		TaskSingleton.GetArchObject().remove(pos);

		//Log.d("onclick", "********* TO TODO ");

		fileSaverLoader.saveObject(this, TaskSingleton.GetArchObject(),
				arch_file);
		fileSaverLoader.saveObject(this, TaskSingleton.GetTodoObject(),
				todo_file);
		
		displayCount();
		setup_adapter();

	}

	public void counts() {
		
		// Recounts status and number of tasks

		checked = 0;
		unchecked = 0;

		if (!TaskSingleton.GetArchObject().isEmpty()) {
			for (int i = 0; i < TaskSingleton.GetArchObject().size(); i++) {
				if (TaskSingleton.GetArchObject().get(i).getStatus() == false) {
//					Log.d("onclick", "unchecked "
//							+ TaskSingleton.GetArchObject().get(i).getStatus());
					unchecked += 1;
				} else {
//					Log.d("onclick", "checked "
//							+ TaskSingleton.GetArchObject().get(i).getStatus());
					checked += 1;
				}
			}
		}
	}

	public void displayCount() {

		// Updates the counts on screen
		
		
		counts();

		TextView totalCount = (TextView) findViewById(R.id.arch_total_count);
		TextView checkedCount = (TextView) findViewById(R.id.arch_checked_count);
		TextView uncheckedCount = (TextView) findViewById(R.id.arch_unchecked_count);

		totalCount.setText(Integer.toString(TaskSingleton.GetArchObject()
				.size()));
		checkedCount.setText(Integer.toString(checked));
		uncheckedCount.setText(Integer.toString(unchecked));

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
