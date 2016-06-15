package interfaces;

import javafx.collections.ObservableList;
import vacancyDataManager.Vacancy;

public interface IVacancyData {
	public ObservableList<Vacancy> getVacancies(String type, String specialization);
	public void addVacancy(int jobID, String type, int count, String description);
}
