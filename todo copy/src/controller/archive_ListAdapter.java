package controller;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jivedesign.todo.R;


public class archive_ListAdapter extends task_ListAdapter {
	
	// THIS SECOND LIST ADAPTER WAS MADE IN ORDER TO APPLY DIFFERENT BEHAVIOR FOR THE ARCHIVE,
	// SUCH AS DISABLED CHECKBOXES AND SOME VARIoUS STYLES. IS IT REDUNANT AND BAD DESIGN? MAYBE.
	

	private int layoutResourceId;
	private Context context;
	private ArrayList<Task> taskList;
	
	public archive_ListAdapter(Context context, int layoutResourceId,
			ArrayList<Task> taskList) {
		super(context, layoutResourceId, taskList);
		// TODO Auto-generated constructor stub
		
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.taskList = new ArrayList<Task>();
		this.taskList.addAll(taskList);
		
	}
	
	public static class TaskListHolder {
		Task task;
		CheckBox arch_checkBox;
		TextView arch_name;
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
		
		// CUSTOM LIST ROW
		row = vi.inflate(R.layout.arch_entity, null);
		

		holder = new TaskListHolder();

		holder.arch_checkBox = (CheckBox) row.findViewById(R.id.arch_checkBox);
		holder.arch_name = (TextView) row.findViewById(R.id.arch_name);

		row.setTag(holder);

		
		Task task = taskList.get(position);

		holder.arch_checkBox.setChecked(task.getStatus());
		holder.arch_name.setText(task.getTaskName());

		holder.arch_name.setTag(task);

		return row;

	}
	

}
