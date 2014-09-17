package com.jivedesign.todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.util.Log;
import controller.Task;

public class Utils {

	private static int LastID = 0;

	private static String todofile = "/taskFile.ser";
	private static String archfile = "/archFile.ser";

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
		// http://developer.android.com/guide/topics/data/data-storage.html#filesInternal

		Log.d("onclick", "********* SAVING: " + TaskSingleton.GetTodoObject());

		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {

			fos = new FileOutputStream(todofile);
			out = new ObjectOutputStream(fos);
			// out.writeObject(TaskSingleton.GetTodoObject());

			for (int i = TaskSingleton.GetTodoObject().size(); TaskSingleton
					.GetTodoObject().size() > i; i--) {
				out.writeObject(i);
			}

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
		// http://developer.android.com/guide/topics/data/data-storage.html#filesInternal

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		// Check if file exists, else make new one

		File file = new File(todofile);
		if (file.exists()) {

			Log.d("onclick", "** READING OLD FILE*****");

			try {

				fis = new FileInputStream(todofile);
				ois = new ObjectInputStream(fis);

				// TaskSingleton.GetTodoObject().addAll((Collection<? extends
				// Task>)
				// ois.readObject());

				int i = 0;
				while (fis != null) {
					TaskSingleton.GetTodoObject().add((Task) ois.readObject());
					i += 1;
				}
				ois.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		Log.d("onclick",
				"********* FROM FILE: " + TaskSingleton.GetTodoObject());

	}

}
