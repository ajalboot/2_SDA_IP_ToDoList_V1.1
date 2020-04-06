package AppMain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestMyMain {

	/*
	 * Testing isNumeric Method that use to check User Input data for Task DueDate
	 */
	@Test
	public void MyMainTest1() {

		assertEquals(true, MyMain.isNumeric("20200304"));

		assertEquals(false, MyMain.isNumeric("2Z0200304"));

	}

}
