/*
TodoApp: Track Todos, check them off, archive them, and share them

Copyright (C) 2014 Joshua Nguyen jhn@ualberta.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.

*/

/*
 This Singleton class holds the list of Todo tasks and Archive tasks.
 It is used throughout the application.
 */


package com.jivedesign.todo;

import java.io.Serializable;
import java.util.ArrayList;

import controller.Task;

public class TaskSingleton implements Serializable {

	// Understanding Singletons
	// http://en.wikipedia.org/wiki/Singleton_pattern
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


