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


}
