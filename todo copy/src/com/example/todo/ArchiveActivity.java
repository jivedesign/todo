package com.example.todo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import controller.Task;
import controller.archive_ListAdapter;
import controller.task_ListAdapter;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ArchiveActivity extends Activity {

//	public ArchiveActivity() {
//		// TODO Auto-generated constructor stub
//	}

	final Context context = this;
	private ListView archiveListView;
	private archive_ListAdapter ala;
	
	private int total = 0;
	private int checked = 0;
	private int unchecked = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.arch_frag);
	    // TODO Auto-generated method stub
	    
		archiveListView = (ListView) findViewById(R.id.archive_ListView);
		setup_adapter();
	}
	

	public void setup_adapter() {
		
		//ala = new archive_ListAdapter(this, R.layout.arch_frag, TaskSingleton.GetArchObject());
		ala = new archive_ListAdapter(this, R.layout.arch_entity,TaskSingleton.GetArchObject());

		// Pump adapter into Listview

		ListView archListView = archiveListView;
		Log.d("onclick", "********* THIS IS Archive SETUP ADAPTER: ");
		archListView.setAdapter(ala);

		

		archListView
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						{
							Log.d("onclick", "********* THIS IS A ARCH LONG CLICK: ");
							// Task task = todoList.get(position);
							edit_arch(position);
						}
						return true;
					}
				});
		
		archListView
			.setOnItemClickListener(new OnItemClickListener() {

			
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				
				
				Log.d("onclick", "todo status from list bEFORE: "
						+ TaskSingleton.GetArchObject().get(position)
								.getStatus());

				if (TaskSingleton.GetArchObject().get(position).getStatus() == false) {
					TaskSingleton.GetArchObject().get(position).setStatus(true);
					

				} else {
					TaskSingleton.GetArchObject().get(position)
							.setStatus(false);
				}

				Log.d("onclick", "todo status from list AFTER: "
						+ TaskSingleton.GetArchObject().get(position)
								.getStatus());
				
				setup_adapter();

				displayCount();

			}
		});
	}
	
	
	public void edit_arch(final int position) {
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
								Log.d("onclick", "********* Deleted Arch ");
								setup_adapter();
							}
						})
				.setPositiveButton("Unarchive",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Log.d("onclick", "********* UNARCHIVE ");
								toTodo(position);
								
							}
						});
		AlertDialog alertDialog = editDialog.create();
		alertDialog.show();

	}
	
	
	public void task2Archive(String task_name, boolean status) {
		
		Task task = new Task(Utils.getNewID(), "arch", status, task_name);
		
		TaskSingleton.GetArchObject().add(task);
		setup_adapter();
		
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
			return true;
		}
			
		return super.onOptionsItemSelected(item);
	}
	

	public void toTodo(int pos) {

		TaskSingleton.GetArchObject().add(
				(Task) TaskSingleton.GetArchObject().get(pos));
		TaskSingleton.GetArchObject().remove(pos);

		setup_adapter();

		Log.d("onclick", "********* TO ARCHIVE ");

		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);

	}


	
	public void counts() {

		checked = 0;
		unchecked = 0;

		if (!TaskSingleton.GetArchObject().isEmpty()) {

			for (int i = 0; i < TaskSingleton.GetArchObject().size(); i++) {

				if (TaskSingleton.GetArchObject().get(i).getStatus() == false) {
					Log.d("onclick", "unchecked "
							+ TaskSingleton.GetArchObject().get(i).getStatus());
					unchecked += 1;
				} else {
					Log.d("onclick", "checked "
							+ TaskSingleton.GetArchObject().get(i).getStatus());
					checked += 1;
				}

			}
		}

		Log.d("onclick", "********* Total COunts " + total + "Actual total"
				+ TaskSingleton.GetArchObject().size());
		Log.d("onclick", "********* checked " + checked);
		Log.d("onclick", "********* unchecked " + unchecked);

	}

	public void displayCount() {

		counts();

		TextView totalCount = (TextView) findViewById(R.id.arch_total_count);
		TextView checkedCount = (TextView) findViewById(R.id.arch_checked_count);
		TextView uncheckedCount = (TextView) findViewById(R.id.arch_unchecked_count);

		totalCount.setText(Integer.toString(TaskSingleton.GetTodoObject()
				.size()));
		checkedCount.setText(Integer.toString(checked));
		uncheckedCount.setText(Integer.toString(unchecked));

	}

}
