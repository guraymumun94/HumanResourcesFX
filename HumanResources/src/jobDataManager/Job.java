package jobDataManager;

public class Job implements Comparable<Job>{
	
	private int id;
	private String title;
	
	public Job(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(Job other) {
		if(this.id == other.id || this.title.compareTo(other.title) == 0)
			return 0;
		
		return -1;
		
	}
	
}
