
package AppHandler;

import java.util.ArrayList;
import java.util.Scanner;

import AppMain.MyMain;
import AppMain.MyTask;
import AppMenu.Menu;

public class UserInputHandler {

	// Define string variables to save user inputs
	private static String userOptionInput;

	private static String user1stInput;

	private static String user2ndInput;

	private static String user3rdInput;

	private static String user4thInput;

	private static Menu menu = new Menu();

	// Define a scanner variable to read user input
	private static Scanner userInput = new Scanner(System.in);

	// Defined an Arraylist to save input data for Task
	private static ArrayList<MyTask> inTaskList = new ArrayList<MyTask>();

	public static int getInTaskList() {
		return inTaskList.size();
	}

	/**
	 * Handling ToDoList Main Menu options
	 */
	public static void todoMainMenu() {

		menu.toDisplayMenuOptions(1);

		Messages.ASKFORUSEROPTION.printOut(1);

		userOptionInput = userInput.nextLine();

		switch (userOptionInput) {
		case "0":
			// Terminate the processing
			terminateProcessing();
			break;

		case "1":
			// Enter to Show Task List Menu
			MyMain.clearConsole();
			showTaskMenu();
			break;

		case "2":
			// Add task to application buffer
			user4thInput = "YES";
			while (!(user4thInput.trim().toUpperCase().contentEquals("N")
					|| user4thInput.trim().toUpperCase().contentEquals("NO"))) {

				Messages.ASKFORTASKNAMEIN.printOut(1);
				setUser1stInput(userInput.nextLine());

				Messages.ASKFORTASKPROJCT.printOut(1);
				setUser2ndInput(userInput.nextLine());

				Messages.ASKFORTSKDUEDATE.printOut(1);
				setUser3rdInput(isDateValid(userInput.nextLine()));

				inTaskList.add(new MyTask(getUser1stInput(), getUser2ndInput(), getUser3rdInput(), "todo"));

				Messages.InfoMessage("ADDNEWTASK");

				user4thInput = userInput.nextLine();
			}
			MyMain.clearConsole();
			todoMainMenu();
			break;

		case "3":
			// Enter to Edit Task Menu
			MyMain.clearConsole();
			editTaskMenu();
			break;

		case "4":
			// Write tasks that saved in application buffer into ToDoList File without Quit
			MyMain.clearConsole();
			addTasksToFile();
			todoMainMenu();
			break;

		case "5":
			// Write tasks that saved in application buffer into ToDoList File and Quit
			addTasksToFile();
			Messages.INFOSAVEANDQUIT.printOut(2);
			System.exit(0);
			break;

		default:
			MyMain.clearConsole();
			Messages.InfoMessage("WRONGINPUT");
			todoMainMenu();
			break;

		}
	}

	/**
	 * Handling Show Task List Menu options
	 */
	static void showTaskMenu() {

		menu.toDisplayMenuOptions(2);

		Messages.ASKFORUSEROPTION.printOut(1);

		userOptionInput = userInput.nextLine();

		switch (userOptionInput) {

		case "0":
			// Terminate the processing
			terminateProcessing();
			break;

		case "1":
			// Show all tasks
			Messages.INFOSHOWALLTASKS.printOut(2);
			TxtFileHandler.dataReader(3, null, null);
			if (getUserConfirmation("Continue")) {
				MyMain.clearConsole();
				showTaskMenu();
			}
			break;

		case "2":
			// Show List of Tasks by Duedate
			Messages.INFOSHOWTSKBYDAT.printOut(2);
			Messages.ASKFORTSKDUEDATE.printOut(1);
			setUser1stInput(isDateValid(userInput.nextLine()));
			TxtFileHandler.dataReader(2, getUser1stInput(), null);
			if (getUserConfirmation("Continue")) {
				MyMain.clearConsole();
				showTaskMenu();
			}
			break;

		case "3":
			// Show List of Tasks by Project Name
			Messages.INFOSHOWTSKBYPRC.printOut(2);
			Messages.ASKFORTASKPROJCT.printOut(1);
			setUser1stInput(userInput.nextLine());
			TxtFileHandler.dataReader(1, getUser1stInput(), null);
			if (getUserConfirmation("Continue")) {
				MyMain.clearConsole();
				showTaskMenu();
			}

			break;

		case "4":
			// Retrun to ToDoList main Menu
			MyMain.clearConsole();
			Messages.INFOWELCOMEBACK.printOut(2);
			todoMainMenu();
			break;

		default:
			MyMain.clearConsole();
			Messages.InfoMessage("WRONGINPUT");
			showTaskMenu();
			break;
		}
	}

	/**
	 * Handling Edit Task Menu
	 */
	static void editTaskMenu() {

		menu.toDisplayMenuOptions(3);

		Messages.ASKFORUSEROPTION.printOut(1);

		userOptionInput = userInput.nextLine();

		switch (userOptionInput) {

		case "0":
			// Terminate the processing
			terminateProcessing();
			break;

		case "1":
			// Edit Task Name
			Messages.ASKFOROLDTASKNME.printOut(1);
			setUser1stInput(userInput.nextLine());
			Messages.ASKFORTASKPROJCT.printOut(1);
			setUser2ndInput(userInput.nextLine());
			Messages.ASKFORNEWTASKNME.printOut(1);
			setUser3rdInput(userInput.nextLine());
			TxtFileHandler.dataUpdater(0, getUser1stInput(), getUser2ndInput(), getUser3rdInput());
			if (getUserConfirmation("Continue")) {
				MyMain.clearConsole();
				editTaskMenu();
			}
			break;
		case "2":
			// Edit Project Name
			Messages.ASKFORTASKNAMEIN.printOut(1);
			setUser1stInput(userInput.nextLine());
			Messages.ASKFOROLDPROJECT.printOut(1);
			setUser2ndInput(userInput.nextLine());
			Messages.ASKFORNEWPROJECT.printOut(1);
			setUser3rdInput(userInput.nextLine());

			TxtFileHandler.dataUpdater(1, getUser1stInput(), getUser2ndInput(), getUser3rdInput());
			if (getUserConfirmation("Continue")) {
				MyMain.clearConsole();
				editTaskMenu();
			}
			break;

		case "3":
			// Edit Task DueDate
			Messages.ASKFORTASKNAMEIN.printOut(1);
			setUser1stInput(userInput.nextLine());
			Messages.ASKFORTASKPROJCT.printOut(1);
			setUser2ndInput(userInput.nextLine());
			Messages.ASKFORNEWDUEDATE.printOut(1);
			setUser3rdInput(isDateValid(userInput.nextLine()));

			TxtFileHandler.dataUpdater(2, getUser1stInput(), getUser2ndInput(), getUser3rdInput());
			if (getUserConfirmation("Continue")) {
				MyMain.clearConsole();
				editTaskMenu();
			}
			break;

		case "4":
			// Edit Task Status
			Messages.ASKFORTASKNAMEIN.printOut(1);
			setUser1stInput(userInput.nextLine());
			Messages.ASKFORTASKPROJCT.printOut(1);
			setUser2ndInput(userInput.nextLine());
			Messages.ASKFORNEWSTATUSS.printOut(1);
			setUser3rdInput(userInput.nextLine());
			TxtFileHandler.dataUpdater(3, getUser1stInput(), getUser2ndInput(), getUser3rdInput());
			if (getUserConfirmation("Continue")) {
				MyMain.clearConsole();
				editTaskMenu();
			}
			break;

		case "5":
			// Delete Task
			Messages.ASKFORTASKNAMEIN.printOut(1);
			setUser1stInput(userInput.nextLine());
			Messages.ASKFORTASKPROJCT.printOut(1);
			setUser2ndInput(userInput.nextLine());
			TxtFileHandler.dataDeleter(getUser1stInput(), getUser2ndInput());
			if (getUserConfirmation("Continue")) {
				MyMain.clearConsole();
				editTaskMenu();
			}
			break;

		case "6":
			// Retrun to ToDoList main Menu
			MyMain.clearConsole();
			Messages.INFOWELCOMEBACK.printOut(2);
			todoMainMenu();

		default:
			MyMain.clearConsole();
			Messages.InfoMessage("WRONGINPUT");
			editTaskMenu();
			break;
		}
	}

	/**
	 * Check if the input date is valid( expected date format [YYYYMMDD],It loops
	 * until the user entering a valid date
	 * 
	 * @param inputDate
	 * @return
	 */
	static String isDateValid(String inputDate) {
		while (!(inputDate.length() == 8 && MyMain.isNumeric(inputDate))) {
			System.out.print("\nYour input for  Date ( " + inputDate + " ) is incorrect.\n"
					+ "The Date formate should be (YYYYMMDD)\n" + "Please retry to enter a valid Date : ");
			inputDate = userInput.nextLine();
		}
		return inputDate;
	}

	/**
	 * Asking for user confirmation before moving to the next step of the processing
	 * 
	 * @param msgContent Message content displays to user depends on use purposes
	 * @return bolean (True or False)
	 */
	static boolean getUserConfirmation(String msgContent) {
		setUser1stInput("");
		while (!(getUser1stInput().toUpperCase().equals("YES") || getUser1stInput().toUpperCase().equals("NO"))) {
			System.out.print("Do you want to " + msgContent + " (Yes or No):");
			setUser1stInput(userInput.nextLine());
		}
		if (getUser1stInput().trim().toUpperCase().equals("YES")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Terminate the processing after checking tasks list in the application buffer
	 */
	static void terminateProcessing() {
		if (inTaskList.size() > 0) {
			Messages.InfoMessage("TASKBUFFER");
			if (getUserConfirmation("save or discard Task/s before quit"))
				addTasksToFile();
		}
		Messages.INFOTERMINATEPRC.printOut(2);
		System.exit(0);
	}

	/**
	 * Reading the tasks list from input array list and writing those tasks in
	 * ToDoList file using the dataWriter method
	 */
	static void addTasksToFile() {
		for (MyTask dataRecord : inTaskList) {
			TxtFileHandler.dataWriter(dataRecord.getInTask(), dataRecord.getInProject(), dataRecord.getInDueDate(),
					dataRecord.getInStatus());
		}
		switch (inTaskList.size()) {
		case 0:
			Messages.INFOADDNOTASK2FI.printOut(2);
			break;

		case 1:
			Messages.InfoMessage("ADDDONETASK");
			break;

		default:
			Messages.InfoMessage("ADDMANYTASK");
			break;
		}
		inTaskList.clear();
	}

	public static String getUser1stInput() {
		return user1stInput;
	}

	public static void setUser1stInput(String user1stInput) {
		UserInputHandler.user1stInput = user1stInput;
	}

	public static String getUser3rdInput() {
		return user3rdInput;
	}

	public static void setUser3rdInput(String user3rdInput) {
		UserInputHandler.user3rdInput = user3rdInput;
	}

	public static String getUser2ndInput() {
		return user2ndInput;
	}

	public static void setUser2ndInput(String user2ndInput) {
		UserInputHandler.user2ndInput = user2ndInput;
	}

	public static String getUserOptionInput() {
		return userOptionInput;
	}

}
