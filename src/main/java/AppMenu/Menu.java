
package AppMenu;

public class Menu implements AppInterfaces {

	private static final String MainMenuOptions = (" ___________________________________________ \n"
			+ "|                                           |\n" + "|            ToDoList Main Menu             |\n"
			+ "|                                           |\n" + " ——————————————————————————————————————————— \n"
			+ "\nPick an option:\n" + "(0) Quit and Terminate the processing\n"
			+ "(1) Show Task List (by date or project)\n" + "(2) Add New Task\n"
			+ "(3) Edit Task (update, mark as done, remove)\n" + "(4) Save Tasks to File without Quit\n"
			+ "(5) Save Tasks to File and Quit\n");

	private static final String ShowTaskOptions = (" ___________________________________________ \n"
			+ "|                                           |\n" + "|       Show Task List Options Menu         |\n"
			+ "|                                           |\n" + " ——————————————————————————————————————————— \n"
			+ "\nPick an option:\n" + "(0) Quit and Terminate the processing\n" + "(1) Show list of all Tasks\n"
			+ "(2) Show Tasks list by date\n" + "(3) Show Tasks list by project\n"
			+ "(4) Return Back to ToDoList Main Menu\n");

	private static final String EditTaskOptions = (" ___________________________________________ \n"
			+ "|                                           |\n" + "|        Task Edit Options Menu             |\n"
			+ "|                                           |\n" + " ——————————————————————————————————————————— \n"
			+ "\nPick an option:\n" + "(0) Quit and Terminate the processing\n" + "(1) Edit Task Name\n"
			+ "(2) Edit Project name\n" + "(3) Change Task Due Date\n" + "(4) Mark Task as \"Done\"\n"
			+ "(5) Remove a Task\n" + "(6) Return Back to ToDoList Main Menu\n");

	public void toDisplayMenuOptions(int menuOptionsKey) {

		String menuOptions = null;

		switch (menuOptionsKey) {

		case 1:
			menuOptions = MainMenuOptions;
			break;

		case 2:
			menuOptions = ShowTaskOptions;
			break;

		case 3:
			menuOptions = EditTaskOptions;
			break;
		}
		System.out.println(menuOptions);
	}

}
