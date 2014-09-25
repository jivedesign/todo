package controller;

import java.io.Serializable;

public class Task implements Serializable {

	//SERIALIZABLE REFERENCE: http://www.coderzheaven.com/2012/07/25/serialization-android-simple-example/
	
	private String class_name;
	protected int taskID = 0;
	protected String group = "todo";
	protected boolean status = false;
	protected String taskName;
	

	public String getClass_name() {
		return getClass_name();
	}


	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	
	public Task(int taskID, String group, boolean status, String taskName) {
		this.taskID = taskID;
		this.group = group;
		this.status = status;
		this.taskName = taskName; 
		
	}


	public int getTaskID() {
		return taskID;
	}


	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}


	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}


	public boolean getStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	
	}
	
//	
//	public void changeStatus(View v) {
//		TextView task_textview = (TextView) v.findViewById(R.id.todo_name);
//		
//		// CHANGES THE STATE OF THE TASK
//		// Used this for the StrikeTrhoguh: http://stackoverflow.com/questions/9786544/creating-a-strikethrough-text-in-android
//		
//		if (this.status == false) {
//			
//			Log.d("onclick", "todo status from list: " + this.status);
//			Log.d("onclick", "todo Name from list: " + this.taskName);
//			
//			this.status = true;
//									
//			task_textview.setPaintFlags(task_textview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//			
//			Log.d("onclick", "todo status from list: " + this.status);
//		} else {
//			Log.d("onclick", "todo status from list: " + this.status);
//			Log.d("onclick", "todo Name from list: " + this.taskName);
//			
//			this.status = false;
//			task_textview.setPaintFlags(task_textview.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
//			Log.d("onclick", "todo status from list: " + this.status);
//		}
//		
//		
//		
//		
//	}
	
	
}
