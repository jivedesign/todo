package com.jivedesign.todo;

import com.example.todo.R;

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
import android.widget.TextView;
import controller.Task;
import controller.task_ListAdapter;

public class MainActivity extends ActionBarActivity {

	final Context context = this;

	// static ArrayList<Task> todoList = new ArrayList<Task>();

	private ListView todoListView;
	private task_ListAdapter tla;

	private int total = 0;
	private int checked = 0;
	private int unchecked = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Utils.readObject();
		
		
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

		setup_adapter();

	}

	public void setup_adapter() {

		// Save the Singleton
		Utils.saveObject();
		displayCount();

		// Pump list adapter full of task_entities which contain items of
		// todoList
		tla = new task_ListAdapter(this, R.layout.task_entity,
				TaskSingleton.GetTodoObject());

		// Pump adapter into Listview

		ListView newTodoListView = todoListView;
		newTodoListView.setAdapter(tla);

		Log.d("onclick", "********* THIS IS SETUP ADAPTER: ");

		newTodoListView
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						{
							Log.d("onclick", "********* THIS IS A LONG CLICK: ");
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
				
				
				Log.d("onclick", "todo status from list bEFORE: "
						+ TaskSingleton.GetTodoObject().get(position)
								.getStatus());

				if (TaskSingleton.GetTodoObject().get(position).getStatus() == false) {
					TaskSingleton.GetTodoObject().get(position).setStatus(true);
					

				} else {
					TaskSingleton.GetTodoObject().get(position)
							.setStatus(false);
				}

				Log.d("onclick", "todo status from list AFTER: "
						+ TaskSingleton.GetTodoObject().get(position)
								.getStatus());
				
				setup_adapter();

				displayCount();

			}
		});
		
		Utils.readObject();
	
		
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

	public void add_todo() {

		// WITH HELP FROM JASMINE WOO, a real human

		LayoutInflater li = LayoutInflater.from(this);

		// Get XML file to view
		View promptsView = li.inflate(R.layout.add_todo_dialog, null);

		final EditText taskName = (EditText) promptsView.findViewById(R.id.add_todo_dialog);

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
		
		setup_adapter();

	}

	public void edit_todo(final int position) {
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

								Log.d("onclick", "********* Deleted Task ");
								setup_adapter();
							}
						})
				.setPositiveButton("Archive",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Log.d("onclick", "********* ARCHIVE ");
								Log.d("onclick", "********* BEfore to ARCHIVE ");
								toArchive(position);

							}
						});
		displayCount();
		setup_adapter();
		
		AlertDialog alertDialog = editDialog.create();
		alertDialog.show();

	}

	public void toArchive(int pos) {

		TaskSingleton.GetArchObject().add(
				(Task) TaskSingleton.GetTodoObject().get(pos));
		TaskSingleton.GetTodoObject().remove(pos);

		Log.d("onclick", "********* TO ARCHIVE ");

		displayCount();

		setup_adapter();
		
//		Intent i = new Intent(this, ArchiveActivity.class);
//		startActivity(i);
		
	}

	public void counts() {

		checked = 0;
		unchecked = 0;

		if (!TaskSingleton.GetTodoObject().isEmpty()) {

			for (int i = 0; i < TaskSingleton.GetTodoObject().size(); i++) {

				if (TaskSingleton.GetTodoObject().get(i).getStatus() == false) {
					Log.d("onclick", "unchecked "
							+ TaskSingleton.GetTodoObject().get(i).getStatus());
					unchecked += 1;
				} else {
					Log.d("onclick", "checked "
							+ TaskSingleton.GetTodoObject().get(i).getStatus());
					checked += 1;
				}

			}
		}

		Log.d("onclick", "********* Total COunts " + total + "Actual total"
				+ TaskSingleton.GetTodoObject().size());
		Log.d("onclick", "********* checked " + checked);
		Log.d("onclick", "********* unchecked " + unchecked);

	}

	public void displayCount() {

		counts();

		TextView totalCount = (TextView) findViewById(R.id.todo_total_count);
		TextView checkedCount = (TextView) findViewById(R.id.todo_checked_count);
		TextView uncheckedCount = (TextView) findViewById(R.id.todo_unchecked_count);

		totalCount.setText(Integer.toString(TaskSingleton.GetTodoObject()
				.size()));
		checkedCount.setText(Integer.toString(checked));
		uncheckedCount.setText(Integer.toString(unchecked));

	}

}