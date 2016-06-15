package jobDataManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import databaseManager.ConnectionManager;
import interfaces.IJobData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class JobManager implements IJobData{

	private ConnectionManager connection;
	private Statement statement;
	private ResultSet resultSet;
	
	@Override
	public ObservableList<Job> getJobs() {
		ObservableList<Job> jobs = FXCollections.observableArrayList();
		int id;
		String specialization;
		
		try {
			connection = new ConnectionManager();
			statement = connection.link().createStatement();
			
			resultSet = statement.executeQuery("select job.title, job.id from job");
			
			while(resultSet.next()) {
				id = resultSet.getInt("job.id");
				specialization = resultSet.getString("job.title");
				if(!jobs.contains(new Job(id, specialization)))
					jobs.add(new Job(id, specialization));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jobs;
	}

	public ObservableMap<String, Integer> getJobIDsMap() {
		ObservableList<Job> jobs = getJobs();
		ObservableMap<String, Integer> IDs = FXCollections.observableHashMap();
		for (Job job : jobs) {
			IDs.put(job.getTitle(), job.getId());
		}
		return IDs;
	}
	
	public int getJobID(String specialization) {
		return getJobIDsMap().get(specialization);
	}
	
	public String getJobSpecialization(int id) {
		ObservableList<Job> jobs = getJobs();
		ObservableMap<Integer, String> specializations = FXCollections.observableHashMap();
		for (Job job : jobs) {
			specializations.put(job.getId(), job.getTitle());
		}
		return specializations.get(id);
	}
}
