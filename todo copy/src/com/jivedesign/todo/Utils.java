package com.jivedesign.todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import controller.Task;

public class Utils {

	private static int LastID = 0;

	private static String todofile = "taskFile.sav";
	private static String archfile = "archFile.sav";

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

	public static void saveObject(Context context) {
		// REFERENCED FROM:
		// http://developer.android.com/guide/topics/data/data-storage.html#filesInternal

		
		try {

			FileOutputStream fos = context.openFileOutput(todofile,
                    Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			Log.d("onclick", "********* SAVING: ");
			oos.writeObject(TaskSingleton.GetTodoObject());

			fos.close();

			
		} catch (Exception ex) {
			Log.d("onclick", "**** CATCH ***** SAVING: ");
			ex.printStackTrace();
		}

	}

	public static void readObject(Context context) throws FileNotFoundException {
		// Read task objects to internal storage - returns task object

		// REFERENCED FROM:
		// http://developer.android.com/guide/topics/data/data-storage.html#filesInternal

		// Check if file exists, else make new one

		try {

			FileInputStream fis = context.openFileInput(todofile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			ArrayList<Task> tasksFromFile = (ArrayList<Task>) ois.readObject();
			Log.d("onclick", "********* TASK FROM FILE " + tasksFromFile.size());
			
			Log.d("onclick",
					"********* FROM FILE: ");
			
			for (int i = 0; i < tasksFromFile.size(); i++) {
				TaskSingleton.GetTodoObject().add(tasksFromFile.get(i));
			}
			ois.close();
			
			Log.d("onclick", "********* TASK FROM FILE " + tasksFromFile.size());
			
			Log.d("onclick",
					"********* FROM FILE: ");
			
			for (int i = 0; i < tasksFromFile.size(); i++) {
				TaskSingleton.GetTodoObject().add(tasksFromFile.get(i));
			}
			ois.close();
			

		} catch (Exception ex) {
			Log.d("onclick", "**** CATCH ***** READ ");
			ex.printStackTrace();
		}

		

	}

}
