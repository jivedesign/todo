package com.jivedesign.todo;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Task;

public class TaskSingleton implements Serializable {

	//http://en.wikipedia.org/wiki/Singleton_pattern
	private static final long serialVersionUID = 0L;
	
	private static ArrayList<Task> todoList;
	private static ArrayList<Task> archList;
	
	private TaskSingleton() {
	}
	
	public static ArrayList<Task> GetTodoObject() {
		if (todoList == null) {
			todoList = new ArrayList<Task>(); 
		}
		return todoList;
	}
	
	public static ArrayList<Task> GetArchObject() {
		if (archList == null) {
			archList = new ArrayList<Task>(); 
		}
		return archList;
	}
	
	
	
	
}


