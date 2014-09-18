package com.jivedesign.todo;

import com.example.todo.R;

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

public class ShareActivity extends Activity {

	private String email_address = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_activity);
		
		//Emailer.emailTasks(email_address, TaskSingleton.GetTodoObject());

		Intent intent = new Intent(Intent.ACTION_SEND);
		
		intent.setData(Uri.parse("mailto:"));
		intent.setType("plain/text");
		
		intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"jhn@ualberta.ca"});
		intent.putExtra(Intent.EXTRA_SUBJECT, "Test email");
		intent.putExtra(Intent.EXTRA_TEXT   , "body of email");
		
		startActivity(Intent.createChooser(intent, "Send Mail Using :"));
		
		
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

	// Array radioAdapter = array.createFromResource(this, R.id.shareRadioMenu);

	public void getEmail(View v) {

		EditText emailString = (EditText) findViewById(R.id.email_address);
		email_address = (String) emailString.getText().toString();
		Log.d("onclick", "*EMAIL ADDRESS " + email_address);

	}

	public void toSendTodos(View v) {
		Intent i = new Intent(this, ShareTodoActivity.class);
		startActivity(i);
	}

	public void toSendArchives(View v) {
		Intent i = new Intent(this, ShareArchiveActivity.class);
		startActivity(i);
	}

}
