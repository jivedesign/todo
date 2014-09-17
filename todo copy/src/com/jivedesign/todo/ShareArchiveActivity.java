package com.jivedesign.todo;

import com.example.todo.R;

import controller.task_ListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ShareArchiveActivity extends Activity {

	
	private ListView shareArchiveListView;
	private task_ListAdapter tla;
	
	public ShareArchiveActivity() {
		// TODO Auto-generated constructor stub
	}


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.share_archives);

	    setup_adapter();
	}
	
	
	
	public void setup_adapter() {
		tla = new task_ListAdapter(this, R.layout.share_archives, TaskSingleton.GetArchObject());
		shareArchiveListView = (ListView) findViewById(R.id.share_archiveList);
		shareArchiveListView.setAdapter(tla);
		
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
