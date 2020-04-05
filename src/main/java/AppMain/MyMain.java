
package AppMain;

import AppHandler.Messages;
import AppHandler.UserInputHandler;

public class MyMain {

	public static void main(String[] args) {

		clearConsole();

		Messages.INFOWELCOMEFIRST.printOut(2);

		Messages.InfoMessage("TASKSSTATIS");

		UserInputHandler.todoMainMenu();

	}

	/**
	 * Clear the console before printing the options menu. For windows executes
	 * command line "cls" else (Unix) the command line : "clear"
	 */
	public static final void clearConsole() {

		ProcessBuilder commandLineExecutor = new ProcessBuilder();

		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				commandLineExecutor.command("cls").inheritIO().start().waitFor();
			} else {
				commandLineExecutor.command("clear").inheritIO().start().waitFor();
			}
		} catch (final Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Checking if the input date is Numeric or not
	 * 
	 * @param inputData input data
	 * @return boolean (True or False)
	 */
	public static boolean isNumeric(String inputData) {
		return inputData.matches("[+-]?\\d*(\\.\\d+)?");
	}

}
