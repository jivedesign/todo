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



}
