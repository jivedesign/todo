package com.jivedesign.todo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import controller.Task;

public class fileSaverLoader {

	
//	private static String todofile = "taskFile.sav";
//	private static String archfile = "archFile.sav";
	
	public fileSaverLoader() {
		// TODO Auto-generated constructor stub
	}

	
	public static void saveObject(Context context, ArrayList<Task> taskArray, String file_name) {
		// REFERENCED FROM:
		// http://developer.android.com/guide/topics/data/data-storage.html#filesInternal

		try {

			FileOutputStream fos = context.openFileOutput(file_name,
					Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			Log.d("onclick", "********* SAVING: ");
			oos.writeObject(taskArray);

			fos.close();

		} catch (Exception ex) {
			Log.d("onclick", "**** CATCH ***** SAVING: ");
			ex.printStackTrace();
		}

	}

	public static void readObject(Context context, ArrayList<Task> taskArray, String file_name) throws FileNotFoundException {
		// Read task objects to internal storage - returns task object

		// REFERENCED FROM:
		// http://developer.android.com/guide/topics/data/data-storage.html#filesInternal

		// Check if file exists, else make new one

		try {

			FileInputStream fis = context.openFileInput(file_name);
			ObjectInputStream ois = new ObjectInputStream(fis);

			ArrayList<Task> tasksFromFile = (ArrayList<Task>) ois.readObject();
			Log.d("onclick", "********* TASK FROM FILE " + tasksFromFile.size());


			for (int i = 0; i < tasksFromFile.size(); i++) {
				taskArray.add(tasksFromFile.get(i));
			}
			ois.close();

			Log.d("onclick", "********* TASK FROM FILE " + tasksFromFile.size());

			Log.d("onclick", "********* FROM FILE: ");

			for (int i = 0; i < tasksFromFile.size(); i++) {
				Task newTask = tasksFromFile.get(i);
				if (!taskArray.contains(newTask)) {
					taskArray.add(tasksFromFile.get(i));
				}
			}
			ois.close();

		} catch (Exception ex) {
			Log.d("onclick", "**** CATCH ***** READ ");
			ex.printStackTrace();
		}

	}
	
	
}
