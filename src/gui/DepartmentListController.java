package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Departments;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {

	
	
	@FXML
	private Button buttonNovo;
	
	@FXML
	private TableView<Departments> tableViewDepartments;
	
	@FXML
	private TableColumn<Departments, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Departments, String> tableColumnName;
	
	private ObservableList<Departments> obsList;
	
	private DepartmentService service;
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/DepartmentForm.fxml", parentStage);
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartments.prefHeightProperty().bind(stage.heightProperty());
		
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Departments> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartments.setItems(obsList);
	}
	
	private void createDialogForm(String absolutName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
			Pane pane = loader.load();
			
			Stage dialogueStage = new Stage();
			dialogueStage.setTitle("Insira os dados do Departamento");
			dialogueStage.setScene(new Scene(pane));
			dialogueStage.setResizable(false);
			dialogueStage.initOwner(parentStage);
			dialogueStage.initModality(Modality.WINDOW_MODAL);
			dialogueStage.showAndWait();
			
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "ERROR  LOADING VIEW", e.getMessage(), AlertType.ERROR);
		}
	}

}
