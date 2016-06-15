package vacancyDataManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import databaseManager.ConnectionManager;
import interfaces.IVacancyData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jobDataManager.JobManager;

public class VacancyManager implements IVacancyData{

	private ConnectionManager connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private JobManager jobManager;
	public static ObservableList<Vacancy> vacancies;
	
	public VacancyManager() {
		jobManager = new JobManager();
		getVacancies("Всички", "Всички");
		connection = new ConnectionManager();
	}
	
	@Override
	public ObservableList<Vacancy> getVacancies(String type, String specialization) {
		int count;
		String description;
		vacancies = FXCollections.observableArrayList();

		try {
			connection = new ConnectionManager();
			statement = connection.link().createStatement();
			
			if(!type.equals("Всички") && !specialization.equals("Всички"))
				resultSet = statement.executeQuery("select job.title, vacancy.type, vacancy.count, vacancy.description "
					+ "from vacancy, job where job.id = vacancy.job_id and vacancy.type = " + "\"" + type + "\""
							+ "and job.title = "+ "\"" + specialization + "\"");
			
			else if(type.equals("Всички") && !specialization.equals("Всички"))
				resultSet = statement.executeQuery("select job.title, vacancy.type, vacancy.count, vacancy.description "
						+ "from vacancy, job where job.id = vacancy.job_id "
								+ "and job.title = "+ "\"" + specialization + "\"");
			
			else if(!type.equals("Всички") && specialization.equals("Всички"))
				resultSet = statement.executeQuery("select job.title, vacancy.type, vacancy.count, vacancy.description "
						+ "from vacancy, job where job.id = vacancy.job_id "
								+ "and vacancy.type = " + "\"" + type + "\"");
			
			else if(type.equals("Всички") && specialization.equals("Всички"))
				resultSet = statement.executeQuery("select job.title, vacancy.type, vacancy.count, vacancy.description "
						+ "from vacancy, job where job.id = vacancy.job_id");
			
			while(resultSet.next()) {
				count = resultSet.getInt("vacancy.count");
				description = resultSet.getString("vacancy.description");
				type = resultSet.getString("vacancy.type");
				specialization = resultSet.getString("job.title");
				if(!vacancies.contains(new Vacancy(specialization, type, count, description)))
					vacancies.add(new Vacancy(specialization, type, count, description));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vacancies;
	}
	
	@Override
	public void addVacancy(int jobID, String type, int count, String description) {
		
		try {
			preparedStatement = connection.link().prepareStatement(
					"INSERT INTO vacancy(id, type, count, description, job_id) "
					+ "VALUES(?, ?, ?, ?, ?)");
			
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, type);
			preparedStatement.setInt(3, count);
			preparedStatement.setString(4, description);
			preparedStatement.setInt(5, jobID);
			
			if(!vacancies.contains(new Vacancy(jobManager.getJobSpecialization(jobID), type, count, description))) {
				preparedStatement.executeUpdate();
				vacancies.add(new Vacancy(jobManager.getJobSpecialization(jobID), type, count, description));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
