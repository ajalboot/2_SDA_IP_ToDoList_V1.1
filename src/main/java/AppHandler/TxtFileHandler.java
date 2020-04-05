
package AppHandler;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import AppMain.MyTask;

public class TxtFileHandler {

	// Define a variable to save arrayList record during data parsing
	static String dataLine = null;

	// Define Variables to save required data for the origin file
	static String fileName = "./bin/ToDoList.txt";
	static File file = new File(fileName);
	static Path filePath = Paths.get(fileName);

	// Define Variables to save required data for the temporary files
	static String tmpFileName = "./bin/.ToDoList.tmp";
	static File tmpFile = new File(tmpFileName);
	static Path tmpFilePath = Paths.get(tmpFileName);

	// Define a variable for columns delimiter
	public static String colDel = "\t\t\t";

	private static ArrayList<MyTask> outTaskList = new ArrayList<MyTask>();

	/**
	 * 
	 * Writing a task in the ToDoList.txt file.
	 * 
	 * @param Task    Task name input
	 * @param Project Projet name input
	 * @param DueDate Task Duedate input
	 * @param Status  Task Status input
	 */
	static void dataWriter(String inTask, String inProject, String inDueDate, String inStatus) {

		StandardOpenOption standardOpenOption = StandardOpenOption.APPEND;
		
		if (!file.exists())
			standardOpenOption = StandardOpenOption.CREATE;

		try (BufferedWriter dataWriter = Files.newBufferedWriter(filePath, standardOpenOption);) {

			dataWriter.write(inTask + colDel + inProject + colDel + inDueDate + colDel + inStatus + "\n");

			dataWriter.flush();

			dataWriter.close();

		} catch (IOException e) {
			//if unable to edit the todolist file,it throws an error and exits immediately
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	 * Reading file lines.
	 * 
	 * if ( KeyParam > 0) The application does not print the lines that written in
	 * ToDoList file. (KeyParam =1 or 2) The application prints only the lines that
	 * met the condition. (KeyParam = 3) The application prints all the lines that
	 * written in ToDoList files
	 * 
	 * @param keyParam       Column position in target file
	 * @param conditionParam Data filter condition
	 * @param infilePath     Input file path that application will read it
	 */
	static void dataReader(int keyParam, String conditionParam, Path infilePath) {
		int readerCounter = 0;

		if (infilePath == null)
			infilePath = filePath;

		outTaskList.clear();

		try (BufferedReader datareader = Files.newBufferedReader(infilePath)) {
			while ((dataLine = datareader.readLine()) != null) {
				String[] dataField = dataLine.split(colDel);
				outTaskList.add(new MyTask(dataField[0], dataField[1], dataField[2], dataField[3]));
			}
			datareader.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		if (keyParam > 0) {
			headerWriter(1);
			for (MyTask rec : outTaskList) {
				if (!rec.getInDueDate().toUpperCase().equals("DUEDATE")
						&& !rec.getInStatus().toUpperCase().equals("STATUS")
						&& !rec.getInDueDate().toUpperCase().equals("=======")
						&& !rec.getInStatus().toUpperCase().equals("======")) {
					switch (keyParam) {
					case 1:
						if (rec.getInProject().trim().toUpperCase().equals(conditionParam.trim().toUpperCase())) {
							System.out.println(rec.getInTask() + colDel + rec.getInProject() + colDel
									+ rec.getInDueDate() + colDel + rec.getInStatus());
							readerCounter++;
						}
						break;

					case 2:
						if (rec.getInDueDate().trim().toUpperCase().equals(conditionParam.trim().toUpperCase())) {
							System.out.println(rec.getInTask() + colDel + rec.getInProject() + colDel
									+ rec.getInDueDate() + colDel + rec.getInStatus());
							readerCounter++;
						}
						break;

					case 3:
						System.out.println(rec.getInTask() + colDel + rec.getInProject() + colDel + rec.getInDueDate()
								+ colDel + rec.getInStatus());
						readerCounter++;
						break;
					}
				}
			}
			// Printing the count of retrieved lines
			if (readerCounter > 0) {
				System.out.println("\nCount of displayed Tasks = " + readerCounter + "\n");
			} else {
				System.out.println("\n No data found for identifier ( " + conditionParam + " )\n");
			}
		}
	}

	/**
	 * Updating task attributes based on input parameters (keyParam, taskName and
	 * projectName)
	 * 
	 * @param keyParam Column position in target file
	 * @param taskName Task name that will update
	 * @param procName Project name for the Task that will update
	 * @param newData  The new data that will replace in
	 */
	static void dataUpdater(int keyParam, String taskName, String procName, String newData) {

		file.renameTo(tmpFile);

		dataReader(0, null, tmpFilePath);

		for (MyTask rec : outTaskList) {

			if (rec.getInTask().trim().toUpperCase().equals(taskName.trim().toUpperCase())
					&& rec.getInProject().trim().toUpperCase().equals(procName.trim().toUpperCase())) {

				String columnName = null, columnValue = null;

				switch (keyParam) {
				case 0:
					dataWriter(newData, rec.getInProject(), rec.getInDueDate(), rec.getInStatus());
					columnName = "Task Name";
					columnValue = rec.getInTask();
					break;

				case 1:
					dataWriter(rec.getInTask(), newData, rec.getInDueDate(), rec.getInStatus());
					columnName = "Project Name";
					columnValue = rec.getInProject();
					break;

				case 2:
					dataWriter(rec.getInTask(), rec.getInProject(), newData, rec.getInStatus());
					columnName = "DueDate";
					columnValue = rec.getInDueDate();
					break;

				case 3:
					dataWriter(rec.getInTask(), rec.getInProject(), rec.getInDueDate(), newData);
					columnName = "Status";
					columnValue = rec.getInStatus();
					break;

				}
				System.out.print("Info : \n" + columnName + " for task : " + taskName + " has been updated\n Old  : "
						+ columnValue + "\n New : " + newData + "\n");
			} else {
				dataWriter(rec.getInTask(), rec.getInProject(), rec.getInDueDate(), rec.getInStatus());
			}
		}

		tmpFile.delete();
	}

	/**
	 * Deleting task from ToDoList file based on (taskName and projectName) input
	 * parameters
	 * 
	 * @param taskName Task name that will update
	 * @param procName Project name for the Task that will update
	 */
	static void dataDeleter(String taskName, String procName) {

		file.renameTo(tmpFile);

		dataReader(0, null, tmpFilePath);

		for (MyTask rec : outTaskList) {

			if (rec.getInTask().trim().toUpperCase().equals(taskName.trim().toUpperCase())
					&& rec.getInProject().trim().toUpperCase().equals(procName.trim().toUpperCase())) {
				System.out.println("Info : \n Task name : " + taskName + " has been successfully deleted");
			} else {
				dataWriter(rec.getInTask(), rec.getInProject(), rec.getInDueDate(), rec.getInStatus());
			}
		}

		tmpFile.delete();
	}

	/**
	 * Counting the Tasks according to the task status (Done or Todo)
	 * 
	 * @param keyValue Task Status (Done or Todo)
	 * @return counterParam that contains the count of tasks in ToDoList file that
	 *         matchs with keyValue (Done or Todo).
	 */
	public static int tasksStats(String keyValue) {
		
		int taskCounter = 0;

		if (!file.exists())
			headerWriter(2);

		dataReader(0, null, filePath);

		for (MyTask rec : outTaskList) {
			if (rec.getInStatus().trim().toUpperCase().equals(keyValue))
				taskCounter++;
		}

		outTaskList.clear();

		return taskCounter;
	}

	/**
	 * Writing/Printing file header according the keyParam input parameter.
	 * 
	 * @param keyParam (keyParam = 1 ) Printing the file header on the console.
	 *                 (keyParam = 2 ) Writing file header in the ToDoList file
	 * 
	 */
	private static void headerWriter(int keyParam) {
		switch (keyParam) {
		case 1:
			Messages.PRINTFILEHEADERS.printOut(2);//("<PrintFileHeaderForUsers/>");
			break;

		case 2:
			dataWriter("Task", "Project", "DueDate", "Status");
			dataWriter("====", "=======", "=======", "======");
			break;
		}
	}

}
