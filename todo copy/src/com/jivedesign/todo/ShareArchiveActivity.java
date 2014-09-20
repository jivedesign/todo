package com.jivedesign.todo;

import java.util.ArrayList;

import com.example.todo.R;

import controller.Task;
import controller.task_ListAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
	    
	    Intent intent = getIntent();
	    email_address = intent.getStringExtra("email");

	    setup_adapter();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		setup_adapter();
		
	}
	
	
	
	
	public void setup_adapter() {
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
				
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text + selectedTask.getTaskName(), duration);
				toast.show();
				
				someArchivesList.add(selectedTask);
				
			}
		});
		
	}
	

	public void emailSomeArchives(View v) {
		Emailer.emailTasks(this, "some archives", email_address, someArchivesList);
		
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
