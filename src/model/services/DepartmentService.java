package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Departments;

public class DepartmentService {
	
	
	public List<Departments> findAll() {
		List<Departments> list = new ArrayList<>();
		list.add(new Departments(1, "Juridico"));
		list.add(new Departments(2, "RH"));
		list.add(new Departments(3, "Contabilidade"));
		list.add(new Departments(4, "SAC"));
		return list;
	}
	
}
