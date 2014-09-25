package controller;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jivedesign.todo.R;
import com.jivedesign.todo.TaskSingleton;

public class task_ListAdapter extends ArrayAdapter<Task> {

	private int layoutResourceId;
	private Context context;
	private ArrayList<Task> taskList;
	// private List<controller.Task> list;

	

	public task_ListAdapter(Context context, int layoutResourceId,
			ArrayList<Task> taskList) {
		super(context, layoutResourceId, taskList);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.taskList = new ArrayList<Task>();
		this.taskList.addAll(taskList);
	}

	public static class TaskListHolder {
		Task task;
		CheckBox todo_checkBox;
		TextView todo_name;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		// Builds the list and populates it
		
		
		// Major contribs from :
		// http://www.mysamplecode.com/2012/07/android-listview-checkbox-example.html

		View row = convertView;
		
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		TaskListHolder holder = null;
		//Log.v("ConvertView", String.valueOf(position));

		LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		row = vi.inflate(R.layout.task_entity, null);
		

		holder = new TaskListHolder();

		holder.todo_checkBox = (CheckBox) row.findViewById(R.id.todo_checkBox);
		holder.todo_name = (TextView) row.findViewById(R.id.todo_name);

		row.setTag(holder);

		
		//Does the selected state change as well as loads into ListView as selected or not
				
		
		//Log.d("onclick", "*** tla: " + position);
		
		
		Task task = taskList.get(position);

		holder.todo_checkBox.setChecked(task.getStatus());
		holder.todo_name.setText(task.getTaskName());

		holder.todo_name.setTag(task);

		return row;

	}
	

	
	
	
		

}
