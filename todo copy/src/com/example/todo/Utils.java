package com.example.todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import controller.Task;

public class Utils {

	private static int LastID = 0;

	private static String todofile = "taskFile.ser";
	private static String archfile = "archFile.ser";
	
	public static void moveTo(int taskID) {
		// Takes in a taskID, finds the task and switches its group to either
		// "todo" or "archive"

		// need to parse text file

	}

	public static void save(int taskID) {
		// writes to file, using the task ID?????

	}

	public ArrayList<Task> readTask() {
		ArrayList<Task> taskList = new ArrayList<Task>();

		// read form file and pump into taskList

		return taskList;
	}

	

	public static int getNewID() {
		// Gives new ID value.

		if (LastID == 0) {
			LastID = 1;
			Log.d("onclick", "********* NEW ID: " + LastID);

			return LastID;

		}
		LastID = LastID + 1;
		Log.d("onclick", "********* NEW ID: " + LastID);

		return LastID;
	}
	
	
	public static void saveObject() {
		// REFERENCED FROM:
		// http://www.vogella.com/tutorials/JavaSerialization/article.html
		// written by: Lars Vogel
		
		Log.d("onclick", "********* SAVING: " + TaskSingleton.GetTodoObject());

		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {

			fos = new FileOutputStream(todofile);
			out = new ObjectOutputStream(fos);
			out.writeObject(TaskSingleton.GetTodoObject());

			
//			for (int i = TaskSingleton.GetTodoObject().size(); TaskSingleton.GetTodoObject().size() > i; i--) {
//				out.writeObject(i);
//			}
			
			
			// fos = new FileOutputStream(archfile);
			// out.writeObject(TaskSingleton.GetArchObject());

			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	
	public static void readObject() {
		// Read task objects to internal storage - returns task object

		// REFERENCED FROM:
		// http://www.vogella.com/tutorials/JavaSerialization/article.html
		// written by: Lars Vogel

		Log.d("onclick", "********* FROM FILE: " + TaskSingleton.GetTodoObject());
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
		fis = new FileInputStream(todofile);
		ois = new ObjectInputStream(fis);
		
		TaskSingleton.GetTodoObject().addAll((Collection<? extends Task>) ois.readObject());
//
//		int i = 0;
//		while (fis != null) {
//			TaskSingleton.GetTodoObject().addAll((Collection<? extends Task>) ois.readObject());
//		}
//		
		ois.close();
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	

}
