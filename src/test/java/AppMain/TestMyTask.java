package AppMain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestMyTask {

	/*
	 * Instantiate a new object from MyTask Class and Test getter and setter methods
	 * for its variables
	 */

	@Test
	public void MyTaskTest1() {

		MyTask testTask = new MyTask(null,null,null,null) ;
		
		testTask.setInTask("Task1");

		assertEquals("Task1", testTask.getInTask());
		
		testTask.setInProject("Proc2");

		assertEquals("Proc2", testTask.getInProject());
		
		testTask.setInDueDate("20200401");
		
		assertEquals("20200401", testTask.getInDueDate());
		
		testTask.setInStatus("Todo");

		assertEquals("Todo", testTask.getInStatus());

	}

}
