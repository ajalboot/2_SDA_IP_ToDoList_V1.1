package AppHandler;



public enum Messages {

	/*
	 *  Data Collection Messages
	 */
	ASKFORUSEROPTION("Please, Choose an option from the above list: "), 
	ASKFORTASKNAMEIN("Please, Enter Task name: "),
	ASKFORTASKPROJCT("Please, Enter Project name: "), 
	ASKFORTSKDUEDATE("Please, Enter Task DueDate[YYYYMMDD]: "),
	ASKFORNEWDUEDATE("Please, Enter Task DueDate[YYYYMMDD] (NEW): "), 
	ASKFORNEWSTATUSS("Please, Enter Status (NEW): "),
	ASKFOROLDTASKNME("Please, Enter Task Name (OLD): "), 
	ASKFORNEWTASKNME("Please, Enter Task Name (NEW): "),
	ASKFOROLDPROJECT("Please, Enter Project Name (OLD): "), 
	ASKFORNEWPROJECT("Please, Enter Project Name (NEW): "),
	/*
	 * Information Messages.
	 */	
	INFOWELCOMEFIRST("\nWelcome to ToDoList Application...\n"),
	INFOWELCOMEBACK("\nWelcome back to ToDoList Main Menu...\n"),
	INFOTERMINATEPRC("\nProcess has been successfully terminated...\n"),
	INFOADDNOTASK2FI("There is no new task to add to ToDoList.csv file\n"), 
	INFOSHOWALLTASKS("\nShow all tasks.\n"),
	INFOSHOWTSKBYDAT("\nShow Tasks List by Duedate.\n"), 
	INFOSHOWTSKBYPRC("\nShow Tasks List by Project Name.\n"),
	INFOSAVEANDQUIT("Save and Quit, Bye !\n"),
	PRINTFILEHEADERS("Task" + TxtFileHandler.colDel + "Project" + TxtFileHandler.colDel + "DueDate  "
			+ TxtFileHandler.colDel + "Status" + "\n" + "====" + TxtFileHandler.colDel + "======="
			+ TxtFileHandler.colDel + "=======   " + TxtFileHandler.colDel + "======"),
	INFOUNKNOWNMESG("");

	private String msg;

	Messages(String inMSG) {
		this.msg = inMSG;
	}

	public void printOut(int newLine) {
		switch (newLine) {
		case 1:
			System.out.print(msg);
			break;
		case 2:
			System.out.println(msg);
			break;
		}
	}

	/*
	 * Create a special methods to handle Info Messages that contains dynamic parameters because the enom
	 * 
	 * @param messageKey (String) > it's an unique key that used to differentiate
	 *                   between output messages.
	 */
	public static void InfoMessage(String messgaeKey) {

		String msgBody = null;

		switch (messgaeKey) {

		case "WRONGINPUT":
			msgBody = ("\nOpps!! Your input (" + UserInputHandler.getUserOptionInput()
					+ ") does not exist in Options List\n");
			break;

		case "TASKBUFFER":
			msgBody = ("\nOpps!! You have " + UserInputHandler.getInTaskList() + " Task/s in process buffer.\n");
			break;

		case "ADDDONETASK":
			msgBody = (" " + UserInputHandler.getInTaskList() + " task has been added to ToDoList.csv file\n");
			break;

		case "ADDMANYTASK":
			msgBody = (" " + UserInputHandler.getInTaskList() + "  tasks have been added to ToDoList.csv file\n");
			break;

		case "TASKSSTATIS":
			msgBody = ("You have " + TxtFileHandler.tasksStats("TODO") + " tasks todo and "
					+ TxtFileHandler.tasksStats("DONE") + " tasks are done!\n");
			break;

		case "ADDNEWTASK":
			msgBody = ("\nThe task has been added to the list." + "\n-Task name : " + UserInputHandler.getUser1stInput()
					+ "\n-Project name : " + UserInputHandler.getUser2ndInput() + "\n-Task DueDate : "
					+ UserInputHandler.getUser3rdInput() + "\n\nDo you want add new task [(Y)es,(N)o] :");
			break;

		default:
			break;
		}
		System.out.println(msgBody);
	}
}
