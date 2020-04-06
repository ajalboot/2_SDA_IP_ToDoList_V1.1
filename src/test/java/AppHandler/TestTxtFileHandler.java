
package AppHandler;

import static org.junit.Assert.assertEquals;

import java.io.File;
import org.junit.Test;

import AppMain.MyTask;

public class TestTxtFileHandler {

	File file = new File(TxtFileHandler.fileName);

	/*
	 * Testing dataWriter Method to create initial file if not exists
	 */
	@Test
	public void fileHandlerTest1() {

		if (file.exists())
			file.delete();

		TxtFileHandler.headerWriter(2);
		TxtFileHandler.dataWriter("Task1", "Proc1", "20200404", "Todo");

		TxtFileHandler.dataReader(0, null, null);

		assertEquals(true, file.exists());
	}

	/*
	 * Testing dataReader Method by reading the task attributes for the first Task
	 */
	@Test
	public void fileHandlerTest2() {

		TxtFileHandler.dataReader(0, null, null);

		for (MyTask rec : TxtFileHandler.getOutTaskList()) {

			assertEquals("Task1", rec.getInTask());
			assertEquals("Proc1", rec.getInProject());
			assertEquals("20200404", rec.getInDueDate());
			assertEquals("Todo", rec.getInStatus());
		}

	}

	/*
	 * Testing dataWriter and dataReader methods together
	 */
	@Test
	public void fileHandlerTest3() {
		int taskCount = 0;

		TxtFileHandler.dataWriter("Task2", "Proc1", "20200402", "Todo");
		TxtFileHandler.dataWriter("Task3", "Proc1", "20200403", "Todo");

		TxtFileHandler.dataReader(0, null, null);

		for (int i = 0; i < TxtFileHandler.getOutTaskList().size(); i++) {
			taskCount++;
		}

		assertEquals(3, taskCount);
	}

	/*
	 * Testing dataUpdater Method by updating Project Name
	 */
	@Test
	public void fileHandlerTest4() {

		TxtFileHandler.dataUpdater(1, "Task1", "Proc1", "Proc2");

		int taskCount = 0;
		TxtFileHandler.dataReader(1, "Proc2", null);

		for (int i = 0; i < TxtFileHandler.getOutTaskList().size(); i++) {
			taskCount++;
		}
		assertEquals(1, taskCount);
	}

	/*
	 * Testing dataUpdater Method by updating Task Status
	 */
	@Test
	public void fileHandlerTest5() {

		TxtFileHandler.dataUpdater(3, "Task3", "Proc1", "Done");

		assertEquals(1, TxtFileHandler.tasksStats("DONE"));
	}

	/*
	 * Testing tasksStats Method
	 */
	@Test
	public void fileHandlerTest6() {

		assertEquals(2, TxtFileHandler.tasksStats("TODO"));
	}

	/*
	 * Testing dataDeleter Method
	 */
	@Test
	public void fileHandlerTest7() {
		int taskCount = 0;
		TxtFileHandler.dataDeleter("Task2", "Proc1");

		TxtFileHandler.dataReader(0, null, null);
		for (int i = 0; i < TxtFileHandler.getOutTaskList().size(); i++) {
			taskCount++;
		}

		assertEquals(2, taskCount);
	}

}
