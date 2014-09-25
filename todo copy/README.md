jhn-TodoApp

This is a simple, attractive, and easy-to-use TODO list application. This app lets you record TODO items, and allows you to check that items are done, and archive items you don't want to see anymore. 

The app is seperated into three sections; Todo, Archives, and Share, which are accessible from the "three dot" menu icon on the top right

LICENSE:

This project is licensed under the GNU GENERAL PUBLIC LICENSE which is available in the LICENSE file on this repository.



INSTRUCTIONS:

Todos:
	- To add a Todo, tap the "+" icon and input the name of your Todo task
	- To check off a Todo, simply tap the Todo item. To uncheck it, tap it again
	- To Archive a Todo, LONG click the item and select "Archive"
	- To Delete a Todo, LONG click the item and select "Delete"

Archives:
	- To Unarchive an Archived TODO, LONG click the item and select "Archive"
	- To Delete an Archived Todo, LONG click the item and select "Delete"

Share:
	Here you can share your Todos and Archives:
		- You can share Everything, all TODOS, all ARCHIVES, a selection of TODOS, or a selection of Archives

	Sharing a selection of Todos or Archives:
		- Select which you would like to share a selection of. 
			This will bring you to a new Activity corresponding to your selection
		- Select the tasks you would like to send, then tap "Email"



RATIONALE:
	Some classes are similar, such as the Main Activity and Archive Activity, List Adapters, and Share Activities. This is done in order to STYLE the activities differenly. I wanted the user to know where they were withouth having to read labels or indicators - namely, have a distinction between TODO Activity and ARCHIVE activity.


KNOWN USABILITY ISSUES:
	In the activities for sharing a selection of items, when you select the item, it gets highlighted. When the list view is scrolled and the selected item is OUT OF VIEW, the selection highlight is removed. BUT the item is still ACTUALLY selected - just not visually shown.





REFERENCES:

- Understanding Singletons
	http://en.wikipedia.org/wiki/Singleton_pattern

- RadioGroup/Radio Buttons : Android 
	http://developer.android.com/guide/topics/ui/controls/radiobutton.html

- Learning a bit about Dialog Boxes : Android
	http://developer.android.com/reference/android/app/AlertDialog.Builder.html#setSingleChoiceItems(java.lang.CharSequence[],int, android.content.DialogInterface.OnClickListener)

- Saving files : Android
	http://developer.android.com/guide/topics/data/data-storage.html#filesInternal
	
- Learning about emailing
	// http://www.tutorialspoint.com/android/android_sending_email.htm

	http://www.tutorialspoint.com/about/about_disclaimer.htm
	"You are granted permission to access the website and its contents only for the purpose of learning and/or business expansion."

	
