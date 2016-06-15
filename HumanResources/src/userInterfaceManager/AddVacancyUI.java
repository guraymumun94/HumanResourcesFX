package userInterfaceManager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import jobDataManager.JobManager;
import vacancyDataManager.Vacancy;
import vacancyDataManager.VacancyManager;

public class AddVacancyUI implements Initializable {
	@FXML
	Label wrongTypeLabel;
	@FXML
	Label wrongJobTitleLabel;
	@FXML
	Label wrongCountLabel;
	@FXML
	ComboBox<String> typeCombo;
	@FXML
	ComboBox<String> jobTitleCombo;
	@FXML
	ComboBox<Integer> countCombo;
	@FXML
	TextArea description;
	JobManager jobManager;
	VacancyManager vacancyManager;

	public void addVacancy() {		
		if(isWrong())
			return;
		
		int jobID = jobManager.getJobID(jobTitleCombo.getSelectionModel().getSelectedItem());
		String type = typeCombo.getSelectionModel().getSelectedItem();
		int count = countCombo.getSelectionModel().getSelectedItem();
		
		if(VacancyManager.vacancies.contains(new Vacancy(jobManager.getJobSpecialization(jobID), type, count, description.getText()))) {
			Alert alert = new Alert(AlertType.ERROR, "Вече съществува вакантно място с посочените данни.\nМоля, опитайте отново!", ButtonType.OK);
			alert.setHeaderText("Не може да се добави ново вакантно място!");
			alert.setTitle("");
			alert.show();
			return;
		}
		vacancyManager.addVacancy(jobID, type, count, description.getText());
		
		Alert alert = new Alert(AlertType.INFORMATION, "Успешно добавихте ново вакантно място!", ButtonType.OK);
		alert.setHeaderText("Поздравления!");
		alert.setTitle("");
		alert.show();
	}
	
	private boolean isWrong() {
		if(typeCombo.getSelectionModel().getSelectedItem() == null) {
			wrongTypeLabel.setVisible(true);
			return true;
		}
		else
			wrongTypeLabel.setVisible(false);
		
		if(jobTitleCombo.getSelectionModel().getSelectedItem() == null) {
			wrongJobTitleLabel.setVisible(true);
			return true;
		}	
		else
			wrongJobTitleLabel.setVisible(false);
		
		if(countCombo.getSelectionModel().getSelectedItem() == null) {
			wrongCountLabel.setVisible(true);
			return true;
		}
		else
			wrongCountLabel.setVisible(false);
		
		return false;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vacancyManager = new VacancyManager();		
		jobManager = new JobManager();
		vacancyManager.getVacancies("Всички", "Всички"); //initialize ObservableList<Vacancy> vacancies
		
		ObservableList<String> types = FXCollections.observableArrayList("Постоянна работа", "Временна работа", "Стаж");
		typeCombo.setItems(types);
		wrongTypeLabel.setVisible(false);
		ObservableList<String> jobTitles = FXCollections.observableArrayList(jobManager.getJobIDsMap().keySet());
		jobTitleCombo.setItems(jobTitles);
		wrongJobTitleLabel.setVisible(false);
		ObservableList<Integer> counts = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);
		countCombo.setItems(counts);
		wrongCountLabel.setVisible(false);
	}
	

}
