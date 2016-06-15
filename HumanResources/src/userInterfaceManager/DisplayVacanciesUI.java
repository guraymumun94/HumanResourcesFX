package userInterfaceManager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import vacancyDataManager.Vacancy;
import vacancyDataManager.VacancyManager;

public class DisplayVacanciesUI implements Initializable{
	@FXML 
	ListView<Vacancy> vacanciesListView;
	@FXML
	ComboBox<String> typeCombo;
	@FXML
	ComboBox<String> specializationCombo;
	VacancyManager vacancyManager;
	
	public void displayVacancies() {		
//		if(typeCombo.getSelectionModel().getSelectedItem() == null && specializationCombo.getSelectionModel().getSelectedItem() == null)
//			vacanciesListView.setItems(vacancies.getVacancies("Всички", "Всички"));
		if((typeCombo.getSelectionModel().getSelectedItem().toString().equals("Всички") && specializationCombo.getSelectionModel().getSelectedItem().toString().equals("Всички")))
			vacanciesListView.setItems(vacancyManager.getVacancies("Всички", "Всички"));
		else if((typeCombo.getSelectionModel().getSelectedItem().toString().equals("Всички") && !specializationCombo.getSelectionModel().getSelectedItem().toString().equals("Всички")))
			vacanciesListView.setItems(vacancyManager.getVacancies(typeCombo.getSelectionModel().getSelectedItem().toString(), specializationCombo.getSelectionModel().getSelectedItem().toString()));
		else if((!typeCombo.getSelectionModel().getSelectedItem().toString().equals("Всички") && specializationCombo.getSelectionModel().getSelectedItem().toString().equals("Всички")))
			vacanciesListView.setItems(vacancyManager.getVacancies(typeCombo.getSelectionModel().getSelectedItem().toString(), specializationCombo.getSelectionModel().getSelectedItem().toString()));
		if(vacancyManager.getVacancies(typeCombo.getSelectionModel().getSelectedItem().toString(), specializationCombo.getSelectionModel().getSelectedItem().toString()).isEmpty()) {
			vacanciesListView.setItems(FXCollections.observableArrayList());
			Alert alert = new Alert(AlertType.ERROR, "Опитайте отново!", ButtonType.OK);
			alert.setHeaderText("Няма намерени резултати!");
			alert.setTitle("");
			alert.show();
		}
		
		else
			vacanciesListView.setItems(vacancyManager.getVacancies(typeCombo.getSelectionModel().getSelectedItem().toString(), specializationCombo.getSelectionModel().getSelectedItem().toString()));
	}
	
	@FXML 
	public void handleMouseClick(MouseEvent click) {
        if (vacanciesListView.getSelectionModel().getSelectedItem() != null && click.getClickCount() == 2) {
        	Stage dialog = new Stage();
        	dialog.setTitle("Описание на избраната позиция");
        	
        	Text description = new Text(vacanciesListView.getSelectionModel().getSelectedItem().getDescription());
        	description.setWrappingWidth(360);
        	description.setTextAlignment(TextAlignment.JUSTIFY);
        	
        	ScrollPane scrollPane = new ScrollPane(description);
        	scrollPane.setPadding(new Insets(10, 10, 10, 10));
        	
        	Scene scene = new Scene(scrollPane);
        	scrollPane.setFitToWidth(true);
        	dialog.setScene(scene);
        	dialog.setHeight(400);
        	dialog.setWidth(400);
        	dialog.setResizable(false);
        	dialog.show();
        }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> specializations = FXCollections.observableArrayList("Всички", "Специализация1", "Специализация2", "Специализация3");
		specializationCombo.setItems(specializations);
		//specializationCombo.getSelectionModel().select("Всички");
		specializationCombo.setValue("Всички");
		
		vacancyManager = new VacancyManager();
		vacancyManager.getVacancies("Всички", "Всички");
		
		ObservableList<String> types = FXCollections.observableArrayList("Всички", "Постоянна работа", "Временна работа", "Стаж");
		typeCombo.setItems(types);
		//typeCombo.getSelectionModel().select("Всички");
		typeCombo.setValue("Всички");
	}
	
}
