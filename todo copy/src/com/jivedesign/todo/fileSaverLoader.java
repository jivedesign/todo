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

	public fileSaverLoader() {
		// TODO Auto-generated constructor stub
	}

	public static void saveObject(Context context, ArrayList<Task> taskArray,
			String file_name) {
		// REFERENCED FROM:
		// http://developer.android.com/guide/topics/data/data-storage.html#filesInternal

		try {

			FileOutputStream fos = context.openFileOutput(file_name,
					Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			Log.d("onclick", "********* SAVING: ");
			oos.writeObject(taskArray);

			fos.close();
			oos.close();

		} catch (Exception ex) {
			Log.d("onclick", "**** CATCH ***** SAVING: ");
			ex.printStackTrace();
		}

	}

	public static void readObject(Context context, ArrayList<Task> taskArray,
			String file_name) throws FileNotFoundException {
		// Read task objects to internal storage - returns task object

		// REFERENCED FROM:
		// http://developer.android.com/guide/topics/data/data-storage.html#filesInternal

		try {

			// onCreate is called between activity switches, if read happens
			// every switch, we ADD lists on lists. must clear each time
			taskArray.clear();

			FileInputStream fis = context.openFileInput(file_name);
			ObjectInputStream ois = new ObjectInputStream(fis);

			ArrayList<Task> tasksFromFile = (ArrayList<Task>) ois.readObject();
			Log.d("onclick", "********* TASK FROM FILE " + tasksFromFile.size());

//			for (int i = 0; i < tasksFromFile.size(); i++) {
//				taskArray.add(tasksFromFile.get(i));
//			}
			
			taskArray.addAll(tasksFromFile);
			
			ois.close();
			fis.close();

		} catch (Exception ex) {
			Log.d("onclick", "**** CATCH ***** READ ");
			ex.printStackTrace();
		}

	}

}
