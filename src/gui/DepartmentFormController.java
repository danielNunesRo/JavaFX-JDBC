package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Departments;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	
	
	private Departments departmentEntity;
	
	private DepartmentService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private Label labelErrorName;
	
	public void setDepartment(Departments departmentEntity) {
		this.departmentEntity = departmentEntity;
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
			if(departmentEntity == null || service == null) {
				throw new IllegalStateException("Service or Entity was null!");
			}
			try {
				departmentEntity = getFormData();
				service.saveOrUpdate(departmentEntity);
				notifyDataChangeListeners();
				Utils.currentStage(event).close();
			} catch (DbException e) {
				Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
			}
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener: dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Departments getFormData() {
		Departments obj = new Departments();
		obj.setId(Utils.tryParseToInt(txtId.getId()));
		obj.setName(txtName.getText());
		
		return obj;
		
	}

	@FXML
	public void onBtCancelAction() {
		System.out.println("ONN BT ACTION - CANCEL");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	public void updateFormData() {
		if (departmentEntity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setId(String.valueOf(departmentEntity.getId()));
		txtName.setText(String.valueOf(departmentEntity.getName()));
	}

}
