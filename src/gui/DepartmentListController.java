package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Departments;

public class DepartmentListController implements Initializable {

	@FXML
	private Button buttonNovo;
	
	@FXML
	private TableView<Departments> tableViewDepartments;
	
	@FXML
	private TableColumn<Departments, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Departments, String> tableColumnName;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("ONBT NEW ACTION!");
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

}
