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
 *  The Main Activity acts as the Todo Activity. It contains methods for modifying Todo and Archive lists
 *  as well as methods for displaying counts. 
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
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import controller.Task;
import controller.task_ListAdapter;

public class MainActivity extends Activity {

	final Context context = this;

	// static ArrayList<Task> todoList = new ArrayList<Task>();

	private ListView todoListView;
	private task_ListAdapter tla;
	String todo_file = "todoFile.sav";
	String arch_file = "archFile.sav";

	private int total = 0;
	private int checked = 0;
	private int unchecked = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			fileSaverLoader.readObject(this, TaskSingleton.GetTodoObject(), todo_file);
			fileSaverLoader.readObject(this, TaskSingleton.GetArchObject(),
					arch_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		startup();
	}


	@Override
	public void onResume() {
		super.onResume();
		
		// sets up the list view on resume
		setup_adapter();
		
	}
	
	
	public void startup() {
		
		// sets up onclick and the listview
		
		todoListView = (ListView) findViewById(R.id.todo_listView);
		final Button add_todoButton = (Button) findViewById(R.id.add_Button);

		add_todoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				add_todo();
				displayCount();
			}
		});

		//Log.d("onclick", "********* SIZE OF SINGLETON: "+ TaskSingleton.GetTodoObject().size());

		setup_adapter();
		
	}
	

	
	public void setup_adapter() {
		
		// Generates the list of tasks into the list view for checking and unchecking

		// Save the Singleton
		fileSaverLoader.saveObject(this, TaskSingleton.GetTodoObject(), todo_file);
		displayCount();

		// Pump list adapter full of task_entities which contain items of
		// todoList
		tla = new task_ListAdapter(this, R.layout.task_entity,
				TaskSingleton.GetTodoObject());

		// Pump adapter into Listview

		ListView newTodoListView = todoListView;
		newTodoListView.setAdapter(tla);

		newTodoListView
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						{
							// Task task = todoList.get(position);
							edit_todo(position);
						}
						return true;
					}
				});

		newTodoListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub

				taskChangeStatus(position);

			}
		});

	}

	protected void taskChangeStatus(int position) {
		
		// Changes the status of task when clicked

		if (TaskSingleton.GetTodoObject().get(position).getStatus() == false) {
			TaskSingleton.GetTodoObject().get(position).setStatus(true);

		} else {
			TaskSingleton.GetTodoObject().get(position).setStatus(false);
		}

//		Log.d("onclick", "todo status from list AFTER: "
//				+ TaskSingleton.GetTodoObject().get(position).getStatus());

		setup_adapter();
		displayCount();

	}



	public void add_todo() {

		// Adds a new todo to the todo list

		LayoutInflater li = LayoutInflater.from(this);

		// Get XML file to view
		View promptsView = li.inflate(R.layout.add_todo_dialog, null);

		final EditText taskName = (EditText) promptsView
				.findViewById(R.id.add_todo_dialog);

		// Create a new AlertDialog
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// Link the alertdialog to the XML
		alertDialogBuilder.setView(promptsView);

		// Building the dialog for adding
		alertDialogBuilder

		// Makes the "Create" button
				.setPositiveButton("Create",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

								String newTaskName_toString = (String) taskName
										.getText().toString();

								if (newTaskName_toString.isEmpty()) {
									add_todo();
								}

								else {
									Task newTask = new Task(Utils.getNewID(),
											"todo", false, newTaskName_toString);
									TaskSingleton.GetTodoObject().add(newTask);
									setup_adapter();
								}
							}
						}).setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// Do nothing
								dialog.cancel();
							}
						});

		AlertDialog alertDialog = alertDialogBuilder.create();

		alertDialog.show();

		fileSaverLoader.saveObject(this, TaskSingleton.GetTodoObject(), todo_file);
		setup_adapter();

	}

	public void edit_todo(final int position) {
		
		// Brings up DIalog window with options to Archive and Delete
		
		
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
								TaskSingleton.GetTodoObject().remove(position);

								//Log.d("onclick", "********* Deleted Task ");
								setup_adapter();
							}
						})
				.setPositiveButton("Archive",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
//								Log.d("onclick", "********* ARCHIVE ");
//								Log.d("onclick", "********* BEfore to ARCHIVE ");
								toArchive(position);

							}
						});
		displayCount();
		setup_adapter();

		AlertDialog alertDialog = editDialog.create();
		alertDialog.show();

	}

	public void toArchive(int pos) {

		// Sends the task item to Archive list
		
		TaskSingleton.GetTodoObject().get(pos).setGroup("archive");

		TaskSingleton.GetArchObject().add(
				(Task) TaskSingleton.GetTodoObject().get(pos));
		TaskSingleton.GetTodoObject().remove(pos);

		//Log.d("onclick", "********* TO ARCHIVE ");

		displayCount();

		fileSaverLoader.saveObject(this, TaskSingleton.GetTodoObject(), todo_file);
		fileSaverLoader.saveObject(this, TaskSingleton.GetArchObject(),arch_file);
		
		
		setup_adapter();

		// Intent i = new Intent(this, ArchiveActivity.class);
		// startActivity(i);

	}

	public void counts() {

		// Counts the tasks and their statuses
		
		checked = 0;
		unchecked = 0;

		if (!TaskSingleton.GetTodoObject().isEmpty()) {

			for (int i = 0; i < TaskSingleton.GetTodoObject().size(); i++) {

				if (TaskSingleton.GetTodoObject().get(i).getStatus() == false) {
					unchecked += 1;
				} else {
					checked += 1;
				}

			}
		}
	}

	public void displayCount() {

		// Updates the counts on screen
		
		
		counts();

		TextView totalCount = (TextView) findViewById(R.id.todo_total_count);
		TextView checkedCount = (TextView) findViewById(R.id.todo_checked_count);
		TextView uncheckedCount = (TextView) findViewById(R.id.todo_unchecked_count);

		totalCount.setText(Integer.toString(TaskSingleton.GetTodoObject()
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
