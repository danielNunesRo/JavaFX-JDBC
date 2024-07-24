package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemVendedor;
	
	@FXML
	private MenuItem menuItemDepartamento;
	
	@FXML
	private MenuItem menuItemAbout;
	
	
	@FXML
	public void onMenuItemVendedor() {
		System.out.println("CLICOU EM VENDEDOR!");
	}
	
	@FXML
	public void onMenuItemDepartamento() {
		System.out.println("CLICOU EM DEPARTAMENTO!");
	}
	
	@FXML
	public void onMenuItemAbout() {
		System.out.println("CLICOU EM ABOUT!");
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
