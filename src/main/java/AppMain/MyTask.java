package AppMain;

public class MyTask {

	private String inTask;
	private String inProject;
	private String inDueDate;
	private String inStatus;

	// Constructor has type of data that is required
	public MyTask(String taskName, String taskProject, String taskDueDate, String taskStatus) {
		this.setInTask(taskName);
		this.setInProject(taskProject);
		this.setInDueDate(taskDueDate);
		this.setInStatus(taskStatus);
	}

	public String getInTask() {
		return inTask;
	}

	public void setInTask(String inTask) {
		this.inTask = inTask;
	}

	public String getInProject() {
		return inProject;
	}

	public void setInProject(String inProject) {
		this.inProject = inProject;
	}

	public String getInDueDate() {
		return inDueDate;
	}

	public void setInDueDate(String inDueDate) {
		this.inDueDate = inDueDate;
	}

	public String getInStatus() {
		return inStatus;
	}

	public void setInStatus(String inStatus) {
		this.inStatus = inStatus;
	}

}
